package com.argiculturre.config;

import io.github.cdimascio.dotenv.Dotenv;
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
        // Afficher le répertoire de travail pour debug
        String userDir = System.getProperty("user.dir");
        System.out.println("Working Directory: " + userDir);

        // Charger Dotenv avec le chemin absolu
        Dotenv dotenv = Dotenv.configure()
                .directory(userDir)  // Utiliser le répertoire courant
                .ignoreIfMissing()  // Ne pas ignorer si manquant
                .load();

        this.jdbcUrl = dotenv.get("JDBC_URL");
        this.username = dotenv.get("DB_USER");
        this.password = dotenv.get("PASSWORD");

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