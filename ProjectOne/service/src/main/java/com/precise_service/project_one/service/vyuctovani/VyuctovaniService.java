package com.precise_service.project_one.service.vyuctovani;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.NajemniSmlouva;
import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.faktura.FakturaPolozka;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokolPolozka;
import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;
import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozka;
import com.precise_service.project_one.repository.VyuctovaniRepository;
import com.precise_service.project_one.service.faktura.IFakturaPolozkaService;
import com.precise_service.project_one.service.predavaci_protokol.IPredavaciProtokolPolozkaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniService implements IVyuctovaniService {

  @Autowired
  private VyuctovaniRepository vyuctovaniRepository;

  @Autowired
  private IPolozkaTypService polozkaTypService;

  @Autowired
  private IFakturaPolozkaService fakturaPolozkaService;

  @Autowired
  private IVyuctovaniPolozkaService vyuctovaniPolozkaService;

  @Autowired
  private IPredavaciProtokolPolozkaService predavaciProtokolPolozkaService;

  @Override
  public Vyuctovani postVyuctovani(Vyuctovani vyuctovani) {
    log.trace("postVyuctovani()");
    return vyuctovaniRepository.save(vyuctovani);
  }

  @Override
  public Vyuctovani putVyuctovani(Vyuctovani vyuctovani) {
    log.trace("putVyuctovani()");
    return vyuctovaniRepository.save(vyuctovani);
  }

  @Override
  public Vyuctovani getVyuctovani(String idVyuctovani) {
    log.trace("getVyuctovani()");
    return vyuctovaniRepository.findById(idVyuctovani).get();
  }

  @Override
  public List<Vyuctovani> getVyuctovaniAll() {
    log.trace("getVyuctovaniAll()");
    return vyuctovaniRepository.findAll();
  }

  @Override
  public List<Vyuctovani> getVyuctovaniInRange(Date from, Date to) {
    log.trace("getVyuctovaniInRange()");
    return vyuctovaniRepository.getVyuctovaniInRange(from, to);
  }

  @Override
  public List<Vyuctovani> getVyuctovaniListInRange(Osoba prihlasenyUzivatel, CasovyInterval zuctovaciInterval) {
    log.trace("getVyuctovaniListInRange()");
    return vyuctovaniRepository.getVyuctovaniListInRange(zuctovaciInterval.getZacatek(), zuctovaciInterval.getKonec(), prihlasenyUzivatel.getId());
  }

  @Override
  public List<Vyuctovani> getVyuctovaniListInRange(Osoba prihlasenyUzivatel) {
    log.trace("getVyuctovaniListInRange()");
    return vyuctovaniRepository.getVyuctovaniListInRange(prihlasenyUzivatel.getId());
  }

  @Override
  public void deleteVyuctovani(String idVyuctovani) {
    log.trace("deleteVyuctovani()");
    vyuctovaniRepository.deleteById(idVyuctovani);
  }

  @Override
  public void deleteVyuctovaniAll() {
    log.trace("deleteVyuctovaniAll()");
    vyuctovaniRepository.deleteAll();
  }

  @Override
  public Vyuctovani generovatVyuctovani(String nazev, CasovyInterval zuctovaciObdobi, Nemovitost nemovitost, Osoba najemnik, List<Faktura> fakturaList, PredavaciProtokol predavaciProtokol, NajemniSmlouva najemniSmlouva, Osoba prihlasenyUzivatel) {
    log.trace("deleteVyuctovaniAll()");
    List<PolozkaTyp> polozkaTypList = polozkaTypService.getPolozkaTypListByIdNemovitost(nemovitost.getId());

    Vyuctovani vyuctovani = new Vyuctovani();
    vyuctovani.setNazev(nazev);
    vyuctovani.setZuctovaciObdobi(zuctovaciObdobi);
    vyuctovani.setNemovitost(nemovitost);
    vyuctovani.setNajemnik(najemnik);
    vyuctovani.setUzivatel(prihlasenyUzivatel);
    vyuctovani.setDatumVystaveni(new Date());
    vyuctovani.setPredavaciProtokol(predavaciProtokol);
    vyuctovani.setSeznamVychozichFaktur(fakturaList);
    vyuctovani = postVyuctovani(vyuctovani);

    for (PolozkaTyp polozkaTyp : polozkaTypList) {
      vypocetVyuctovaniPolozka(polozkaTyp, vyuctovani, prihlasenyUzivatel);
    }

    return vyuctovani;
  }

  private void vypocetVyuctovaniPolozka(PolozkaTyp polozkaTyp, Vyuctovani vyuctovani, Osoba prihlasenyUzivatel) {
    log.trace("vypocetVyuctovaniPolozka()");

    for (Faktura faktura : vyuctovani.getSeznamVychozichFaktur()) {
      List<FakturaPolozka> fakturaPolozkaAll = fakturaPolozkaService.getFakturaPolozkaAll(faktura.getId());
      for (FakturaPolozka fakturaPolozka : fakturaPolozkaAll) {
        if (fakturaPolozka.getPolozkaTyp().getId().equals(polozkaTyp.getId())) {

          VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();
          vyuctovaniPolozka.setPolozkaTyp(polozkaTyp);
          vyuctovaniPolozka.setVyuctovani(vyuctovani);
          vyuctovaniPolozka.setUzivatel(prihlasenyUzivatel);
          vyuctovaniPolozka.setZdroj("Faktura: ");

          vyuctovaniPolozka.setPocatecniStav(fakturaPolozka.getPocatecniStav());
          vyuctovaniPolozka.setKoncovyStav(fakturaPolozka.getKoncovyStav());
          vyuctovaniPolozka.setSpotreba(fakturaPolozka.getSpotreba());
          vyuctovaniPolozka.setNaklady(fakturaPolozka.getNaklady());

          vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
        }
      }
    }

    List<PredavaciProtokolPolozka> predavaciProtokolPolozkaAll = predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaAll(vyuctovani.getPredavaciProtokol().getId());
    for (PredavaciProtokolPolozka predavaciProtokolPolozka : predavaciProtokolPolozkaAll) {

      if (predavaciProtokolPolozka.getPolozkaTyp().getId().equals(polozkaTyp.getId())) {
        VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();
        vyuctovaniPolozka.setPolozkaTyp(polozkaTyp);
        vyuctovaniPolozka.setVyuctovani(vyuctovani);
        vyuctovaniPolozka.setUzivatel(prihlasenyUzivatel);
        vyuctovaniPolozka.setZdroj("Předávací protokol: ");

        Cislo pocatecniStav = new Cislo();
        pocatecniStav.setMnozstvi(Double.valueOf(predavaciProtokolPolozka.getStavMeraku()));
        pocatecniStav.setJednotka(predavaciProtokolPolozka.getJednotka());
        vyuctovaniPolozka.setPocatecniStav(pocatecniStav);
        // TODO: zvolit, dle vyšší faktury
        //vyuctovaniPolozka.setKoncovyStav(fakturaPolozka.getKoncovyStav());
        // TODO: dopočítat
        //vyuctovaniPolozka.setSpotreba(fakturaPolozka.getSpotreba());
        //vyuctovaniPolozka.setNaklady(fakturaPolozka.getNaklady());

        vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
      }
    }


    // vypocet vyuctovani dane polozky
    VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();
    vyuctovaniPolozka.setPolozkaTyp(polozkaTyp);
    vyuctovaniPolozka.setVyuctovani(vyuctovani);
    vyuctovaniPolozka.setUzivatel(prihlasenyUzivatel);
    vyuctovaniPolozka.setZvyraznit(true);
    vyuctovaniPolozka.setZdroj("Vypočteno: ");

    Cislo pocatecniStav = new Cislo();
    // TODO: zvolit mensi
    pocatecniStav.setMnozstvi(Double.valueOf("123"));
    pocatecniStav.setJednotka("kWh");
    vyuctovaniPolozka.setPocatecniStav(pocatecniStav);
    // TODO: zvolit, dle vyšší faktury
    Cislo koncovyStav = new Cislo();
    koncovyStav.setMnozstvi(Double.valueOf("456"));
    koncovyStav.setJednotka("kWh");
    vyuctovaniPolozka.setKoncovyStav(koncovyStav);
    // TODO: dopočítat
    Cislo spotreba = new Cislo();
    spotreba.setMnozstvi(koncovyStav.getMnozstvi() - pocatecniStav.getMnozstvi());
    spotreba.setJednotka(koncovyStav.getJednotka());
    vyuctovaniPolozka.setSpotreba(spotreba);
    Cislo naklady = new Cislo();
    naklady.setMnozstvi(Double.valueOf("333"));
    naklady.setJednotka("Kč");
    vyuctovaniPolozka.setNaklady(naklady);
    vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
  }
}
