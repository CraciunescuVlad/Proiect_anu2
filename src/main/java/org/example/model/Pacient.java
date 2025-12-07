package org.example.model;

public class Pacient {
    private int id;
    private String nume;
    private String prenume;
    private String cnp;
    private String telefon;

    public Pacient(int id, String nume, String prenume, String cnp, String telefon) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.telefon = telefon;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public String getTelefon() {
        return telefon;
    }

    @Override
    public String toString() {
        return id + "," + nume + "," + prenume + "," + cnp + "," + telefon;
    }
}
