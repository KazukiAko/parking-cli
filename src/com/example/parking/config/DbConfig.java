package com.example.parking.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConfig {

    private static final Path CONFIG_PATH = Paths.get("config", "db.properties");
    private static final Properties PROPS = loadProperties();

    private DbConfig() {
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        if (!Files.exists(CONFIG_PATH)) {
            return props;
        }
        try (InputStream in = Files.newInputStream(CONFIG_PATH)) {
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("DB設定ファイルの読み込みに失敗しました: " + CONFIG_PATH, e);
        }
        return props;
    }

    private static String getSetting(String key, String defaultValue) {
        String value = PROPS.getProperty(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return value.trim();
    }

    public static Connection getConnection() throws SQLException {
        String url = getSetting("db.url", "jdbc:postgresql://localhost:5432/parking_cli_db");
        String user = getSetting("db.user", "postgres");
        String password = getSetting("db.password", "");
        return DriverManager.getConnection(url, user, password);
    }
}