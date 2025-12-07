package org.example.repository;

import org.example.model.Doctor;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DoctorRepository {
    private final Connection connection;

    public DoctorRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    //Adauga doctor nou
    public void adaugaDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctori (nume, specializare, program) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, doctor.getNume());
            stmt.setString(2, doctor.getSpecializare());
            stmt.setString(3, doctor.getProgram());
            stmt.executeUpdate();

            // preluăm ID-ul generat automat
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                doctor.setId(keys.getInt(1));
            }
            System.out.println("Doctor adaugat cu succes! ID generat: " + doctor.getId());
        } catch (SQLException e) {
            System.out.println("Eroare la adaugare doctor: " + e.getMessage());
        }
    }

    //Obtinem lista completa de doctori
    public List<Doctor> getTotiDoctorii() {
        List<Doctor> lista = new ArrayList<>();
        String sql = "SELECT * FROM doctori";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("specializare"),
                        rs.getString("program")
                );
                lista.add(d);
            }

        } catch (SQLException e) {
            System.out.println("Eroare la citire doctori: " + e.getMessage());
        }

        return lista;
    }

    //Actualizam programul unui doctor
    public void actualizeazaProgram(int id, String programNou) {
        String sql = "UPDATE doctori SET program = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, programNou);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Program actualizat pentru doctorul cu ID " + id);
        } catch (SQLException e) {
            System.out.println("Eroare la actualizare doctor: " + e.getMessage());
        }
    }

    //Sterge un doctor
    public void stergeDoctor(int id) {
        String sql = "DELETE FROM doctori WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Doctor șters (ID: " + id + ")");
        } catch (SQLException e) {
            System.out.println("Eroare la ștergere doctor: " + e.getMessage());
        }
    }

    //Cauta un doctor dupa ID
    public Doctor cautaDupaId(int id) {
        String sql = "SELECT * FROM doctori WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Doctor(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("specializare"),
                        rs.getString("program")
                );
            }
        } catch (SQLException e) {
            System.out.println("Eroare la cautare doctor: " + e.getMessage());
        }
        return null;
    }
}
