package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Transaction;

public class TransactionDao {

    public boolean insert(Connection connection, Transaction transaction) throws SQLException {
        String sql = "INSERT INTO WALLET_TRANSACTIONS "
                + "(ACCOUNT_ID, TRANSACTION_TYPE, AMOUNT, RELATED_EMAIL, DESCRIPTION) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transaction.getAccountId());
            statement.setString(2, transaction.getTransactionType());
            statement.setDouble(3, transaction.getAmount());
            statement.setString(4, transaction.getRelatedEmail());
            statement.setString(5, transaction.getDescription());
            return statement.executeUpdate() == 1;
        }
    }

    public List<Transaction> findByAccountId(Connection connection, int accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM WALLET_TRANSACTIONS WHERE ACCOUNT_ID = ? "
                + "ORDER BY TRANSACTION_ID DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    transactions.add(createTransaction(result));
                }
            }
        }

        return transactions;
    }

    public List<Transaction> findAll(Connection connection) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT T.*, A.EMAIL FROM WALLET_TRANSACTIONS T "
                + "JOIN WALLET_ACCOUNTS A ON T.ACCOUNT_ID = A.ACCOUNT_ID "
                + "ORDER BY T.TRANSACTION_ID DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                Transaction transaction = createTransaction(result);
                transaction.setEmail(result.getString("EMAIL"));
                transactions.add(transaction);
            }
        }

        return transactions;
    }

    private Transaction createTransaction(ResultSet result) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(result.getInt("TRANSACTION_ID"));
        transaction.setAccountId(result.getInt("ACCOUNT_ID"));
        transaction.setTransactionType(result.getString("TRANSACTION_TYPE"));
        transaction.setAmount(result.getDouble("AMOUNT"));
        transaction.setRelatedEmail(result.getString("RELATED_EMAIL"));
        transaction.setDescription(result.getString("DESCRIPTION"));
        transaction.setTransactionDate(result.getTimestamp("TRANSACTION_DATE"));
        return transaction;
    }
}
