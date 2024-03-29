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
/**

 See kontroller haldab kõiki sessioonidega seotud päringuid.
 */
@RestController
public class SeanssController {

    private SeanssService service;
    @Autowired
    SeanssController(SeanssService service) {
        this.service = service;
    }
    /**

     Tagastab kõik sessioonid.
     @return kõikide sessioonide loend
     */
    @GetMapping("/seansid")
    List<Seanss> kõikSeansid() {
        return service.kõikSeansid();
    }

    /**

    Tagastab filtreeritud sessioonid antud parameetrite põhjal.
    @param vanusepiirang vanusepiirang, mille järgi filtreerida
    @param algusaeg algusaeg, mille järgi filtreerida
    @param zanr žanr, mille järgi filtreerida
    @param keel keel, mille järgi filtreerida
    @return filtreeritud sessioonide loend
    */

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
    /**

     Tagastab kõik sessioonisoovitused antud kasutaja ID põhjal.
     @param kasutajaId kasutaja ID, kellele soovitusi saada
     @return SeanssJaSkoor objekt, mis sisaldab soovitatud sessioone ja nende sobimise tõenäosust
     */
    @GetMapping("/soovitused")
    SeanssJaSkoor kõikSoovitused(@RequestParam Long kasutajaId) {
        Map<Seanss, Double> soovitused=service.soovitaSeansse(kasutajaId);
        List<Seanss> seansid = new ArrayList<>(soovitused.keySet());
        List<Double> skoorid = new ArrayList<>(soovitused.values());
        return new SeanssJaSkoor(seansid, skoorid);
    }



}
