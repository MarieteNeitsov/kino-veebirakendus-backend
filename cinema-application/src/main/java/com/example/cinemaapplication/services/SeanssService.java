package com.example.cinemaapplication.services;

import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.repositories.SeanssRepositoorium;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeanssService {

    private final SeanssRepositoorium repositoorium;

    public SeanssService(SeanssRepositoorium repositoorium){
        this.repositoorium = repositoorium;
    }


    public List<Seanss> kõikSeansid() {
        return repositoorium.findAll();
    }


    public List<Seanss> kõikSoovitused(Long kasutajaId) {
        return new ArrayList<>();

    }
}
