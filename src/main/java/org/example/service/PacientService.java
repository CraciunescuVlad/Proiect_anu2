//package org.example.service;
//import org.example.model.Pacient;
//import java.util.*;
//import java.io.*;
//
//public class PacientService {
//    private final String filePath;
//    private List<Pacient> pacienti;
//
//    public PacientService(String filePath) {
//        this.filePath = filePath;
//        this.pacienti = new ArrayList<>();
//        incarcaDinFisier();
//    }
//
//    private void incarcaDinFisier() {
//        pacienti.clear();
//        List<String> linii = FileService.readFile(filePath);
//        for (String linie : linii) {
//            String[] parts = linie.split(",");
//            if (parts.length == 5) {
//                try {
//                    int id = Integer.parseInt(parts[0]);
//                    Pacient p = new Pacient(id, parts[1], parts[2], parts[3], parts[4]);
//                    pacienti.add(p);
//                } catch (NumberFormatException e) {
//                    System.out.println("Eroare la conversie pentru linia: " + linie);
//                }
//            }
//        }
//    }
//
//    private void salveazaInFisier() {
//        List<String> linii = new ArrayList<>();
//        for (Pacient p : pacienti) {
//            linii.add(p.toString());
//        }
//        FileService.writeFile(filePath, linii);
//    }
//
//    public void adaugaPacient(Pacient p) {
//        pacienti.add(p);
//        salveazaInFisier();
//    }
//
//    public List<Pacient> getTot(){
//        return new ArrayList<>(pacienti);
//    }
//
//    public Pacient cautaDupaId(int id){
//        for (Pacient p : pacienti) {
//            if (p.getId() == id) {
//                return p;
//            }
//        }
//        return null;
//    }
//
//    public boolean stergePacient(int id) {
//        boolean removed = pacienti.removeIf(p -> p.getId() == id);
//        if (removed) {
//            salveazaInFisier();
//        }
//        return removed;
//    }
//
//    public boolean actualizeazaTelefon(int id, String nouTelefon){
//        for(Pacient p : pacienti){
//            if(p.getId() == id){
//                Pacient actualizat = new Pacient(p.getId(), p.getNume(), p.getPrenume(), p.getCnp(), nouTelefon);
//                pacienti.set(pacienti.indexOf(p), actualizat);
//                salveazaInFisier();
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public List<Pacient> cautaDupaNume(String text){
//        List<Pacient> rezultate = new ArrayList<>();
//        String query = text.toLowerCase();
//        for(Pacient p : pacienti){
//            if (p.getNume().toLowerCase().contains(query) || p.getPrenume().toLowerCase().contains(query)) {
//                rezultate.add(p);
//            }
//        }
//        return rezultate;
//    }
//}
