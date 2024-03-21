package com.example.cinemaapplication.repositories;

import com.example.cinemaapplication.dataobjects.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepositoorium extends CrudRepository<Film, Long> {
}
