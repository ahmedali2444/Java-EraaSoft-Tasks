package services;

import models.Account;

/*
 * AccountService Interface
 *
 * This interface defines the contract for account-related operations.
 * Any class that implements this interface must provide the logic
 * for creating an account and checking whether an account already exists.
 */

public interface AccountService {

    /*
     * Creates a new account after validating the received data.
     *
     * Parameter:
     * account -> an Account object containing user data
     *
     * Returns:
     * true  -> if account created successfully
     * false -> if creation failed because of duplicate or invalid data
     */
    Boolean CreateAccount(Account account);

    /*
     * Checks whether an account already exists for login purposes.
     *
     * Parameter:
     * account -> the account we want to search for
     *
     * Returns:
     * true  -> if account exists
     * false -> if account does not exist or the input data is invalid
     */
    Boolean IsAccountExists(Account account);
}
