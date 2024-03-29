package com.example.cinemaapplication.DTO;
/**
 * Kujutab kasutaja andmeedastusobjekti kinorakenduses.
 */
public class KasutajaDTO {
    private String email;
    private String parool;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParool() {
        return parool;
    }

    public void setParool(String parool) {
        this.parool = parool;
    }
}
