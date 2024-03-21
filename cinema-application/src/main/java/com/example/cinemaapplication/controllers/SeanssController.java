package com.example.cinemaapplication.controllers;

import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.repositories.SeanssRepositoorium;
import com.example.cinemaapplication.services.SeanssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


   /* @GetMapping("/soovitused")
    List<Seanss> kõikSoovitused() {
        return service.kõikSoovitused();
    }*/

}
