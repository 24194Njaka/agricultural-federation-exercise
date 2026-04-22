package com.collective.federation.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataSourceConfig {

    public static Connection getConnection() {
        String url = null;
        String user = null;
        String pass = null;

        url = System.getenv("JDBC_URL");
        user = System.getenv("USERNAME");
        pass = System.getenv("PASSWORD");

        if (url == null || user == null || pass == null) {
            try (InputStream is = DataSourceConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (is != null) {
                    Properties prop = new Properties();
                    prop.load(is);

                    if (url == null) url = prop.getProperty("jdbc.url");
                    if (user == null) user = prop.getProperty("jdbc.username");
                    if (pass == null) pass = prop.getProperty("jdbc.password");

                    if (url == null) url = prop.getProperty("spring.datasource.url");
                    if (user == null) user = prop.getProperty("spring.datasource.username");
                    if (pass == null) pass = prop.getProperty("spring.datasource.password");
                }
            } catch (Exception ignored) {
            }
        }

        if (url == null || user == null || pass == null) {
            throw new RuntimeException("Missing DB credentials. Set JDBC_URL, USERNAME, PASSWORD or add jdbc.url/jdbc.username/jdbc.password to application.properties");
        }

        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            throw new RuntimeException("Erreur de connexion DB", e);
        }
    }
}