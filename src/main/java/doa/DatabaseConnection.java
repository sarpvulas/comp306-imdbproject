package doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/MovieDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "kaan";

    private Connection connection = null;

    public DatabaseConnection() {
        try {
            connectToDatabase();
        } catch (SQLException e) {
            handleSQLException(e, "Error connecting to the database.");
        }
    }

    public boolean isAppInUse(String apiKey) {
        try {
            String query = "SELECT in_use FROM user_sessions WHERE api_key = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, apiKey);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getBoolean("in_use");
                }
            }
        } catch (SQLException e) {
            handleSQLException(e, "Error checking if the app is in use.");
        }
        return false;
    }

    public void releaseConnection(String apiKey) {
        try {
            String query = "UPDATE user_sessions SET in_use = false WHERE api_key = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, apiKey);
                statement.executeUpdate();
            }

            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            handleSQLException(e, "Error releasing the connection.");
        }
    }

    private void connectToDatabase() throws SQLException {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the MySQL database.");
        } catch (SQLException e) {
            handleSQLException(e, "Error connecting to the database.");
            throw e; // Re-throw the exception to signal the failure to the caller
        }
    }

    private void handleSQLException(SQLException e, String message) {
        System.err.println(message);
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
        e.printStackTrace();
    }
}

