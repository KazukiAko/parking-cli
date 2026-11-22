package com.example.parking.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/parking_cli_db";
    private static final String DEFAULT_USER = "postgres";
    private static final String DEFAULT_PASSWORD = "System0";

    private DbConfig() {
    }

    public static Connection getConnection() throws SQLException {
        String url = getEnvOrDefault("PARKING_DB_URL", DEFAULT_URL);
        String user = getEnvOrDefault("PARKING_DB_USER", DEFAULT_USER);
        String password = getEnvOrDefault("PARKING_DB_PASSWORD", DEFAULT_PASSWORD);
        return DriverManager.getConnection(url, user, password);
    }

    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }
}