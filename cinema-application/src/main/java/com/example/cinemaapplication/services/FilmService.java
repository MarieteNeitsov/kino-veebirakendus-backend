package com.example.cinemaapplication.services;

import com.example.cinemaapplication.dataobjects.Film;
import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.repositories.FilmRepositoorium;
import com.example.cinemaapplication.repositories.SaalRepositoorium;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    private final FilmRepositoorium repositoorium;

    public FilmService(FilmRepositoorium repositoorium){
        this.repositoorium = repositoorium;
    }

    public void lisaFilm(Film film){
        repositoorium.save(film);
    }
}
