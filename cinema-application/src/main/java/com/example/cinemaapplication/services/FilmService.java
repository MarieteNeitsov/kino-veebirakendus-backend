package com.example.cinemaapplication.services;

import com.example.cinemaapplication.dataobjects.Film;
import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.repositories.FilmRepositoorium;
import com.example.cinemaapplication.repositories.SaalRepositoorium;
import org.springframework.stereotype.Service;
/**
 * Teenuseklass filmide haldamiseks ja suhtlemiseks andmebaasiga.
 */
@Service
public class FilmService {

    private final FilmRepositoorium repositoorium;

    public FilmService(FilmRepositoorium repositoorium){
        this.repositoorium = repositoorium;
    }

    /**
     * Lisab uue filmi hoidlasse.
     *
     * @param film lisatav film
     */
    public void lisaFilm(Film film){
        repositoorium.save(film);
    }
}
