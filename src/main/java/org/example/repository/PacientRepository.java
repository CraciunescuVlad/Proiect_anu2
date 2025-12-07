package org.example.repository;

import org.example.model.Pacient;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacientRepository {
    private Connection connection;

    public PacientRepository(){
        this.connection = DatabaseConnection.getConnection();
    }

    //Adaugam pacient
    public void adaugaPacient(Pacient pacient){
        String sql = "INSERT INTO pacienti VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pacient.getId());
            stmt.setString(2, pacient.getNume());
            stmt.setString(3, pacient.getPrenume());
            stmt.setString(4, pacient.getCnp());
            stmt.setString(5, pacient.getTelefon());
            stmt.executeUpdate();
            System.out.println("Pacient adaugat cu succes");
        } catch (SQLException e) {
            System.out.println("Eroare la adaugare pacient: " + e.getMessage());
        }
    }

    //Citeste toti pacientii
    public List<Pacient> getTotiPacientii(){
        List<Pacient> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacienti";
        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Pacient p = new Pacient(rs.getInt("id"), rs.getString("nume"), rs.getString("prenume"), rs.getString("cnp"), rs.getString("telefon"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Eroare la citire pacient: " + e.getMessage());
        }
        return lista;
    }

    //Actualizeaza un pacient
    public void actualizeazaPacient(int id, String telefonNou){
        String sql = "UPDATE pacienti SET telefon = ? WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, telefonNou);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Telefon actualizat");
        } catch (SQLException e) {
            System.out.println("Eroare la actualizare: " + e.getMessage());
        }
    }

    //Sterge un pacient
    public void stergePacient(int id){
        String sql = "DELETE FROM pacienti WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Pacient sters");
        } catch (SQLException e) {
            System.out.println("Eroare la stergere: " + e.getMessage());
        }
    }

    //Cauta un pacient dupa ID
    public Pacient cautaDupaId(int id) {
        String sql = "SELECT * FROM pacienti WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pacient(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getString("cnp"),
                        rs.getString("telefon")
                );
            }
        } catch (SQLException e) {
            System.out.println("Eroare la cautare pacient: " + e.getMessage());
        }
        return null;
    }
}
