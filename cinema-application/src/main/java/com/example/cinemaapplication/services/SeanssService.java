package com.example.cinemaapplication.services;

import com.example.cinemaapplication.dataobjects.Kasutaja;
import com.example.cinemaapplication.dataobjects.Seanss;
import com.example.cinemaapplication.repositories.SeanssRepositoorium;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeanssService {
    private static final Logger logger = LoggerFactory.getLogger(SeanssService.class);
    private final SeanssRepositoorium repositoorium;
    private final KasutajaService kasutajaService;
    public SeanssService(SeanssRepositoorium repositoorium, KasutajaService kasutajaService){
        this.kasutajaService = kasutajaService;
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
    public Seanss leiaSeanss(Long id){
        return repositoorium.findById(id).orElseThrow(() -> new RuntimeException("Seanssi id-ga " + id + " ei leitud"));
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
    
   
    
    public  Map<Seanss, Double> soovitaSeansse(Long kasutajaId){
        Kasutaja kasutaja= kasutajaService.leiaKasutaja(kasutajaId);
        List<Seanss> kasutajaAjalugu = kasutaja.getVaadatudSeansid();

        int vaadatudSeanssideArv = kasutajaAjalugu.size();

        Map<String, Double> zanriKaalud = leiaZanriKaalud(kasutajaAjalugu, vaadatudSeanssideArv);

        Map<String, Double> algusajaKaalud = leiaAlgusajaKaalud(kasutajaAjalugu, vaadatudSeanssideArv);

        List<Seanss> sobivadSeansid = leiaSobivadSeansid(kasutajaAjalugu, zanriKaalud, algusajaKaalud);

        Map<Seanss, Double> soovitusKaalud = new HashMap<>();

        for (Seanss seanss: sobivadSeansid){
            double lisaProtsent = 0.0;
            double algusajaKaal = algusajaKaalud.get(Integer.toString(seanss.getAlgusAeg().getHour()));
            double zanriKaal = zanriKaalud.get(seanss.getFilm().getZanr());
            if(algusajaKaal >= 0.5 && (!(zanriKaal==1.0))){
                lisaProtsent=0.1;
            }
            soovitusKaalud.put(seanss, zanriKaal+lisaProtsent);
        }

        //AI tööriistaga genereeritud sorteerimse kood
        Map<Seanss, Double> sortedSoovitusKaalud = soovitusKaalud.entrySet().stream()
                .sorted(Map.Entry.<Seanss, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));

        return soovitusKaalud;
    }

    private List<Seanss> leiaSobivadSeansid(List<Seanss> kasutajaAjalugu, Map<String, Double> zanriKaalud, Map<String, Double> algusajaKaalud) {
        List<Seanss> sobivadSeansid = kõikSeansid().stream()
                .filter(seanss -> !kasutajaAjalugu.contains(seanss))
                .filter(seanss -> zanriKaalud.containsKey(seanss.getFilm().getZanr()))
                .filter(seanss -> algusajaKaalud.containsKey(Integer.toString(seanss.getAlgusAeg().getHour())))
                .collect(Collectors.toList());
        return sobivadSeansid;
    }

    private static Map<String, Double> leiaAlgusajaKaalud(List<Seanss> kasutajaAjalugu, int vaadatudSeanssideArv) {
        Map<String,Double> algusajaKaalud = new HashMap<>();
        for (Seanss seanss: kasutajaAjalugu){
            int algusAeg = seanss.getAlgusAeg().getHour();
            String algusAegString = Integer.toString(algusAeg);
            if (algusajaKaalud.containsKey(algusAegString)){
                algusajaKaalud.put(algusAegString, algusajaKaalud.get(algusAegString)+1.0);
            } else {
                algusajaKaalud.put(algusAegString, 1.0);
            }
        }
        algusajaKaalud.replaceAll((k, v) -> v / vaadatudSeanssideArv);
        return algusajaKaalud;
    }

    private static Map<String, Double> leiaZanriKaalud(List<Seanss> kasutajaAjalugu, int vaadatudSeanssideArv) {
        Map<String, Double> zanriKaalud = new HashMap<>();
        for (Seanss seanss: kasutajaAjalugu){
            String zanr = seanss.getFilm().getZanr();
            if (zanriKaalud.containsKey(zanr)){
                zanriKaalud.put(zanr, zanriKaalud.get(zanr)+1.0);
            } else {
                zanriKaalud.put(zanr, 1.0);
            }
        }
        zanriKaalud.replaceAll((k, v) -> v / vaadatudSeanssideArv);
        return zanriKaalud;
    }


}
