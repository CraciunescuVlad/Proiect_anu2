package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // 🔹 Datele de conectare la baza ta Supabase
    private static final String URL = "jdbc:postgresql://db-pool.ibcvzjwaqhnbpfvxlfpz.supabase.co:6543/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Craciunescu2006"; // parola aleasă la crearea proiectului Supabase

    // 🔹 Obiectul Connection (unic pentru întreaga aplicație)
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexiune la baza de date realizată cu succes!");
            } catch (SQLException e) {
                System.out.println("❌ Eroare la conectare: " + e.getMessage());
            }
        }
        return connection;
    }
}
