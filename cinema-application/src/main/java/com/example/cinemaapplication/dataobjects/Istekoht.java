package com.example.cinemaapplication.dataobjects;

import jakarta.persistence.*;


@Entity
@Table(name="Istekohad")
public class Istekoht {
    @Id
    @GeneratedValue
    private long id;
    private int rida;
    private int kohaNumber;
    private boolean vaba;
    @ManyToOne
    private Saal saal;

    public Istekoht(int rida, int kohaNumber, boolean vaba, Saal saal) {
        this.rida = rida;
        this.kohaNumber = kohaNumber;
        this.vaba = vaba;

    }

    public Istekoht() {

    }


    public int getRida() {
        return rida;
    }

    public int getKoht() {
        return kohaNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRida(int rida) {
        this.rida = rida;
    }

    public void setKoht(int kohaNumber) {
        this.kohaNumber = kohaNumber;
    }


    public long getId() {
        return id;
    }

    public int getKohaNumber() {
        return kohaNumber;
    }

    public boolean isVaba() {
        return vaba;
    }

    public Saal getSaal() {
        return saal;
    }
}

