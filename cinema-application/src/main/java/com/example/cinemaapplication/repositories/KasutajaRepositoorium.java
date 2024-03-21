package com.example.cinemaapplication.repositories;

import com.example.cinemaapplication.dataobjects.Kasutaja;
import org.springframework.data.repository.CrudRepository;

public interface KasutajaRepositoorium extends CrudRepository<Kasutaja, Long> {
}
