package com.example.cinemaapplication.controllers;

import com.example.cinemaapplication.dataobjects.Istekoht;
import com.example.cinemaapplication.services.SaalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**

 See kontroller haldab kõiki saalidega seotud päringuid.
 */
@RestController
public class SaalController {
    private SaalService service;
    @Autowired
    SaalController(SaalService service) {
        this.service = service;
    }
    /**

     Tagastab saali ID põhjal.
     @param id saali ID, mida tagastada
     @return saalis olevate istekohtade loend
     */
    @GetMapping("/saal/{id}")
    List<Istekoht> leiaSaal(@PathVariable Long id) {
        return service.leiaSaal(id);
    }

    /**

     Pakub välja istekohad saalis, mille ID on antud.
     @param id saali ID
     @param arv pakutavate istekohtade arv
     @return pakutavate istekohtade loend
     */
    @GetMapping("/saal/{id}/soovita/{arv}")
    List<Integer> soovitaKohad(@PathVariable Long id, @PathVariable int arv) {
        return service.soovitaKohad(arv,id);
    }
}
