package com.argiculturre.config;


import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    private static PGSimpleDataSource dataSource;

    static {
        dataSource = new PGSimpleDataSource();

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            throw new RuntimeException("Database environment variables are not set properly");
        }

        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
