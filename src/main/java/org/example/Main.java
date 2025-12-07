package org.example;

import org.example.model.*;
import org.example.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Inițializăm repository-urile conectate la baza de date
        PacientRepository pacientRepo = new PacientRepository();
        DoctorRepository doctorRepo = new DoctorRepository();
        ProgramareRepository programareRepo = new ProgramareRepository();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENIU PRINCIPAL =====");
            System.out.println("1. Gestionare PACIENȚI");
            System.out.println("2. Gestionare DOCTORI");
            System.out.println("3. Gestionare PROGRAMĂRI");
            System.out.println("0. Iesire");
            System.out.print("Alege opțiunea: ");
            int opt = scanner.nextInt();
            scanner.nextLine(); // curățare buffer

            switch (opt) {
                case 1 -> meniuPacienti(scanner, pacientRepo);
                case 2 -> meniuDoctori(scanner, doctorRepo);
                case 3 -> meniuProgramari(scanner, programareRepo);
                case 0 -> {
                    System.out.println("La revedere!");
                    return;
                }
                default -> System.out.println("Optiune invalida!");
            }
        }
    }

    // -------------------- PACIENȚI --------------------
    private static void meniuPacienti(Scanner scanner, PacientRepository repo) {
        while (true) {
            System.out.println("\n=== Meniu Pacienti ===");
            System.out.println("1. Afiseaza toti pacientii");
            System.out.println("2. Adauga pacient");
            System.out.println("3. Cauta pacient după ID");
            System.out.println("4. Sterge pacient");
            System.out.println("0. Inapoi");
            System.out.print("Alege: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1 -> repo.getTotiPacientii().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();
                    System.out.print("Prenume: ");
                    String prenume = scanner.nextLine();
                    System.out.print("CNP: ");
                    String cnp = scanner.nextLine();
                    System.out.print("Telefon: ");
                    String telefon = scanner.nextLine();
                    repo.adaugaPacient(new Pacient(nume, prenume, cnp, telefon));
                }
                case 3 -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    Pacient p = repo.cautaDupaId(id);
                    System.out.println(p != null ? p : "Pacient inexistent!");
                }
                case 4 -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    repo.stergePacient(id);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Optiune invalida!");
            }
        }
    }

    // -------------------- DOCTORI --------------------
    private static void meniuDoctori(Scanner scanner, DoctorRepository repo) {
        while (true) {
            System.out.println("\n=== Meniu Doctori ===");
            System.out.println("1. Afișează toți doctorii");
            System.out.println("2. Adaugă doctor");
            System.out.println("3. Caută doctor după ID");
            System.out.println("4. Șterge doctor");
            System.out.println("0. Înapoi");
            System.out.print("Alege: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1 -> repo.getTotiDoctorii().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();
                    System.out.print("Specializare: ");
                    String specializare = scanner.nextLine();
                    System.out.print("Program: ");
                    String program = scanner.nextLine();
                    repo.adaugaDoctor(new Doctor(nume, specializare, program));
                }
                case 3 -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    Doctor d = repo.cautaDupaId(id);
                    System.out.println(d != null ? d : "Doctor inexistent!");
                }
                case 4 -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    repo.stergeDoctor(id);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opțiune invalidă!");
            }
        }
    }

    // -------------------- PROGRAMĂRI --------------------
    private static void meniuProgramari(Scanner scanner, ProgramareRepository repo) {
        while (true) {
            System.out.println("\n=== Meniu Programari ===");
            System.out.println("1. Afiseaza toate programarile");
            System.out.println("2. Adauga programare");
            System.out.println("3. Marcheaza programare ca efectuata");
            System.out.println("4. Sterge programare");
            System.out.println("0. Inapoi");
            System.out.print("Alege: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1 -> repo.getToateProgramarile().forEach(System.out::println);
                case 2 -> {
                    try {
                        System.out.print("Nume doctor: ");
                        String numeDoctor = scanner.nextLine();

                        System.out.print("Nume pacient: ");
                        String numePacient = scanner.nextLine();

                        System.out.print("Data (yyyy-MM-dd HH:mm): ");
                        String data = scanner.nextLine();
                        LocalDateTime dateTime = LocalDateTime.parse(data.replace(" ", "T"));

                        System.out.print("Interval orar (ex: 09:00 - 09:30): ");
                        String interval = scanner.nextLine();

                        Programare p = new Programare(numeDoctor, numePacient, dateTime, interval, false);
                        repo.adaugaProgramare(p);
                    } catch (Exception e) {
                        System.out.println("Format invalid! Exemplu corect: 2025-12-20 09:00");
                    }
                }
                case 3 -> {
                    System.out.print("ID programare: ");
                    int id = scanner.nextInt();
                    repo.marcheazaEfectuata(id);
                }
                case 4 -> {
                    System.out.print("ID programare: ");
                    int id = scanner.nextInt();
                    repo.stergeProgramare(id);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Optiune invalida!");
            }
        }
    }
}
