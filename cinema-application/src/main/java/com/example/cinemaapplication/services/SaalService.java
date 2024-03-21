package com.example.cinemaapplication.services;

import com.example.cinemaapplication.dataobjects.Istekoht;
import com.example.cinemaapplication.dataobjects.Saal;
import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.repositories.SaalRepositoorium;
import com.example.cinemaapplication.repositories.SeanssRepositoorium;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SaalService {
    private final SaalRepositoorium repositoorium;

    public SaalService(SaalRepositoorium repositoorium){
        this.repositoorium = repositoorium;
    }

    public List<Istekoht> kõikIstekohad(Long saaliId) {
        Optional<Saal> optionalSaal = repositoorium.findById(saaliId);

        if (optionalSaal.isPresent()) {
            Saal saal = optionalSaal.get();
            return saal.getIstekohad();
        } else {
            throw new RuntimeException("Saali" + saaliId +"ei leitud");
        }

    }
}
