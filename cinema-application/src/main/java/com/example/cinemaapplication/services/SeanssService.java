package com.example.cinemaapplication.services;

import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.repositories.SeanssRepositoorium;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SeanssService {
    private static final Logger logger = LoggerFactory.getLogger(SeanssService.class);
    private final SeanssRepositoorium repositoorium;

    public SeanssService(SeanssRepositoorium repositoorium){
        this.repositoorium = repositoorium;
    }


    public List<Seanss> kõikSeansid() {
        List<Seanss> seansid = repositoorium.findAll();
        seansid.sort(Comparator.comparing(Seanss::getKuupäev).thenComparing(Seanss::getAlgusAeg));
        return seansid;
    }


    public void lisaSeanss(Seanss seanss){
        repositoorium.save(seanss);
    }
    public List<Seanss> kõikSoovitused(Long kasutajaId) {
        return new ArrayList<>();

    }


    public List<Seanss> filtreeriSeansse(String vanusepiirang, String algusaeg, String zanr, String keel) {
        return repositoorium.findAll(Specification
                .where(vanusepiirangudSpec(vanusepiirang))
                .and(algusajadSpec(algusaeg))
                .and(zanridSpec(zanr))
                .and(keeledSpec(keel)));
    }

    private Specification<Seanss> vanusepiirangudSpec(String vanusepiirang) {
        return (root, query, cb) -> vanusepiirang == null ? null : cb.equal(root.get("film").get("vanusepiirang"), vanusepiirang);
    }

    private Specification<Seanss> algusajadSpec(String algusaeg) {
        return (root, query, cb) -> {
            if (algusaeg == null) {
                return null;
            }
            LocalTime filterTime = LocalTime.parse(algusaeg + ":00");
            return cb.greaterThanOrEqualTo(root.get("algusAeg"), filterTime);


        };
    }

    private Specification<Seanss> zanridSpec(String zanr) {
        return (root, query, cb) -> zanr == null ? null : cb.equal(root.get("film").get("zanr"), zanr);
    }

    private Specification<Seanss> keeledSpec(String keel) {
        return (root, query, cb) -> keel == null ? null : cb.equal(root.get("keel"), keel);
    }


}
