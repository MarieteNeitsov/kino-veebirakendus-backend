package com.example.cinemaapplication.repositories;

import com.example.cinemaapplication.dataobjects.Saal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaalRepositoorium extends JpaRepository<Saal, Long> {
}
