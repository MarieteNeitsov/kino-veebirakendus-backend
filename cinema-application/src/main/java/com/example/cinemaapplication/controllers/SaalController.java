package com.example.cinemaapplication.controllers;

import com.example.cinemaapplication.dataobjects.Istekoht;
import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.services.SaalService;
import com.example.cinemaapplication.services.SeanssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaalController {
    private SaalService service;
    @Autowired
    SaalController(SaalService service) {
        this.service = service;
    }

    @GetMapping("/saal/{id}")
    List<Istekoht> kõikIstekohad(@PathVariable Long id) {
        return service.kõikIstekohad(id);
    }
}
