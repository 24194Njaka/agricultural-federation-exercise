package com.argiculturre.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {

    private static String url;
    private static String username;
    private static String password;

    static {
        url = System.getenv("JDBC_URL");
        username = System.getenv("USERNAME");
        password = System.getenv("PASSWORD");

        if (url == null) url = "jdbc:postgresql://localhost:5432/agriculture";
        if (username == null) username = "postgres";
        if (password == null) password = "postgres";
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found", e);
        }
    }
}