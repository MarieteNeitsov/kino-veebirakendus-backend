package com.example.cinemaapplication.dataobjects;

import jakarta.persistence.*;


@Entity
@Table(name="Filmid")
public class Film {
    @Id
    @GeneratedValue
    private long id;

    private String pealkiri;

    private double kestus;

    private String zanr;
    private String vanusepiirang;


    private String pilt;

    public Film(String pealkiri, double kestus, String zanr, String vanusepiirang,String pilt) {
        this.id = id;
        this.pealkiri = pealkiri;
        this.kestus = kestus;
        this.zanr = zanr;
        this.pilt = pilt;
        this.vanusepiirang = vanusepiirang;
    }

    public Film() {

    }

    public long getId() {
        return id;
    }

    public String getPealkiri() {
        return pealkiri;
    }

    public double getKestus() {
        return kestus;
    }

    public String getVanusepiirang() {
        return vanusepiirang;
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

