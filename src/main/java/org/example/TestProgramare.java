package org.example;

import org.example.model.Programare;
import org.example.repository.ProgramareRepository;

import java.time.LocalDateTime;

public class TestProgramare {
    public static void main(String[] args) {
        ProgramareRepository repo = new ProgramareRepository();

        // 1. Adăugăm programări noi
        repo.adaugaProgramare(new Programare(
                "Dr. Ionescu Mihai",
                "Popescu Andrei",
                LocalDateTime.of(2025, 12, 10, 9, 0),
                "09:00 - 09:30",
                false
        ));

        repo.adaugaProgramare(new Programare(
                "Dr. Popa Ana",
                "Vasilescu Ioana",
                LocalDateTime.of(2025, 12, 11, 10, 30),
                "10:30 - 11:00",
                true
        ));

        // 2. Afișăm toate programările
        System.out.println("\n Lista programarilor:");
        for (var p : repo.getToateProgramarile()) {
            System.out.println(p);
        }

        // 3. Marchez o programare ca efectuată (ex: ID = 1)
        repo.marcheazaEfectuata(1);

        // 4. Șterge o programare (ex: ID = 2)
        // repo.stergeProgramare(2);
    }
}
