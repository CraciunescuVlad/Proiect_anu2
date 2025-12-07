package org.example;

import org.example.util.DatabaseConnection;
import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ Conexiunea funcționează corect!");
        } else {
            System.out.println("❌ Nu s-a putut stabili conexiunea!");
        }
    }
}
