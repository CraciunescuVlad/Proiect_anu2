package org.example.model;

import java.time.LocalDateTime;

public class Programare {
    private int id;
    private int pacientId;
    private int doctorId;
    private LocalDateTime data;
    private boolean efectuata;

    public Programare(int id, int pacientId, int doctorId, LocalDateTime data, boolean efectuata) {
        this.id = id;
        this.pacientId = pacientId;
        this.doctorId = doctorId;
        this.data = data;
        this.efectuata = efectuata;
    }

    public int getId() {
        return id;
    }

    public int getPacientId() {
        return pacientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getData() {
        return data;
    }

    public boolean isEfectuata() {
        return efectuata;
    }

    @Override
    public String toString() {
        return id + "," + pacientId + "," + doctorId + "," + data + "," + efectuata;
    }
}
