package com.example.cinemaapplication;

import com.example.cinemaapplication.dataobjects.Kasutaja;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
/**
 * Klass JSON Web Tokenite (JWT) genereerimiseks.
 */
public class JwtUtil {
    private final String SECRET_KEY = "lksdecf8wenfma021k12345678901234";

    private final long JWT_TOKEN_VALIDITY = 7 * 24 * 60 * 60;
    private final Key key;

    public JwtUtil() {

        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String subject) {
        return Jwts.builder().setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }
}