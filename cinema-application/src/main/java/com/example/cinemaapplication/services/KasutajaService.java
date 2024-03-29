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
/**
 * Teenuseklass kasutajate haldamiseks kinorakenduses.
 */
@Service
public class KasutajaService {

    private final KasutajaRepositoorium repositoorium;

    private final JwtUtil jwtUtil;

    public KasutajaService(KasutajaRepositoorium repositoorium){
        this.repositoorium = repositoorium;

        this.jwtUtil = new JwtUtil();
    }

    /**
     * Lisab uue kasutaja andmebaasi.
     *
     * @param kasutajaDTO lisatav kasutaja DTO
     * @return map, mis sisaldab JWT-d ja kasutaja ID-d
     * @throws IllegalArgumentException kui e-posti aadress on juba kasutusel
     */
    public Map<String, Object> lisaKasutaja(KasutajaDTO kasutajaDTO){
        if (repositoorium.findByEmail(kasutajaDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email on kasutusel");
        }
        String hashedPassword = BCrypt.hashpw(kasutajaDTO.getParool(), BCrypt.gensalt());
        Kasutaja kasutaja = new Kasutaja(kasutajaDTO.getEmail(), hashedPassword);

        Kasutaja registreeritudKasutaja = repositoorium.save(kasutaja);

        String jwt = jwtUtil.generateToken(kasutaja.getEmail());

        Map<String, Object> vastus = new HashMap<>();
        vastus.put("jwt", jwt);
        vastus.put("userId", registreeritudKasutaja.getId());

        return vastus;
    }
    /**
     * Leiab kasutaja tema ID järgi.
     *
     * @param kasutajaId kasutaja ID
     * @return leitud kasutaja
     * @throws RuntimeException kui kasutajat ei leita
     */

    public Kasutaja leiaKasutaja(Long kasutajaId){

        return repositoorium.findById(kasutajaId).orElseThrow(() -> new RuntimeException("Kasutajat id-ga " + kasutajaId + " ei leitud"));
    }
    /**
     * Uuendab kasutajat andmebaasis.
     *
     * @param kasutaja uuendatav kasutaja
     */
    public void uuendaKasutaja(Kasutaja kasutaja) {
        repositoorium.save(kasutaja);
    }
}
