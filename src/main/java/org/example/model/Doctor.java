package org.example.model;
import java.util.*;


public class Doctor {
    private int id;
    private String nume;
    private String specializare;
    private String program;
    private List<Integer> pacienti;

    public Doctor(int id, String nume, String specializare, String program) {
        this.id = id;
        this.nume = nume;
        this.specializare = specializare;
        this.program = program;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getSpecializare() {
        return specializare;
    }

    public String getProgram() {
        return program;
    }

    @Override
    public String toString() {
        return id + "," + nume + "," + specializare + "," + program;
    }
}
