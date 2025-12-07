package org.example.service;

import java.util.*;

public class DoctorPacientService {
    private final String filePath;
    private final DoctorService doctorService;
    private final PacientService pacientService;
    private final List<String> asocieri;

    public DoctorPacientService(String filePath, DoctorService doctorService, PacientService pacientService) {
        this.filePath = filePath;
        this.doctorService = doctorService;
        this.pacientService = pacientService;
        this.asocieri = new ArrayList<>(FileService.readFile(filePath));
    }

    // 🔹 Asignează un pacient unui doctor
    public boolean asigneazaPacient(int doctorId, int pacientId) {
        if (doctorService.cautaDupaId(doctorId) == null) {
            System.out.println("Eroare: doctorul cu ID " + doctorId + " nu există!");
            return false;
        }
        if (pacientService.cautaDupaId(pacientId) == null) {
            System.out.println("Eroare: pacientul cu ID " + pacientId + " nu există!");
            return false;
        }
        String linie = doctorId + "," + pacientId;
        if (asocieri.contains(linie)) {
            System.out.println("Această asociere există deja!");
            return false;
        }
        asocieri.add(linie);
        FileService.writeFile(filePath, asocieri);
        System.out.println("Pacientul " + pacientId + " a fost asignat doctorului " + doctorId);
        return true;
    }

    // 🔹 Returnează toți pacienții unui doctor
    public List<Integer> getPacientiAiDoctorului(int doctorId) {
        List<Integer> lista = new ArrayList<>();
        for (String linie : asocieri) {
            String[] parts = linie.split(",");
            if (parts.length == 2 && Integer.parseInt(parts[0]) == doctorId) {
                lista.add(Integer.parseInt(parts[1]));
            }
        }
        return lista;
    }

    // 🔹 Afișează relațiile
    public void afiseazaToate() {
        for (String linie : asocieri) {
            System.out.println(linie);
        }
    }
}
