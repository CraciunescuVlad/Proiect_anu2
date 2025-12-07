package org.example.repository;

import org.example.model.Programare;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProgramareRepository {

    private final Connection connection;

    public ProgramareRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    //Adauga o programare noua
    public void adaugaProgramare(Programare programare) {
        String sql = "INSERT INTO programari (nume_doctor, nume_pacient, data_programare, interval_orar, efectuata) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, programare.getNumeDoctor());
            stmt.setString(2, programare.getNumePacient());
            stmt.setTimestamp(3, Timestamp.valueOf(programare.getData()));
            stmt.setString(4, programare.getIntervalOrar());
            stmt.setBoolean(5, programare.isEfectuata());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                programare.setId(keys.getInt(1));
            }
            System.out.println("Programare adaugata cu succes! ID generat: " + programare.getId());
        } catch (SQLException e) {
            System.out.println("Eroare la adaugare programare: " + e.getMessage());
        }
    }

    //Afiseaza toate programarile
    public List<Programare> getToateProgramarile() {
        List<Programare> lista = new ArrayList<>();
        String sql = "SELECT * FROM programari";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Programare p = new Programare(
                        rs.getInt("id"),
                        rs.getString("nume_doctor"),
                        rs.getString("nume_pacient"),
                        rs.getTimestamp("data_programare").toLocalDateTime(),
                        rs.getString("interval_orar"),
                        rs.getBoolean("efectuata")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Eroare la citire programari: " + e.getMessage());
        }

        return lista;
    }

    //Marcheaza o programare ca efectuata
    public void marcheazaEfectuata(int id) {
        String sql = "UPDATE programari SET efectuata = true WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Programarea cu ID " + id + " a fost marcata ca efectuata!");
        } catch (SQLException e) {
            System.out.println("Eroare la actualizare: " + e.getMessage());
        }
    }

    //Sterge o programare
    public void stergeProgramare(int id) {
        String sql = "DELETE FROM programari WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("🗑️ Programare stearsă (ID: " + id + ")");
        } catch (SQLException e) {
            System.out.println("Eroare la stergere programare: " + e.getMessage());
        }
    }
}
