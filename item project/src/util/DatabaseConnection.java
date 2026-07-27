package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
    private static final Properties properties = new Properties();
    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
                properties.load(input);
            }
        } catch (Exception exception) {
            throw new RuntimeException("Database configuration could not be loaded.", exception);
        }
    }
    public static Connection getConnection() throws java.sql.SQLException {
        return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));
    }
}
