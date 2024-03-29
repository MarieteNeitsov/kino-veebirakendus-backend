package com.example.cinemaapplication.dataobjects;

import jakarta.persistence.*;

import java.util.*;
/**
 * Kujutab saali kinorakenduses.
 */
@Entity
@Table(name="Saalid")
public class Saal {
    @Id
    @GeneratedValue
    private int id;
    private String nimi;
    @Transient
    private final int istekohtadeArv = 135;
    @Transient
    private final int ridadeArv = 9;
    @OneToMany(mappedBy = "saal", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private final List<Istekoht> istekohad = new ArrayList<>();

    /**
     * Koostab uue Saal objekti antud nimega.
     *
     * @param nimi saali nimi
     */
    public Saal( String nimi) {
        this.nimi = nimi;

    }
    public Saal() {

    }
    /**
     * Lisab istekohad saali.
     * 135 istet, 9 rida, 15 istekohta reas
     */
    public void lisaIstekohad(){
        Set<Integer> hõivatudKohad = genereeriHõivatudKohad();
        int reaCounter =1;
        int veergCounter = 1;

        for (int i = 1; i <= istekohtadeArv; i++) {
            boolean vaba = true;
            if (hõivatudKohad.contains(i)) vaba = false;
            istekohad.add(new Istekoht(reaCounter,veergCounter,i,vaba,this));

            if(i % 15 == 0) {
                reaCounter++;
            }
            if(veergCounter == 15) {
                veergCounter = 1;
            } else {
                veergCounter++;
            }
        }
    }

    /**
     * Genereerib hõivatud istekohad nii, et 30% saalist oleks hõivatud.
     *
     * @return hõivatud istekohtade hulk
     */
    private Set<Integer> genereeriHõivatudKohad(){

        int protsentHõivatud = 30;
        int arvHõivatud = (int) Math.round(istekohtadeArv * ((double) protsentHõivatud / 100));
        Set<Integer> hõivatudKohad = new HashSet<>();

        for (int i = 0; i < arvHõivatud; i++) {
            int hõivatudKohaNr;

            while(true) {
                hõivatudKohaNr = (int) Math.round(Math.random() * istekohtadeArv +1);

                if(!Arrays.asList(hõivatudKohad).contains(hõivatudKohaNr)) break;
            }
            hõivatudKohad.add(hõivatudKohaNr);

        }
        return hõivatudKohad;
    }
    /**
     * Tagastab saali struktuuri, kus iga rida on eraldi list.
     *
     * @return istekohtade listide list
     */
    public List<List<Istekoht>> saaliStrukruur(){
        List<List<Istekoht>> saal = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            saal.add(new ArrayList<>());
        }
        for (Istekoht istekoht : istekohad) {
            saal.get(istekoht.getRida()-1).add(istekoht);
        }
        väärtusedKohtadele(saal);
        return saal;
    }

    /**
     * Määrab istekohtadele väärtused, reapunktide ja kohapunktide alusel.
     * mida parem koht, seda suurem väärtus on kohal
     *
     * @param saal saali struktuur
     */
    private void väärtusedKohtadele( List<List<Istekoht>> saal){
        int[] kohaPunktid = {1,2,3,4,5,6,7,8,7,6,5,4,3,2,1};
        int[] ridaPunktid = {2,4,6,8,10,8,6,4,2};

        for (int i = 0; i < ridaPunktid.length; i++) {
            for (int j = 0; j < kohaPunktid.length; j++) {
                Istekoht koht = saal.get(i).get(j);
                koht.setVäärtus(kohaPunktid[j]+ridaPunktid[i]);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public int getIstekohtadeArv() {
        return istekohtadeArv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public List<Istekoht> getIstekohad() {
        return istekohad;
    }
}

