package com.example.cinemaapplication.DTO;

import com.example.cinemaapplication.dataobjects.Seanss;

import java.util.List;
/**
 * Kujutab seansside ja sobimise tõenäosuste andmeedastusobjekti kinorakenduses.
 */
public class SeanssJaSkoor{
    private List<Seanss> seansid;
    private List<Double> skoorid;


    public SeanssJaSkoor(List<Seanss> seansid, List<Double> skoorid) {
        this.seansid = seansid;
        this.skoorid = skoorid;
    }

    public List<Seanss> getSeansid() {
        return seansid;
    }

    public List<Double> getSkoorid() {
        return skoorid;
    }
}
