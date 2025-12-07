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

    public Doctor(String nume, String specializare, String program) {
        this(0, nume, specializare, program);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Integer> getPacienti() {
        return pacienti;
    }

    public void setPacienti(List<Integer> pacienti) {
        this.pacienti = pacienti;
    }

    @Override
    public String toString() {
        return id + "," + nume + "," + specializare + "," + program + "," + pacienti;
    }
}
