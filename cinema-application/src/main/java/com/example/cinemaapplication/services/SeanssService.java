package com.example.cinemaapplication.services;

import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.repositories.SeanssRepositoorium;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SeanssService {

    private final SeanssRepositoorium repositoorium;

    public SeanssService(SeanssRepositoorium repositoorium){
        this.repositoorium = repositoorium;
    }


    public List<Seanss> kõikSeansid() {
        List<Seanss> seansid = repositoorium.findAll();
        seansid.sort(Comparator.comparing(Seanss::getKuupäev).thenComparing(Seanss::getAlgusAeg));
        return seansid;
    }


    public void lisaSeanss(Seanss seanss){
        repositoorium.save(seanss);
    }
    public List<Seanss> kõikSoovitused(Long kasutajaId) {
        return new ArrayList<>();

    }
}
