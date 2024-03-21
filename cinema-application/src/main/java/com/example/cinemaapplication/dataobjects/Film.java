package com.example.cinemaapplication.dataobjects;

import jakarta.persistence.*;


@Entity
@Table(name="Filmid")
public class Film {
    @Id
    @GeneratedValue
    private long id;

    private String pealkiri;

    private String kestus;

    private String zanr;

    @Transient
    private String pilt;

    public Film(String pealkiri, String kestus, String zanr, String pilt) {
        this.id = id;
        this.pealkiri = pealkiri;
        this.kestus = kestus;
        this.zanr = zanr;
        this.pilt = pilt;
    }

    public Film() {

    }

    public long getId() {
        return id;
    }

    public String getPealkiri() {
        return pealkiri;
    }

    public String getKestus() {
        return kestus;
    }


    public String getZanr() {
        return zanr;
    }


    public String getPilt() {
        return pilt;
    }

    public void setPealkiri(String pealkiri) {
        this.pealkiri = pealkiri;
    }

    public void setKestus(String kestus) {
        this.kestus = kestus;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public void setPilt(String pilt) {
        this.pilt = pilt;
    }

    @Override
    public String toString() {
        return "Film{" + "id=" + id + ", pealkiri=" + pealkiri + ", kestus=" + kestus + ", zanr=" + zanr;

    }
}

