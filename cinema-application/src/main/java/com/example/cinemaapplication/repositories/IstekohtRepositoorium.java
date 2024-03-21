package com.example.cinemaapplication.repositories;

import com.example.cinemaapplication.dataobjects.Istekoht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IstekohtRepositoorium extends JpaRepository<Istekoht, Long> {
}
