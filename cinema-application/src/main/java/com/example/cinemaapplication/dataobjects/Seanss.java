package com.example.cinemaapplication.dataobjects;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="Seansid")
public class Seanss {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate kuupäev;
    private LocalTime algusAeg;
    @ManyToOne
    private Film film;
    @ManyToOne
    private Saal saal;

    public Seanss(LocalDate kuupäev, LocalTime algusAeg, Film film, Saal saal) {
        this.kuupäev = kuupäev;
        this.algusAeg = algusAeg;
        this.film = film;
        this.saal = saal;
    }


    public Seanss() {

    }
}

