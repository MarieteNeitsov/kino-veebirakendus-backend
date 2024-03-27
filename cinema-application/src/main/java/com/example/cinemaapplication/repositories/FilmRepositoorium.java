package com.example.cinemaapplication.repositories;

import com.example.cinemaapplication.dataobjects.Film;
import com.example.cinemaapplication.dataobjects.Seanss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepositoorium extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {
}
