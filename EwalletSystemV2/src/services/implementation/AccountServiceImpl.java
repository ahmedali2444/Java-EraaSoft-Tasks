package services.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import dao.AccountDao;
import dao.TransactionDao;
import dao.WalletCardDao;
import models.Account;
import models.Transaction;
import models.WalletCard;
import services.AccountService;
import services.EmailNotificationService;
import services.ValidationService;
import util.DatabaseConnection;

public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao = new AccountDao();
    private final TransactionDao transactionDao = new TransactionDao();
    private final WalletCardDao walletCardDao = new WalletCardDao();
    private final ValidationService validationService = new ValidationServiceImpl();
    private final EmailNotificationService emailNotificationService;

    public AccountServiceImpl(EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    private String getAmountValidationMessage(double amount) {
        if (!Double.isFinite(amount)) {
            return "Amount must be a valid number.";
        }

        if (amount < 100) {
            return "Amount must be at least 100.";
        }

        if (amount % 100 != 0) {
            return "Amount must be 100, 200, 300, 400, etc.";
        }

        return null;
    }

    private Transaction createTransaction(Account account, String transactionType, double amount,
            String relatedEmail, String description) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(account.getAccountId());
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transaction.setRelatedEmail(relatedEmail);
        transaction.setDescription(description);
        return transaction;
    }

    @Override
    public void AddTransaction(String email, String transactionType, double amount,
            String relatedEmail, String description) {
        if (description == null || description.trim().isEmpty()) {
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            Account account = accountDao.findByEmail(connection, email);
            if (account != null) {
                transactionDao.insert(connection, createTransaction(account, transactionType,
                        amount, relatedEmail, description));
            }
        } catch (SQLException exception) {
        }
    }

    @Override
    public Account GetAccountByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            return accountDao.findByEmail(connection, email);
        } catch (SQLException exception) {
            return null;
        }
    }

    private Account findAccountByRecipientIdentifier(Connection connection, String recipientIdentifier)
            throws SQLException {
        if (recipientIdentifier == null || recipientIdentifier.trim().isEmpty()) {
            return null;
        }

        String identifier = recipientIdentifier.trim();
        Account account = accountDao.findByEmail(connection, identifier);
        return account != null ? account : accountDao.findByPhoneNumber(connection, identifier);
    }

    private Account getAccountByRecipientIdentifier(String recipientIdentifier) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            return findAccountByRecipientIdentifier(connection, recipientIdentifier);
        } catch (SQLException exception) {
            return null;
        }
    }

    @Override
    public List<Account> GetAllAccounts() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            return accountDao.findAll(connection);
        } catch (SQLException exception) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Transaction> GetTransactionHistory() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            return transactionDao.findAll(connection);
        } catch (SQLException exception) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Transaction> GetAccountTransactionHistory(String email) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Account account = accountDao.findByEmail(connection, email);
            if (account == null) {
                return new ArrayList<>();
            }
            return transactionDao.findByAccountId(connection, account.getAccountId());
        } catch (SQLException exception) {
            return new ArrayList<>();
        }
    }

    @Override
    public Boolean IsEmailExists(String email) {
        return GetAccountByEmail(email) != null;
    }

    @Override
    public Boolean IsPasswordMatches(Account account) {
        if (account == null) {
            return false;
        }

        Account savedAccount = GetAccountByEmail(account.getEmail());
        return savedAccount != null
                && savedAccount.getIsActive()
                && savedAccount.getPassword().equals(account.getPassword());
    }

    @Override
    public Boolean IsAccountActive(String email) {
        Account account = GetAccountByEmail(email);
        return account != null && account.getIsActive();
    }

    @Override
    public Boolean CreateAccount(Account account) {
        String error = GetCreateAccountError(account);
        if (error != null) {
            return false;
        }

        account.setIsAdmin(false);
        account.setIsActive(true);

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            if (!accountDao.insert(connection, account)) {
                connection.rollback();
                return false;
            }

            Account savedAccount = accountDao.findByEmail(connection, account.getEmail());
            transactionDao.insert(connection, createTransaction(savedAccount, "SIGNUP", 0,
                    null, "Account created successfully."));
            connection.commit();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public String GetCreateAccountError(Account account) {
        if (account == null) {
            return "Account data cannot be empty.";
        }

        String error = validationService.getEmailValidationMessage(account.getEmail());
        if (error != null) {
            return error;
        }

        error = validationService.getPasswordValidationMessage(account.getPassword());
        if (error != null) {
            return error;
        }

        error = validationService.getAgeValidationMessage(account.getAge());
        if (error != null) {
            return error;
        }

        error = validationService.getPhoneNumberValidationMessage(account.getPhoneNumber());
        if (error != null) {
            return error;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (accountDao.findByEmail(connection, account.getEmail()) != null) {
                return "Email already exists.";
            }

            if (accountDao.findByPhoneNumber(connection, account.getPhoneNumber()) != null) {
                return "Phone number already exists.";
            }
        } catch (SQLException exception) {
            return "Cannot connect to the database.";
        }

        return null;
    }

    @Override
    public Boolean Deposit(String email, double amount) {
        String error = GetDepositError(email, amount);
        if (error != null) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            Account account = accountDao.findByEmail(connection, email);
            double newBalance = account.getBalance() + amount;
            accountDao.updateBalance(connection, account.getAccountId(), newBalance);
            transactionDao.insert(connection, createTransaction(account, "DEPOSIT", amount,
                    null, "Money deposited. New balance: " + newBalance));
            connection.commit();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public String GetDepositError(String email, double amount) {
        String amountError = getAmountValidationMessage(amount);
        if (amountError != null) {
            return amountError;
        }

        Account account = GetAccountByEmail(email);
        if (account == null) {
            return "Account does not exist.";
        }
        if (!account.getIsActive()) {
            return "Account is inactive.";
        }
        return null;
    }

    @Override
    public Boolean Withdraw(String email, double amount) {
        String error = GetWithdrawError(email, amount);
        if (error != null) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            Account account = accountDao.findByEmail(connection, email);
            double newBalance = account.getBalance() - amount;
            accountDao.updateBalance(connection, account.getAccountId(), newBalance);
            transactionDao.insert(connection, createTransaction(account, "WITHDRAW", amount,
                    null, "Money withdrawn. New balance: " + newBalance));
            connection.commit();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public String GetWithdrawError(String email, double amount) {
        String error = GetDepositError(email, amount);
        if (error != null) {
            return error;
        }

        Account account = GetAccountByEmail(email);
        if (account.getBalance() < amount) {
            return "Balance is not enough.";
        }
        return null;
    }

    @Override
    public Boolean Transfer(String sourceEmail, String recipientIdentifier, double amount) {
        String error = GetTransferError(sourceEmail, recipientIdentifier, amount);
        if (error != null) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            Account source = accountDao.findByEmail(connection, sourceEmail);
            Account destination = findAccountByRecipientIdentifier(connection, recipientIdentifier);
            if (source == null || destination == null) {
                connection.rollback();
                return false;
            }
            double sourceBalance = source.getBalance() - amount;
            double destinationBalance = destination.getBalance() + amount;

            accountDao.updateBalance(connection, source.getAccountId(), sourceBalance);
            accountDao.updateBalance(connection, destination.getAccountId(), destinationBalance);
            transactionDao.insert(connection, createTransaction(source, "TRANSFER_SENT", amount,
                    destination.getEmail(), "Transfer sent. New balance: " + sourceBalance));
            transactionDao.insert(connection, createTransaction(destination, "TRANSFER_RECEIVED", amount,
                    sourceEmail, "Transfer received. New balance: " + destinationBalance));
            connection.commit();
            emailNotificationService.sendTransferReceived(destination.getEmail(), source.getEmail(), amount,
                    destinationBalance);
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public String GetTransferError(String sourceEmail, String recipientIdentifier, double amount) {
        if (sourceEmail == null || sourceEmail.trim().isEmpty()) {
            return "Source email cannot be empty.";
        }
        if (recipientIdentifier == null || recipientIdentifier.trim().isEmpty()) {
            return "Recipient email or phone cannot be empty.";
        }

        String error = GetWithdrawError(sourceEmail, amount);
        if (error != null) {
            return error;
        }

        Account destination = getAccountByRecipientIdentifier(recipientIdentifier);
        if (destination == null) {
            return "Recipient email or phone does not exist.";
        }
        if (sourceEmail.trim().equalsIgnoreCase(destination.getEmail())) {
            return "You cannot transfer money to yourself.";
        }
        if (!destination.getIsActive()) {
            return "Recipient account is inactive.";
        }
        return null;
    }

    @Override
    public Boolean ChangePassword(String email, String oldPassword, String newPassword) {
        String error = GetChangePasswordError(email, oldPassword, newPassword);
        if (error != null) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            Account account = accountDao.findByEmail(connection, email);
            accountDao.updatePassword(connection, account.getAccountId(), newPassword);
            transactionDao.insert(connection, createTransaction(account, "PASSWORD_CHANGE", 0,
                    null, "Password changed successfully."));
            connection.commit();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public String GetChangePasswordError(String email, String oldPassword, String newPassword) {
        Account account = GetAccountByEmail(email);
        if (account == null) {
            return "Account does not exist.";
        }
        if (!account.getIsActive()) {
            return "Account is inactive.";
        }
        if (oldPassword == null || oldPassword.trim().isEmpty()) {
            return "Old password cannot be empty.";
        }
        if (!oldPassword.equals(account.getPassword())) {
            return "Old password is incorrect.";
        }
        if (oldPassword.equals(newPassword)) {
            return "New password cannot be the same as old password.";
        }
        return validationService.getPasswordValidationMessage(newPassword);
    }

    @Override
    public Boolean DeleteAccount(String email) {
        String error = GetDeleteAccountError(email);
        if (error != null) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            Account account = accountDao.findByEmail(connection, email);
            return accountDao.delete(connection, account.getAccountId());
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public String GetDeleteAccountError(String email) {
        Account account = GetAccountByEmail(email);
        if (account == null) {
            return "Account does not exist.";
        }
        if (account.getIsAdmin()) {
            return "Admin account cannot be deleted.";
        }
        return null;
    }

    @Override
    public Boolean InactiveAccount(String email) {
        String error = GetInactiveAccountError(email);
        if (error != null) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            Account account = accountDao.findByEmail(connection, email);
            accountDao.deactivate(connection, account.getAccountId());
            transactionDao.insert(connection, createTransaction(account, "ACCOUNT_INACTIVE", 0,
                    null, "Account was deactivated."));
            connection.commit();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public String GetInactiveAccountError(String email) {
        Account account = GetAccountByEmail(email);
        if (account == null) {
            return "Account does not exist.";
        }
        if (account.getIsAdmin()) {
            return "Admin account cannot be inactive.";
        }
        if (!account.getIsActive()) {
            return "Account is already inactive.";
        }
        return null;
    }

    @Override
    public String GetAdminUpdateAccountError(String currentEmail, String email, String phoneNumber,
            int age, double balance, boolean isActive) {
        Account account = GetAccountByEmail(currentEmail);
        if (account == null) {
            return "Account does not exist.";
        }
        if (account.getIsAdmin()) {
            return "Administrator accounts cannot be edited here.";
        }
        if (!Double.isFinite(balance) || balance < 0) {
            return "Balance must be zero or greater.";
        }

        String error = validationService.getEmailValidationMessage(email);
        if (error != null) {
            return error;
        }
        error = validationService.getPhoneNumberValidationMessage(phoneNumber);
        if (error != null) {
            return error;
        }
        error = validationService.getAgeValidationMessage(age);
        if (error != null) {
            return error;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            Account sameEmail = accountDao.findByEmail(connection, email);
            if (sameEmail != null && sameEmail.getAccountId() != account.getAccountId()) {
                return "Email already exists.";
            }
            Account samePhone = accountDao.findByPhoneNumber(connection, phoneNumber);
            if (samePhone != null && samePhone.getAccountId() != account.getAccountId()) {
                return "Phone number already exists.";
            }
        } catch (SQLException exception) {
            return "Cannot connect to the database.";
        }

        return null;
    }

    @Override
    public Boolean UpdateAccountByAdmin(String currentEmail, String email, String phoneNumber,
            int age, double balance, boolean isActive) {
        String error = GetAdminUpdateAccountError(currentEmail, email, phoneNumber, age, balance, isActive);
        if (error != null) {
            return false;
        }
        Account account = GetAccountByEmail(currentEmail);
        try (Connection connection = DatabaseConnection.getConnection()) {
            return accountDao.updateByAdmin(connection, account.getAccountId(), email.trim(), phoneNumber.trim(),
                    age, balance, isActive);
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public List<WalletCard> GetCards(String email) {
        Account account = GetAccountByEmail(email);
        if (account == null) { return new ArrayList<>(); }
        try (Connection connection = DatabaseConnection.getConnection()) {
            return walletCardDao.findByAccountId(connection, account.getAccountId());
        } catch (SQLException exception) {
            return new ArrayList<>();
        }
    }

    @Override
    public String GetAddCardError(String email, String bankName, String cardHolderName,
            String cardNumber, String expiryMonth, String expiryYear, String cvv) {
        if (GetAccountByEmail(email) == null) { return "Account does not exist."; }
        if (bankName == null || bankName.trim().isEmpty() || cardHolderName == null || cardHolderName.trim().isEmpty()) {
            return "Bank name and card holder name are required.";
        }
        String digits = cardNumber == null ? "" : cardNumber.replaceAll("\\s+", "");
        if (!digits.matches("\\d{16}")) { return "Card number must contain exactly 16 digits."; }
        if (cvv == null || !cvv.matches("\\d{3}")) { return "Security code must contain exactly 3 digits."; }
        try {
            int month = Integer.parseInt(expiryMonth);
            int year = Integer.parseInt(expiryYear);
            if (month < 1 || month > 12 || year < 2026) { return "Enter a valid expiry date."; }
            Account account = GetAccountByEmail(email);
            try (Connection connection = DatabaseConnection.getConnection()) {
                if (walletCardDao.existsByFingerprint(connection, account.getAccountId(), getCardFingerprint(digits))) {
                    return "This card is already added to your wallet.";
                }
            }
        } catch (NumberFormatException exception) {
            return "Enter a valid expiry date.";
        } catch (SQLException exception) {
            return "Cannot connect to the database.";
        }
        return null;
    }

    @Override
    public Boolean AddCard(String email, String bankName, String cardHolderName,
            String cardNumber, String expiryMonth, String expiryYear, String cvv) {
        String error = GetAddCardError(email, bankName, cardHolderName, cardNumber, expiryMonth, expiryYear, cvv);
        if (error != null) { return false; }
        String digits = cardNumber.replaceAll("\\s+", "");
        Account account = GetAccountByEmail(email);
        WalletCard card = new WalletCard();
        card.setAccountId(account.getAccountId());
        card.setBankName(bankName.trim());
        card.setCardHolderName(cardHolderName.trim());
        card.setLastFourDigits(digits.substring(12));
        card.setExpiryMonth(Integer.parseInt(expiryMonth));
        card.setExpiryYear(Integer.parseInt(expiryYear));
        try (Connection connection = DatabaseConnection.getConnection()) {
            return walletCardDao.insert(connection, card, getCardFingerprint(digits));
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public Boolean DeleteCard(String email, int cardId) {
        Account account = GetAccountByEmail(email);
        if (account == null) { return false; }
        try (Connection connection = DatabaseConnection.getConnection()) {
            return walletCardDao.delete(connection, account.getAccountId(), cardId);
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public String GetDepositError(String email, int cardId, double amount) {
        String error = GetDepositError(email, amount);
        return error == null ? getCardOperationError(email, cardId) : error;
    }

    @Override
    public Boolean Deposit(String email, int cardId, double amount) {
        return GetDepositError(email, cardId, amount) == null && Deposit(email, amount);
    }

    @Override
    public String GetWithdrawError(String email, int cardId, double amount) {
        String error = GetWithdrawError(email, amount);
        return error == null ? getCardOperationError(email, cardId) : error;
    }

    @Override
    public Boolean Withdraw(String email, int cardId, double amount) {
        return GetWithdrawError(email, cardId, amount) == null && Withdraw(email, amount);
    }

    @Override
    public String GetUpdateProfileError(String email, String phoneNumber, int age) {
        String error = validationService.getPhoneNumberValidationMessage(phoneNumber);
        if (error != null) { return error; }
        error = validationService.getAgeValidationMessage(age);
        if (error != null) { return error; }
        Account account = GetAccountByEmail(email);
        if (account == null) { return "Account does not exist."; }
        try (Connection connection = DatabaseConnection.getConnection()) {
            Account samePhone = accountDao.findByPhoneNumber(connection, phoneNumber);
            if (samePhone != null && samePhone.getAccountId() != account.getAccountId()) { return "Phone number already exists."; }
        } catch (SQLException exception) { return "Cannot connect to the database."; }
        return null;
    }

    @Override
    public Boolean UpdateProfile(String email, String phoneNumber, int age) {
        if (GetUpdateProfileError(email, phoneNumber, age) != null) { return false; }
        Account account = GetAccountByEmail(email);
        try (Connection connection = DatabaseConnection.getConnection()) {
            return accountDao.updateProfile(connection, account.getAccountId(), phoneNumber, age);
        } catch (SQLException exception) { return false; }
    }

    private String getCardOperationError(String email, int cardId) {
        Account account = GetAccountByEmail(email);
        if (account == null) { return "Account does not exist."; }
        try (Connection connection = DatabaseConnection.getConnection()) {
            return walletCardDao.findById(connection, account.getAccountId(), cardId) == null
                    ? "Select one of your saved cards first." : null;
        } catch (SQLException exception) { return "Cannot connect to the database."; }
    }

    private String getCardFingerprint(String cardNumber) {
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(cardNumber.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte value : hash) { result.append(String.format("%02x", value)); }
            return result.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("Card verification is unavailable.", exception);
        }
    }
}
