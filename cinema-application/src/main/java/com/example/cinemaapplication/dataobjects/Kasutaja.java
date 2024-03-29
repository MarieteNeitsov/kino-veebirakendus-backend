package com.example.cinemaapplication.dataobjects;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Kasutajad")
public class Kasutaja {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String email;

    private String parool;

    @ManyToMany
    @JoinTable(
            name = "kasutaja_seansid",
            joinColumns = @JoinColumn(name = "kasutaja_id"),
            inverseJoinColumns = @JoinColumn(name = "seanss_id")
    )
    private List<Seanss> vaadatudSeansid = new ArrayList<>();

    public Kasutaja(String email, String parool) {

        this.email = email;
        this.parool = parool;
    }

    public Kasutaja() {

    }

    public List<Seanss> getVaadatudSeansid() {
        return vaadatudSeansid;
    }
    public void lisaVaadatudSeans(Seanss seanss){
        vaadatudSeansid.add(seanss);
    }

    public String getEmail() {
        return email;
    }

    public String getParool() {
        return parool;
    }



    public void setParool(String parool) {
        this.parool = parool;
    }

    public long getId() {
        return id;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}

