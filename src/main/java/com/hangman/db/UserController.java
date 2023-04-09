package com.hangman.db;

import java.sql.*;

public class UserController {
    // Declarar variables de conexión y sentencias SQL
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement addUserStmt = null;
    private PreparedStatement getUsersStmt = null;

    // Constructor: conectar a la base de datos y crear la tabla si no existe
    public UserController(String url, String user, String password) throws SQLException {
        // Conectar a la base de datos
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Conexión a la base de datos establecida.");

        // Crear la tabla "users" si no existe
        stmt = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(username VARCHAR(255) NOT NULL, " +
                "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                "score INT, " +
                "time INT, " +
                "PRIMARY KEY (id))";
        stmt.executeUpdate(sql);
        System.out.println("Tabla 'users' creada o actualizada correctamente.");

        // Preparar la consulta para añadir usuarios
        addUserStmt = conn.prepareStatement("INSERT INTO users (username, score, time) VALUES (?, ?, ?)");

        // Preparar la consulta para obtener la lista de usuarios ordenados por score
        getUsersStmt = conn.prepareStatement("SELECT username, score FROM users ORDER BY score DESC");
    }

    // Método para añadir un usuario a la tabla
    public void addUser(String username, int score, int time) throws SQLException {
        addUserStmt.setString(1, username);
        addUserStmt.setInt(2, score);
        addUserStmt.setInt(3, time);
        addUserStmt.executeUpdate();
        System.out.println("Usuario '" + username + "' añadido correctamente.");
    }

    // Método para obtener la lista de usuarios ordenados por score
    public void getUsers() throws SQLException {
        ResultSet rs = getUsersStmt.executeQuery();
        System.out.println("Lista de usuarios ordenados por score:");
        while (rs.next()) {
            String username = rs.getString("username");
            int score = rs.getInt("score");
            System.out.println(username + "\t" + score);
        }
    }

    // Método para cerrar la conexión a la base de datos
    public void close() throws SQLException {
        if (stmt != null)
            stmt.close();
        if (addUserStmt != null)
            addUserStmt.close();
        if (getUsersStmt != null)
            getUsersStmt.close();
        if (conn != null)
            conn.close();
        System.out.println("Conexión a la base de datos cerrada.");
    }
}