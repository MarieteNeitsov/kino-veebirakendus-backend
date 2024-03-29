package com.example.cinemaapplication.controllers;

import com.example.cinemaapplication.DTO.KasutajaDTO;
import com.example.cinemaapplication.dataobjects.Kasutaja;
import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.services.KasutajaService;
import com.example.cinemaapplication.services.SaalService;
import com.example.cinemaapplication.services.SeanssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class KasutajaController {

    private KasutajaService service;
    private SeanssService seanssService;
    @Autowired
    KasutajaController(KasutajaService service,SeanssService seanssService) {
        this.service = service;
        this.seanssService = seanssService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> lisaKasutaja(@RequestBody KasutajaDTO kasutajaDTO) {
        try {
            Map<String, Object> response = service.lisaKasutaja(kasutajaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/salvestaSeanss/{seanssId}/{kasutajaId}")
    public ResponseEntity<Void> salvestaSeanss(@PathVariable Long seanssId, @PathVariable Long kasutajaId) {
        try{
            Kasutaja kasutaja = service.leiaKasutaja(kasutajaId);
            Seanss seanss = seanssService.leiaSeanss(seanssId);
            kasutaja.getVaadatudSeansid().add(seanss);
            service.uuendaKasutaja(kasutaja);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }

}
