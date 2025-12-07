//package org.example.service;
//
//import org.example.model.Doctor;
//
//import javax.print.Doc;
//import java.util.*;
//import java.io.*;
//
//public class DoctorService {
//    private final String filePath;
//    private List<Doctor> doctori;
//
//    public DoctorService(String filePath) {
//        this.filePath = filePath;
//        this.doctori = new ArrayList<>();
//        incarcaDinFisier();
//    }
//
//    public void incarcaDinFisier() {
//        doctori.clear();
//        List <String> linii = FileService.readFile(filePath);
//        for (String linie : linii) {
//            String[] parts = linie.split(",");
//            if(parts.length == 4){
//                try{
//                    int id = Integer.parseInt(parts[0]);
//                    Doctor d = new Doctor(id, parts[1], parts[2], parts[3]);
//                    doctori.add(d);
//                } catch (NumberFormatException e) {
//                    System.out.println("Eroare la conversie pentru linia: " + linie);
//                }
//            }
//        }
//    }
//
//    public void salveazaInFisier(){
//        List<String> linii = new ArrayList<>();
//        for (Doctor d : doctori) {
//            linii.add(d.toString());
//        }
//        FileService.writeFile(filePath, linii);
//    }
//
//    public void adaugaDoctor(Doctor d){
//        doctori.add(d);
//        salveazaInFisier();
//    }
//
//    public List<Doctor> getTot(){
//        return new ArrayList<>(doctori);
//    }
//
//    public Doctor cautaDupaId(int id){
//        for (Doctor d : doctori) {
//            if(d.getId() == id){
//                return d;
//            }
//        }
//        return null;
//    }
//
//    public boolean stergeDoctor(int id){
//        boolean removed = doctori.removeIf(d -> d.getId() == id);
//        if(removed){
//            salveazaInFisier();
//        }
//        return removed;
//    }
//
//    public boolean actualizeazaProgram(int id, String nouProgram){
//        for(Doctor d : doctori){
//            if(d.getId() == id){
//                Doctor actualizat = new Doctor(d.getId(), d.getNume(), d.getSpecializare(), nouProgram);
//                doctori.set(doctori.indexOf(d), actualizat);
//                salveazaInFisier();
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public List<Doctor> cautaDupaSpecializare(String text){
//        List<Doctor> rezultate = new ArrayList<>();
//        String query = text.toLowerCase();
//        for(Doctor d : doctori){
//            if (d.getSpecializare().toLowerCase().contains(query)){
//                rezultate.add(d);
//            }
//        }
//        return rezultate;
//    }
//}
