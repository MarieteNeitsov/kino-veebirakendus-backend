package com.example.cinemaapplication.dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * Kujutab istekohta kinorakenduses.
 */
@Entity
@Table(name="Istekohad")
public class Istekoht {
    @Id
    @GeneratedValue
    private long id;
    private int rida;
    private int veerg;
    private int kohaNumber;
    private boolean vaba;
    @ManyToOne
    @JsonIgnore
    private Saal saal;
    private int väärtus; //istekoha skoor, mis nätab kui hea koht vaatajale on
    /**
     * Koostab uue Istekoht objekti antud parameetritega.
     *
     * @param rida istekoha rida
     * @param veerg istekoha veerg
     * @param kohaNumber istekoha number
     * @param vaba kas istekoht on vaba
     * @param saal saal, kus istekoht asub
     */
    public Istekoht(int rida, int veerg,int kohaNumber, boolean vaba, Saal saal) {
        this.rida = rida;
        this.kohaNumber = kohaNumber;
        this.vaba = vaba;
        this.saal= saal;
        this.veerg = veerg;

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

    public boolean getVaba() {
        return vaba;
    }

    public Saal getSaal() {
        return saal;
    }

    public int getVeerg() {
        return veerg;
    }

    public void setVäärtus(int i) {
        this.väärtus = i;
    }
    public int getVäärtus() {
        return väärtus;
    }
}

