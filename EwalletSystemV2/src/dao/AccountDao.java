package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Account;

public class AccountDao {

    public Account findByEmail(Connection connection, String email) throws SQLException {
        String sql = "SELECT * FROM WALLET_ACCOUNTS WHERE EMAIL = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return createAccount(result);
                }
            }
        }

        return null;
    }

    public Account findByPhoneNumber(Connection connection, String phoneNumber) throws SQLException {
        String sql = "SELECT * FROM WALLET_ACCOUNTS WHERE PHONE_NUMBER = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phoneNumber);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return createAccount(result);
                }
            }
        }

        return null;
    }

    public List<Account> findAll(Connection connection) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM WALLET_ACCOUNTS ORDER BY ACCOUNT_ID";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                accounts.add(createAccount(result));
            }
        }

        return accounts;
    }

    public boolean insert(Connection connection, Account account) throws SQLException {
        String sql = "INSERT INTO WALLET_ACCOUNTS "
                + "(EMAIL, USER_PASSWORD, PHONE_NUMBER, AGE, BALANCE, IS_ADMIN, IS_ACTIVE) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getPhoneNumber());
            statement.setInt(4, account.getAge());
            statement.setDouble(5, account.getBalance());
            statement.setInt(6, account.getIsAdmin() ? 1 : 0);
            statement.setInt(7, account.getIsActive() ? 1 : 0);
            return statement.executeUpdate() == 1;
        }
    }

    public boolean updateBalance(Connection connection, int accountId, double balance) throws SQLException {
        String sql = "UPDATE WALLET_ACCOUNTS SET BALANCE = ? WHERE ACCOUNT_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, balance);
            statement.setInt(2, accountId);
            return statement.executeUpdate() == 1;
        }
    }

    public boolean updatePassword(Connection connection, int accountId, String password) throws SQLException {
        String sql = "UPDATE WALLET_ACCOUNTS SET USER_PASSWORD = ? WHERE ACCOUNT_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, password);
            statement.setInt(2, accountId);
            return statement.executeUpdate() == 1;
        }
    }

    public boolean updateProfile(Connection connection, int accountId, String phoneNumber, int age) throws SQLException {
        String sql = "UPDATE WALLET_ACCOUNTS SET PHONE_NUMBER = ?, AGE = ? WHERE ACCOUNT_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phoneNumber);
            statement.setInt(2, age);
            statement.setInt(3, accountId);
            return statement.executeUpdate() == 1;
        }
    }

    public boolean updateByAdmin(Connection connection, int accountId, String email, String phoneNumber,
            int age, double balance, boolean isActive) throws SQLException {
        String sql = "UPDATE WALLET_ACCOUNTS SET EMAIL = ?, PHONE_NUMBER = ?, AGE = ?, BALANCE = ?, IS_ACTIVE = ? "
                + "WHERE ACCOUNT_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, phoneNumber);
            statement.setInt(3, age);
            statement.setDouble(4, balance);
            statement.setInt(5, isActive ? 1 : 0);
            statement.setInt(6, accountId);
            return statement.executeUpdate() == 1;
        }
    }

    public boolean deactivate(Connection connection, int accountId) throws SQLException {
        String sql = "UPDATE WALLET_ACCOUNTS SET IS_ACTIVE = 0 WHERE ACCOUNT_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            return statement.executeUpdate() == 1;
        }
    }

    public boolean delete(Connection connection, int accountId) throws SQLException {
        String sql = "DELETE FROM WALLET_ACCOUNTS WHERE ACCOUNT_ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            return statement.executeUpdate() == 1;
        }
    }

    private Account createAccount(ResultSet result) throws SQLException {
        Account account = new Account();
        account.setAccountId(result.getInt("ACCOUNT_ID"));
        account.setEmail(result.getString("EMAIL"));
        account.setPassword(result.getString("USER_PASSWORD"));
        account.setPhoneNumber(result.getString("PHONE_NUMBER"));
        account.setAge(result.getInt("AGE"));
        account.setBalance(result.getDouble("BALANCE"));
        account.setIsAdmin(result.getInt("IS_ADMIN") == 1);
        account.setIsActive(result.getInt("IS_ACTIVE") == 1);
        return account;
    }
}
