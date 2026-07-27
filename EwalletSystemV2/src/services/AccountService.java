package services;

import models.Account;
import models.Transaction;
import models.WalletCard;

import java.util.List;

public interface AccountService {

    Boolean CreateAccount(Account account);

    String GetCreateAccountError(Account account);

    void AddTransaction(String email, String transactionType, double amount,
            String relatedEmail, String description);

    Account GetAccountByEmail(String email);

    List<Account> GetAllAccounts();

    List<Transaction> GetTransactionHistory();

    List<Transaction> GetAccountTransactionHistory(String email);

    Boolean IsEmailExists(String email);

    Boolean IsPasswordMatches(Account account);

    Boolean IsAccountActive(String email);

    Boolean Deposit(String email, double amount);

    String GetDepositError(String email, double amount);

    Boolean Withdraw(String email, double amount);

    String GetWithdrawError(String email, double amount);

    Boolean Transfer(String sourceEmail, String recipientIdentifier, double amount);

    String GetTransferError(String sourceEmail, String recipientIdentifier, double amount);

    Boolean ChangePassword(String email, String oldPassword, String newPassword);

    String GetChangePasswordError(String email, String oldPassword, String newPassword);

    Boolean DeleteAccount(String email);

    String GetDeleteAccountError(String email);

    Boolean InactiveAccount(String email);

    String GetInactiveAccountError(String email);

    String GetAdminUpdateAccountError(String currentEmail, String email, String phoneNumber,
            int age, double balance, boolean isActive);

    Boolean UpdateAccountByAdmin(String currentEmail, String email, String phoneNumber,
            int age, double balance, boolean isActive);

    List<WalletCard> GetCards(String email);

    String GetAddCardError(String email, String bankName, String cardHolderName,
            String cardNumber, String expiryMonth, String expiryYear, String cvv);

    Boolean AddCard(String email, String bankName, String cardHolderName,
            String cardNumber, String expiryMonth, String expiryYear, String cvv);

    Boolean DeleteCard(String email, int cardId);

    String GetDepositError(String email, int cardId, double amount);

    Boolean Deposit(String email, int cardId, double amount);

    String GetWithdrawError(String email, int cardId, double amount);

    Boolean Withdraw(String email, int cardId, double amount);

    String GetUpdateProfileError(String email, String phoneNumber, int age);

    Boolean UpdateProfile(String email, String phoneNumber, int age);
}
