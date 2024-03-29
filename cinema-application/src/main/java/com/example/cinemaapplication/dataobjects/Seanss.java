package com.example.cinemaapplication.dataobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Kujutab seanssi kinorakenduses.
 */
@Entity
@Table(name="Seansid")
public class Seanss {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate kuupäev;
    private LocalTime algusAeg;
    private String keel;
    @ManyToOne(fetch = FetchType.EAGER)
    private Film film;
    @ManyToOne(fetch = FetchType.EAGER)
    private Saal saal;
    /**
     * Koostab uue Seanss objekti antud parameetritega.
     *
     * @param kuupäev seansi kuupäev
     * @param algusAeg seansi algusaeg
     * @param film seansi film
     * @param saal seansi saal
     * @param keel seansi keel
     */
    public Seanss(LocalDate kuupäev, LocalTime algusAeg, Film film, Saal saal, String keel) {
        this.kuupäev = kuupäev;
        this.algusAeg = algusAeg;
        this.film = film;
        this.saal = saal;
        this.keel= keel;
    }


    public Seanss() {

    }
    public long getId() {
        return id;
    }

    public LocalDate getKuupäev() {
        return kuupäev;
    }

    public LocalTime getAlgusAeg() {
        return algusAeg;
    }
    public String getKeel() {
        return keel;
    }

    public Film getFilm() {
        return film;
    }

    public Saal getSaal() {
        return saal;
    }
}

