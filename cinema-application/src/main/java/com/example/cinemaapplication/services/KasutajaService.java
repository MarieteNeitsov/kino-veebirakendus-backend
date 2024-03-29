package com.example.cinemaapplication.services;

import com.example.cinemaapplication.DTO.KasutajaDTO;
import com.example.cinemaapplication.JwtUtil;
import com.example.cinemaapplication.dataobjects.Kasutaja;
import com.example.cinemaapplication.repositories.KasutajaRepositoorium;
import com.example.cinemaapplication.repositories.SaalRepositoorium;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class KasutajaService {

    private final KasutajaRepositoorium repositoorium;

    private final JwtUtil jwtUtil;

    public KasutajaService(KasutajaRepositoorium repositoorium){
        this.repositoorium = repositoorium;

        this.jwtUtil = new JwtUtil();
    }


    public Map<String, Object> lisaKasutaja(KasutajaDTO kasutajaDTO){
        if (repositoorium.findByEmail(kasutajaDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email on kasutusel");
        }
        String hashedPassword = BCrypt.hashpw(kasutajaDTO.getParool(), BCrypt.gensalt());
        Kasutaja kasutaja = new Kasutaja(kasutajaDTO.getEmail(), hashedPassword);

        Kasutaja savedKasutaja = repositoorium.save(kasutaja);

        String jwt = jwtUtil.generateToken(kasutaja.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("jwt", jwt);
        response.put("userId", savedKasutaja.getId());

        return response;
    }


    public Kasutaja leiaKasutaja(Long kasutajaId){

        return repositoorium.findById(kasutajaId).orElseThrow(() -> new RuntimeException("Kasutajat id-ga " + kasutajaId + " ei leitud"));
    }

    public void uuendaKasutaja(Kasutaja kasutaja) {
        repositoorium.save(kasutaja);
    }
}
