package com.example.cinemaapplication.dataobjects;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Kasutajad")
public class Kasutaja {

    @Id
    @GeneratedValue
    private long id;


    private String kasutajanimi;

    private String parool;
    @ManyToMany
    @JoinTable(
            name = "kasutaja_filmid",
            joinColumns = @JoinColumn(name = "kasutaja_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<Film> vaadatudFilmid = new ArrayList<>();

    public Kasutaja(String kasutajanimi, String parool) {
        this.id = id;
        this.kasutajanimi = kasutajanimi;
        this.parool = parool;
    }

    public Kasutaja() {

    }

    public void lisaVaadatudFilm(Film film) {
        vaadatudFilmid.add(film);
    }


    public String getKasutajanimi() {
        return kasutajanimi;
    }

    public String getParool() {
        return parool;
    }

    public void setKasutajanimi(String kasutajanimi) {
        this.kasutajanimi = kasutajanimi;
    }

    public void setParool(String parool) {
        this.parool = parool;
    }

    public long getId() {
        return id;
    }

    public List<Film> getVaadatudFilmid() {
        return vaadatudFilmid;
    }
}

