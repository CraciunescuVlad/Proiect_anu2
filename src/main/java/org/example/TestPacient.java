package org.example;

import org.example.model.Pacient;
import org.example.repository.PacientRepository;

public class TestPacient {
    public static void main(String[] args) {
        PacientRepository repo = new PacientRepository();

        // 1. Adaugăm un pacient
        repo.adaugaPacient(new Pacient(10, "Popescu", "Andrei", "5010101123456", "0722000000"));

        // 2. Listăm toți pacienții
        System.out.println("📋 Lista pacienților:");
        for (var p : repo.getTotiPacientii()) {
            System.out.println(p);
        }

        // 3. Actualizăm un pacient (id=1 de exemplu)
        repo.actualizeazaPacient(1, "0733999999");

        // 4. Ștergem un pacient (id=1)
        // repo.stergePacient(1);
    }
}
