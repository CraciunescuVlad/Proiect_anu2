package org.example;

import org.example.model.*;
import org.example.service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // === Inițializări servicii ===
        String pacientPath = "src/main/resources/data/pacienti.csv";
        String doctorPath = "src/main/resources/data/doctori.csv";
        String programariPath = "src/main/resources/data/programari.csv";
        String asocierePath = "src/main/resources/data/asocieri_doctor_pacient.csv";

        PacientService pacientService = new PacientService(pacientPath);
        DoctorService doctorService = new DoctorService(doctorPath);
        ProgramareService programareService = new ProgramareService(programariPath, pacientService, doctorService);
        DoctorPacientService doctorPacientService = new DoctorPacientService(asocierePath, doctorService, pacientService);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENIU PRINCIPAL =====");
            System.out.println("1. Gestionare PACIENȚI");
            System.out.println("2. Gestionare DOCTORI");
            System.out.println("3. Gestionare PROGRAMĂRI");
            System.out.println("4. Asocieri DOCTOR-PACIENT");
            System.out.println("0. Ieșire");
            System.out.print("Alege opțiunea: ");
            int opt = scanner.nextInt();
            scanner.nextLine(); // curățare buffer

            switch (opt) {
                case 1 -> meniuPacienti(scanner, pacientService);
                case 2 -> meniuDoctori(scanner, doctorService);
                case 3 -> meniuProgramari(scanner, programareService);
                case 4 -> meniuAsocieri(scanner, doctorPacientService);
                case 0 -> {
                    System.out.println("La revedere!");
                    return;
                }
                default -> System.out.println("Opțiune invalidă!");
            }
        }
    }

    // -------------------- PACIENȚI --------------------
    private static void meniuPacienti(Scanner scanner, PacientService pacientService) {
        while (true) {
            System.out.println("\n=== Meniu Pacienți ===");
            System.out.println("1. Afișează toți pacienții");
            System.out.println("2. Adaugă pacient");
            System.out.println("3. Caută pacient după ID");
            System.out.println("4. Șterge pacient");
            System.out.println("5. Actualizează telefon");
            System.out.println("6. Caută pacienți după nume/prenume");
            System.out.println("0. Înapoi");
            System.out.print("Alege: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1 -> pacientService.getTot().forEach(System.out::println);
                case 2 -> {
                    System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Nume: "); String nume = scanner.nextLine();
                    System.out.print("Prenume: "); String prenume = scanner.nextLine();
                    System.out.print("CNP: "); String cnp = scanner.nextLine();
                    System.out.print("Telefon: "); String telefon = scanner.nextLine();
                    pacientService.adaugaPacient(new Pacient(id, nume, prenume, cnp, telefon));
                }
                case 3 -> {
                    System.out.print("ID: "); int id = scanner.nextInt();
                    Pacient p = pacientService.cautaDupaId(id);
                    System.out.println(p != null ? p : "Pacient inexistent!");
                }
                case 4 -> {
                    System.out.print("ID: "); int id = scanner.nextInt();
                    if (pacientService.stergePacient(id)) System.out.println("Șters cu succes!");
                    else System.out.println("Pacient inexistent!");
                }
                case 5 -> {
                    System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Telefon nou: "); String tel = scanner.nextLine();
                    if (pacientService.actualizeazaTelefon(id, tel)) System.out.println("Actualizat!");
                    else System.out.println("Pacient inexistent!");
                }
                case 6 -> {
                    System.out.print("Introduceți numele sau prenumele: ");
                    String nume = scanner.nextLine();
                    var rezultate = pacientService.cautaDupaNume(nume);
                    if (rezultate.isEmpty()) System.out.println("Nu s-au găsit pacienți!");
                    else rezultate.forEach(System.out::println);
                }
                case 0 -> { return; }
                default -> System.out.println("Opțiune invalidă!");
            }
        }
    }

    // -------------------- DOCTORI --------------------
    private static void meniuDoctori(Scanner scanner, DoctorService doctorService) {
        while (true) {
            System.out.println("\n=== Meniu Doctori ===");
            System.out.println("1. Afișează toți doctorii");
            System.out.println("2. Adaugă doctor");
            System.out.println("3. Caută doctor după ID");
            System.out.println("4. Șterge doctor");
            System.out.println("5. Actualizează program");
            System.out.println("6. Cauta doctori dupa specializare");
            System.out.println("0. Înapoi");
            System.out.print("Alege: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1 -> doctorService.getTot().forEach(System.out::println);
                case 2 -> {
                    System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Nume: "); String nume = scanner.nextLine();
                    System.out.print("Specializare: "); String sp = scanner.nextLine();
                    System.out.print("Program: "); String prog = scanner.nextLine();
                    doctorService.adaugaDoctor(new Doctor(id, nume, sp, prog));
                }
                case 3 -> {
                    System.out.print("ID: "); int id = scanner.nextInt();
                    Doctor d = doctorService.cautaDupaId(id);
                    System.out.println(d != null ? d : "Doctor inexistent!");
                }
                case 4 -> {
                    System.out.print("ID: "); int id = scanner.nextInt();
                    if (doctorService.stergeDoctor(id)) System.out.println("Șters cu succes!");
                    else System.out.println("Doctor inexistent!");
                }
                case 5 -> {
                    System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Program nou: "); String prog = scanner.nextLine();
                    if (doctorService.actualizeazaProgram(id, prog)) System.out.println("Actualizat!");
                    else System.out.println("Doctor inexistent!");
                }
                case 6 -> {
                    System.out.print("Introduceți specializarea: ");
                    String spec = scanner.nextLine();
                    var rezultate = doctorService.cautaDupaSpecializare(spec);
                    if (rezultate.isEmpty()) System.out.println("Nu s-au găsit doctori!");
                    else rezultate.forEach(System.out::println);
                }
                case 0 -> { return; }
                default -> System.out.println("Opțiune invalidă!");
            }
        }
    }

    // -------------------- PROGRAMĂRI --------------------
    private static void meniuProgramari(Scanner scanner, ProgramareService programareService) {
        while (true) {
            System.out.println("\n=== Meniu Programări ===");
            System.out.println("1. Afișează toate programările");
            System.out.println("2. Adaugă programare");
            System.out.println("3. Caută programare după ID");
            System.out.println("4. Marchează ca efectuată");
            System.out.println("5. Șterge programare");
            System.out.println("0. Înapoi");
            System.out.print("Alege: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1 -> programareService.getTot().forEach(System.out::println);
                case 2 -> {
                    System.out.print("ID: "); int id = scanner.nextInt();
                    System.out.print("ID pacient: "); int pid = scanner.nextInt();
                    System.out.print("ID doctor: "); int did = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Data (yyyy-MM-dd HH:mm): "); String data = scanner.nextLine();
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(data.replace(" ", "T"));
                        Programare p = new Programare(id, pid, did, dateTime, false);
                        if (programareService.adaugaProgramare(p)) System.out.println("Programare adăugată!");
                    } catch (Exception e) {
                        System.out.println("Format invalid! Exemplu corect: 2025-11-20 10:30");
                    }
                }
                case 3 -> {
                    System.out.print("ID: "); int id = scanner.nextInt();
                    Programare p = programareService.cautaDupaId(id);
                    System.out.println(p != null ? p : "Programare inexistentă!");
                }
                case 4 -> {
                    System.out.print("ID: "); int id = scanner.nextInt();
                    if (programareService.marcheazaCaEfectuata(id)) System.out.println("Marcată ca efectuată!");
                    else System.out.println("Programare inexistentă!");
                }
                case 5 -> {
                    System.out.print("ID: "); int id = scanner.nextInt();
                    if (programareService.stergeProgramare(id)) System.out.println("Ștearsă cu succes!");
                    else System.out.println("Programare inexistentă!");
                }
                case 0 -> { return; }
                default -> System.out.println("Opțiune invalidă!");
            }
        }
    }

    // -------------------- ASOCIERI DOCTOR-PACIENT --------------------
    private static void meniuAsocieri(Scanner scanner, DoctorPacientService doctorPacientService) {
        while (true) {
            System.out.println("\n=== Meniu Asocieri Doctor-Pacient ===");
            System.out.println("1. Afișează toate asocierile");
            System.out.println("2. Asignează pacient unui doctor");
            System.out.println("3. Afișează pacienții unui doctor");
            System.out.println("0. Înapoi");
            System.out.print("Alege: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1 -> doctorPacientService.afiseazaToate();
                case 2 -> {
                    System.out.print("ID doctor: "); int did = scanner.nextInt();
                    System.out.print("ID pacient: "); int pid = scanner.nextInt();
                    doctorPacientService.asigneazaPacient(did, pid);
                }
                case 3 -> {
                    System.out.print("ID doctor: "); int did = scanner.nextInt();
                    System.out.println("Pacienții doctorului " + did + ":");
                    for (int pid : doctorPacientService.getPacientiAiDoctorului(did)) {
                        System.out.println("Pacient ID: " + pid);
                    }
                }
                case 0 -> { return; }
                default -> System.out.println("Opțiune invalidă!");
            }
        }
    }

}
