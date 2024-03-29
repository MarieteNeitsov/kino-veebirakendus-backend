package com.example.cinemaapplication;

import com.example.cinemaapplication.dataobjects.Film;
import com.example.cinemaapplication.dataobjects.Istekoht;
import com.example.cinemaapplication.dataobjects.Saal;
import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.services.FilmService;
import com.example.cinemaapplication.services.SaalService;
import com.example.cinemaapplication.services.SeanssService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.ClientInfoStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
/**
 * Klass, et andmebaasi laadida algväärtused.
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

    private final FilmService filmService;
    private final SeanssService seanssService;
    private final SaalService saalService;

    public DatabaseLoader(FilmService filmService, SeanssService seanssService, SaalService saalService) {
        this.filmService = filmService;
        this.seanssService = seanssService;
        this.saalService = saalService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Film> filmid= new ArrayList<>();
        filmid.add(new Film("Barbie",1.54,"Seiklus","PG-13","http://localhost:8080/barbie.jpg") );
        filmid.add(new Film("Saltburn",2.11,"Draama","R","http://localhost:8080/saltburn.jpg") );
        filmid.add(new Film("Oppenheimer",3.0,"Biograafia","R","http://localhost:8080/oppenheimer.jpg") );
        filmid.add(new Film("Anyone But You",1.43,"Komöödia","R", "http://localhost:8080/anyone-but-you.jpg"));
        filmid.add(new Film("Despicable me 3",1.29,"Animatsioon","PG", "http://localhost:8080/despicable-me-3.jpg") );
        filmid.add(new Film("Gone Girl",2.29,"Draama","R", "http://localhost:8080/gone-girl.jpg"));
        filmid.add(new Film("Kung Fu Panda 4",1.34,"Animatsioon","PG", "http://localhost:8080/panda.jpg"));
        filmid.add(new Film("Trollid",1.32,"Animatsioon","PG", "http://localhost:8080/trollid.jpg"));
        filmid.add(new Film("Elvis",2.39,"Biograafia","PG-13", "http://localhost:8080/elvis.jpg"));
        filmid.add(new Film("Hangover",1.40,"Komöödia","R", "http://localhost:8080/hangover.jpg"));
        filmid.add(new Film("Home Alone",1.43,"Draama","PG", "http://localhost:8080/home-alone.jpg"));

        for (Film film : filmid) {
            filmService.lisaFilm(film);
        }

        Saal saal = new Saal("Täheke");
        saal.lisaIstekohad();
        System.out.println(saal.getIstekohad().size());
        saalService.lisaSaal(saal);
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 1),LocalTime.of(17, 15),filmid.get(1),saal,"inglise"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 2),LocalTime.of(17, 30),filmid.get(2),saal,"inglise"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 3),LocalTime.of(17, 45),filmid.get(5),saal,"inglise"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 4),LocalTime.of(18, 00),filmid.get(4),saal,"inglise"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 4),LocalTime.of(18, 15),filmid.get(3),saal,"eesti"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 5),LocalTime.of(18, 30),filmid.get(0),saal,"inglise"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 6),LocalTime.of(18, 45),filmid.get(6),saal,"eesti"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 7),LocalTime.of(19, 00),filmid.get(7),saal,"inglise"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 7),LocalTime.of(19, 15),filmid.get(8),saal,"inglise"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 6),LocalTime.of(19, 30),filmid.get(9),saal,"eesti"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 5),LocalTime.of(19, 45),filmid.get(10),saal,"inglise"));







    }

}




