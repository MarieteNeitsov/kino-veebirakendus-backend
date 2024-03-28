package com.example.cinemaapplication.services;

import com.example.cinemaapplication.dataobjects.Istekoht;
import com.example.cinemaapplication.dataobjects.Saal;
import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.repositories.SaalRepositoorium;
import com.example.cinemaapplication.repositories.SeanssRepositoorium;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SaalService {
    private final SaalRepositoorium repositoorium;

    public SaalService(SaalRepositoorium repositoorium){
        this.repositoorium = repositoorium;
    }

    public List<Istekoht> leiaSaal(Long saaliId) {
        Optional<Saal> optionalSaal = repositoorium.findById(saaliId);

        if (optionalSaal.isPresent()) {
            Saal saal = optionalSaal.get();
            return saal.getIstekohad();
        } else {
            throw new RuntimeException("Saali" + saaliId +"ei leitud");
        }

    }
    public void lisaSaal(Saal saal){
        repositoorium.save(saal);
    }
    public Saal otsiSaaliObjekt(Long saaliId){
        Optional<Saal> optionalSaal = repositoorium.findById(saaliId);
        if(optionalSaal.isPresent()){
            return optionalSaal.get();
        } else {
            throw new RuntimeException("Saali id-ga " + saaliId + " ei leitud");
        }
    }

   private int MaxReaSumma = 0;

    public List<Integer> soovitaKohad(int arv,long saalId){
        Saal saal = otsiSaaliObjekt(saalId);
        List<List<Istekoht>> saaliRead = saal.saaliStrukruur();

        List<Integer> leitudKohad = new ArrayList<>();
        int saaliSuurimSumma = 0;
        for (List<Istekoht> rida : saaliRead) {
            List<Istekoht> kohadReas = leiaJärjestikusedKohad(arv,rida);
            if (kohadReas.isEmpty()) {
                continue;

            }
            if(MaxReaSumma>saaliSuurimSumma){
                saaliSuurimSumma=MaxReaSumma;
                leitudKohad.clear();
                leitudKohad.addAll(kohadReas.stream().map(Istekoht::getKohaNumber).collect(Collectors.toList()));
            }

        }

        return leitudKohad;
    }

    private List<Istekoht> leiaJärjestikusedKohad (int arv,List<Istekoht> rida) {
        List<Istekoht> leitudKohad = new ArrayList<>();
        List<Istekoht> vabadKohad = new ArrayList<>();
        MaxReaSumma = 0;
        int väärtusteSumma = 0;
        for (Istekoht istekoht : rida) {
            if (istekoht.getVaba()) {
                vabadKohad.add(istekoht);
            } else {
                if (vabadKohad.size() >= arv) {
                    Map<List<Istekoht>,Integer> leitud=leiaParimad(vabadKohad, arv);

                    väärtusteSumma = leitud.values().iterator().next();

                    if (väärtusteSumma >  MaxReaSumma) {
                        MaxReaSumma = väärtusteSumma;
                        leitudKohad.clear();
                        leitudKohad.addAll(leitud.keySet().iterator().next());
                    }
                }
                vabadKohad.clear();
            }
        }
        return leitudKohad;
    }

    private Map<List<Istekoht>,Integer> leiaParimad(List<Istekoht> vabadKohad, int arv) {
        int maxVäärtusteSumma = 0;
        int startIndeks = 0;

        for (int i = 0; i <= vabadKohad.size() - arv; i++) {
            int sum = 0;

            for (int j = i; j < i + arv; j++) {
                sum += vabadKohad.get(j).getVäärtus();
            }

            if (sum > maxVäärtusteSumma) {
                maxVäärtusteSumma = sum;
                startIndeks = i;
            }
        }
        Map<List<Istekoht>, Integer> tulemus = new HashMap<>();

        tulemus.put(vabadKohad.subList(startIndeks, startIndeks + arv),maxVäärtusteSumma);
        return tulemus;
    }


}
