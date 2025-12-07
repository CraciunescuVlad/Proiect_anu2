package org.example.service;

import org.example.model.Programare;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProgramareService {
    private final String filePath;
    private final PacientService pacientService;
    private final DoctorService doctorService;
    private List<Programare> programari;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ProgramareService(String filePath, PacientService pacientService, DoctorService doctorService) {
        this.filePath = filePath;
        this.pacientService = pacientService;
        this.doctorService = doctorService;
        this.programari = new ArrayList<>();
        incarcaDinFisier();
    }

    private void incarcaDinFisier() {
        programari.clear();
        List<String> linii = FileService.readFile(filePath);
        for (String linie: linii) {
            String[] parts = linie.split(",");
            if(parts.length == 5){
                try{
                    int id = Integer.parseInt(parts[0]);
                    int pacientId = Integer.parseInt(parts[1]);
                    int doctorId = Integer.parseInt(parts[2]);
                    LocalDateTime data = LocalDateTime.parse(parts[3], formatter);
                    boolean efectuata = Boolean.parseBoolean(parts[4]);
                    programari.add(new Programare(id, pacientId, doctorId, data, efectuata));
                } catch (Exception e) {
                    System.out.println("Eroare la parsarea liniei: " + linie);
                }
            }
        }
    }

    private void salveazaInFisier(){
        List<String> linii = new ArrayList<>();
        for (Programare p: programari) {
            linii.add(p.getId() + "," + p.getPacientId() + "," + p.getDoctorId() + "," + p.getData().format(formatter) + "," + p.isEfectuata());
        }
        FileService.writeFile(filePath, linii);
    }

    public boolean adaugaProgramare(Programare programare){
        if (pacientService.cautaDupaId(programare.getPacientId()) == null) {
            System.out.println("Eroare: pacientul cu ID " + programare.getPacientId() + " nu există!");
            return false;
        }
        if (doctorService.cautaDupaId(programare.getDoctorId()) == null) {
            System.out.println("Eroare: doctorul cu ID " + programare.getDoctorId() + " nu există!");
            return false;
        }
        programari.add(programare);
        salveazaInFisier();
        return true;
    }

    public List<Programare> getTot() {
        return new ArrayList<>(programari);
    }

    public Programare cautaDupaId(int id) {
        for (Programare p: programari) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean marcheazaCaEfectuata(int id) {
        for (Programare p : programari) {
            if (p.getId() == id) {
                Programare actualizata = new Programare(p.getId(), p.getPacientId(), p.getDoctorId(), p.getData(), true);
                programari.set(programari.indexOf(p), actualizata);
                salveazaInFisier();
                return true;
            }
        }
        return false;
    }

    public boolean stergeProgramare(int id) {
        boolean removed = programari.removeIf(p -> p.getId() == id);
        if (removed) salveazaInFisier();
        return removed;
    }
}
