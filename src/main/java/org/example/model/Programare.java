package org.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Programare {
    private int id;
    private String numeDoctor;
    private String numePacient;
    private LocalDateTime data;
    private String intervalOrar;
    private boolean efectuata;

    public Programare(int id, String numeDoctor, String numePacient, LocalDateTime data, String intervalOrar, boolean efectuata) {
        this.id = id;
        this.numeDoctor = numeDoctor;
        this.numePacient = numePacient;
        this.data = data;
        this.intervalOrar = intervalOrar;
        this.efectuata = efectuata;
    }

    // Constructor pentru inserare (fără id)
    public Programare(String numeDoctor, String numePacient, LocalDateTime data, String intervalOrar, boolean efectuata) {
        this(0, numeDoctor, numePacient, data, intervalOrar, efectuata);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeDoctor() {
        return numeDoctor;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getIntervalOrar() {
        return intervalOrar;
    }

    public boolean isEfectuata() {
        return efectuata;
    }

    public void setEfectuata(boolean efectuata) {
        this.efectuata = efectuata;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "Programare{" +
                "id=" + id +
                ", doctor='" + numeDoctor + '\'' +
                ", pacient='" + numePacient + '\'' +
                ", data=" + data.format(fmt) +
                ", interval='" + intervalOrar + '\'' +
                ", efectuata=" + (efectuata ? "Da" : "Nu") +
                '}';
    }
}
