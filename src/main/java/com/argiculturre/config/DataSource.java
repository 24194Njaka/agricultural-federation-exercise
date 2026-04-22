package com.argiculturre.config;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataSource {

    private final String jdbcUrl;
    private final String username;
    private final String password;

    public DataSource() {
        this.jdbcUrl = "jdbc:postgresql://localhost:5432/agriculture";
        this.username = "doudou";
        this.password = "1622Val++";

        System.out.println("=== DATASOURCE CONFIGURATION ===");
        System.out.println("JDBC_URL: " + jdbcUrl);
        System.out.println("DB_USER: " + username);
        System.out.println("PASSWORD: " + (password != null ? "****" : "null"));
        System.out.println("================================");
    }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Attempting to connect to: " + jdbcUrl);
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection successful!");
            return conn;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL Driver not found", e);
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            throw new RuntimeException("Unable to connect to database: " + e.getMessage(), e);
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection", e);
            }
        }
    }
}