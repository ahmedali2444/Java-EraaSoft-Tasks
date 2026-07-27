package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.WalletCard;

public class WalletCardDao {
    public List<WalletCard> findByAccountId(Connection connection, int accountId) throws SQLException {
        List<WalletCard> cards = new ArrayList<>();
        String sql = "SELECT * FROM WALLET_CARDS WHERE ACCOUNT_ID = ? ORDER BY CARD_ID DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) { cards.add(createCard(result)); }
            }
        }
        return cards;
    }

    public WalletCard findById(Connection connection, int accountId, int cardId) throws SQLException {
        String sql = "SELECT * FROM WALLET_CARDS WHERE ACCOUNT_ID = ? AND CARD_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            statement.setInt(2, cardId);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? createCard(result) : null;
            }
        }
    }

    public boolean existsByFingerprint(Connection connection, int accountId, String fingerprint) throws SQLException {
        String sql = "SELECT CARD_ID FROM WALLET_CARDS WHERE ACCOUNT_ID = ? AND CARD_FINGERPRINT = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            statement.setString(2, fingerprint);
            try (ResultSet result = statement.executeQuery()) { return result.next(); }
        }
    }

    public boolean insert(Connection connection, WalletCard card, String fingerprint) throws SQLException {
        String sql = "INSERT INTO WALLET_CARDS (ACCOUNT_ID, BANK_NAME, CARD_HOLDER_NAME, CARD_FINGERPRINT, LAST_FOUR_DIGITS, EXPIRY_MONTH, EXPIRY_YEAR) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, card.getAccountId());
            statement.setString(2, card.getBankName());
            statement.setString(3, card.getCardHolderName());
            statement.setString(4, fingerprint);
            statement.setString(5, card.getLastFourDigits());
            statement.setInt(6, card.getExpiryMonth());
            statement.setInt(7, card.getExpiryYear());
            return statement.executeUpdate() == 1;
        }
    }

    public boolean delete(Connection connection, int accountId, int cardId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM WALLET_CARDS WHERE ACCOUNT_ID = ? AND CARD_ID = ?")) {
            statement.setInt(1, accountId);
            statement.setInt(2, cardId);
            return statement.executeUpdate() == 1;
        }
    }

    private WalletCard createCard(ResultSet result) throws SQLException {
        WalletCard card = new WalletCard();
        card.setCardId(result.getInt("CARD_ID"));
        card.setAccountId(result.getInt("ACCOUNT_ID"));
        card.setBankName(result.getString("BANK_NAME"));
        card.setCardHolderName(result.getString("CARD_HOLDER_NAME"));
        card.setLastFourDigits(result.getString("LAST_FOUR_DIGITS"));
        card.setExpiryMonth(result.getInt("EXPIRY_MONTH"));
        card.setExpiryYear(result.getInt("EXPIRY_YEAR"));
        card.setCreatedAt(result.getTimestamp("CREATED_AT"));
        return card;
    }
}
