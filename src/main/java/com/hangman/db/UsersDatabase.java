package com.hangman.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDatabase {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:4500/hagman_db";
    private static final String DATABASE_USER = "henglint";
    private static final String DATABASE_PASSWORD = "securepass";

    private static final String TABLE_NAME = "users";
    private static final String COLUMN_PRIMARY_KEY = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_TIMESTAMP = "timestamp";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_TIME = "time";

    private static final int PAGE_SIZE = 10;

    public static void createTableIfNotExists() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTO_INCREMENT, %s VARCHAR(255) NOT NULL, %s TIMESTAMP DEFAULT CURRENT_TIMESTAMP, %s INT, %s INT)",
                    TABLE_NAME, COLUMN_PRIMARY_KEY, COLUMN_USERNAME, COLUMN_TIMESTAMP, COLUMN_SCORE, COLUMN_TIME);
            statement.executeUpdate(sql);
        }
    }

    public static void printUsersByScore(int page) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        String.format("SELECT %s, %s, %s, %s FROM %s ORDER BY %s DESC LIMIT ? OFFSET ?",
                                COLUMN_USERNAME, COLUMN_TIMESTAMP, COLUMN_SCORE, COLUMN_TIME, TABLE_NAME,
                                COLUMN_SCORE))) {
            statement.setInt(1, PAGE_SIZE);
            statement.setInt(2, PAGE_SIZE * page);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString(COLUMN_USERNAME);
                    Timestamp timestamp = resultSet.getTimestamp(COLUMN_TIMESTAMP);
                    int score = resultSet.getInt(COLUMN_SCORE);
                    int time = resultSet.getInt(COLUMN_TIME);
                    System.out.printf("%s %s %d %d%n", username, timestamp.toString(), score, time);
                }
            }
        }
    }

    public static void addUser(String username, int score, int time) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
                                TABLE_NAME, COLUMN_USERNAME, COLUMN_SCORE, COLUMN_TIME))) {
            statement.setString(1, username);
            statement.setInt(2, score);
            statement.setInt(3, time);
            statement.executeUpdate();
        }
    }

    public static List<User> listUsersByScore(int page) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        String.format("SELECT %s, %s, %s, %s FROM %s ORDER BY %s DESC LIMIT ? OFFSET ?",
                                COLUMN_USERNAME, COLUMN_TIMESTAMP, COLUMN_SCORE, COLUMN_TIME, TABLE_NAME,
                                COLUMN_SCORE))) {
            statement.setInt(1, PAGE_SIZE);
            statement.setInt(2, PAGE_SIZE * page);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString(COLUMN_USERNAME);
                    Timestamp timestamp = resultSet.getTimestamp(COLUMN_TIMESTAMP);
                    int score = resultSet.getInt(COLUMN_SCORE);
                    int time = resultSet.getInt(COLUMN_TIME);
                    User user = new User(username, timestamp, score, time);
                    users.add(user);
                }
            }
        }
        return users;
    }
}
