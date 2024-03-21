package com.example.cinemaapplication;

import com.example.cinemaapplication.dataobjects.Film;
import com.example.cinemaapplication.dataobjects.Saal;
import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.repositories.FilmRepositoorium;
import com.example.cinemaapplication.repositories.SaalRepositoorium;
import com.example.cinemaapplication.repositories.SeanssRepositoorium;
import com.example.cinemaapplication.services.FilmService;
import com.example.cinemaapplication.services.SaalService;
import com.example.cinemaapplication.services.SeanssService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        filmid.add(new Film("Barbie",1.54,"Seiklus","PG-13","static/pildid/barbie.jpg") );
        filmid.add(new Film("Saltburn",2.11,"Draama","R","static/pildid/saltburn.jpg") );
        filmid.add(new Film("Oppenheimer",3.0,"Biograafia","R","static/pildid/oppenheimer.jpg") );
        filmid.add(new Film("Anyone But You",1.43,"Komöödia","R","static/pildid/anyone-but-you.jpg"));
        filmid.add(new Film("Anyone But You",1.43,"Komöödia","R","static/pildid/anyone-but-you.jpg"));
        filmid.add(new Film("Despicable me 3",1.29,"Animatsioon","PG","static/pildid/despicable-me-3.jpg") );
        filmid.add(new Film("Gone Girl",2.29,"Draama","R","static/pildid/gone-girl.jpg"));

        for (Film film : filmid) {
            filmService.lisaFilm(film);
        }

        Saal saal = new Saal("Täheke");
        saal.lisaIstekohad();
        saalService.lisaSaal(saal);
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 1),LocalTime.of(19, 30),filmid.get(1),saal,"inglise"));
        seanssService.lisaSeanss(new Seanss(LocalDate.of(2024, 4, 2),LocalTime.of(17, 30),filmid.get(2),saal,"inglise"));
    }
}


