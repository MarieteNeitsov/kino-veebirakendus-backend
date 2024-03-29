package com.example.cinemaapplication.controllers;

import com.example.cinemaapplication.DTO.SeanssJaSkoor;
import com.example.cinemaapplication.dataobjects.Kasutaja;
import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.services.KasutajaService;
import com.example.cinemaapplication.services.SeanssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SeanssController {

    private SeanssService service;
    @Autowired
    SeanssController(SeanssService service) {
        this.service = service;
    }

    @GetMapping("/seansid")
    List<Seanss> kõikSeansid() {
        return service.kõikSeansid();
    }

    @GetMapping("/seansid/filter")
    List<Seanss> filtreeriSeansid(@RequestParam(required = false) String vanusepiirang,
                                  @RequestParam(required = false) String algusaeg,
                                  @RequestParam(required = false) String zanr,
                                  @RequestParam(required = false) String keel){
        if ("null".equals(vanusepiirang)) {
            vanusepiirang = null;
        }
        if ("null".equals(algusaeg)) {
            algusaeg = null;
        }
        if ("null".equals(zanr)) {
            zanr = null;
        }
        if ("null".equals(keel)) {
            keel = null;
        }
        List<Seanss> seansid = service.filtreeriSeansse(vanusepiirang, algusaeg, zanr, keel);
        return seansid;
    }

    @GetMapping("/soovitused")
    SeanssJaSkoor kõikSoovitused(@RequestParam Long kasutajaId) {
        Map<Seanss, Double> soovitused=service.soovitaSeansse(kasutajaId);
        List<Seanss> seansid = new ArrayList<>(soovitused.keySet());
        List<Double> skoorid = new ArrayList<>(soovitused.values());
        return new SeanssJaSkoor(seansid, skoorid);
    }



}
