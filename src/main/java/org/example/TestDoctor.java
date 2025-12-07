package org.example;

import org.example.model.Doctor;
import org.example.repository.DoctorRepository;

public class TestDoctor {
    public static void main(String[] args) {
        DoctorRepository repo = new DoctorRepository();

        // Adaugă doctori
        repo.adaugaDoctor(new Doctor("Dr. Ionescu Mihai", "Cardiologie", "Luni-Vineri 08:00-14:00"));
        repo.adaugaDoctor(new Doctor("Dr. Popa Ana", "Dermatologie", "Luni-Joi 10:00-16:00"));

        // Afișează toți doctorii
        System.out.println("📋 Lista doctorilor:");
        for (Doctor d : repo.getTotiDoctorii()) {
            System.out.println(d);
        }

        // Actualizează programul unui doctor (ID = 1 de exemplu)
        repo.actualizeazaProgram(1, "Luni-Vineri 09:00-15:00");

        // Șterge un doctor (ID = 2 de exemplu)
        // repo.stergeDoctor(2);
    }
}
