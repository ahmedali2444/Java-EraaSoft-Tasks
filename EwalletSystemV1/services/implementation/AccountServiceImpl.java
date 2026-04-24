package services.implementation;

import models.Account;
import models.EWalletSystem;
import services.AccountService;

public class AccountServiceImpl implements AccountService {

    // Stores the in-memory accounts list used by the wallet system.
    private final EWalletSystem eWalletSystemData = new EWalletSystem();

    @Override
    public Boolean IsAccountExists(Account account) {
        // Any missing login data is treated as an invalid request instead of throwing an exception.
        if (account == null
                || account.getUserName() == null
                || account.getUserName().trim().isEmpty()
                || account.getPassword() == null
                || account.getPassword().trim().isEmpty()) {
            return false;
        }

        return eWalletSystemData.getAccounts().stream().anyMatch(
                acc -> acc.getUserName().equals(account.getUserName()) &&
                acc.getPassword().equals(account.getPassword()));
    }

    @Override
    public Boolean CreateAccount(Account account) {
        // Rejects invalid or incomplete signup requests before accessing the accounts list.
        if (account == null
                || account.getUserName() == null
                || account.getUserName().trim().isEmpty()
                || account.getPassword() == null
                || account.getPassword().trim().isEmpty()
                || account.getPhoneNumber() == null
                || !account.getPhoneNumber().matches("\\d{10,15}")
                || account.getAge() <= 0
                || account.getAge() > 120) {
            return false;
        }

        // Prevents duplicate usernames from being added to the system.
        boolean isUserNameAlreadyExists = eWalletSystemData.getAccounts().stream()
                .anyMatch(acc -> acc.getUserName().equals(account.getUserName()));

        if (isUserNameAlreadyExists) {
            return false;
        }

        eWalletSystemData.getAccounts().add(account);
        return true;
    }
}
