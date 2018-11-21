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

  private VyuctovaniPolozka zpracovatPosledniVyuctovani(PolozkaTyp polozkaTyp, Vyuctovani vyuctovani, Osoba prihlasenyUzivatel) {
    log.trace("zpracovatPosledniVyuctovani()");
    // TODO: nalelezi posledniho vyuctovani a polozek (z minuleho zuctovaciho obdobi)
    return null;
  }

  private VyuctovaniPolozka zpracovatPredavaciProtokol(PolozkaTyp polozkaTyp, Vyuctovani vyuctovani, Osoba prihlasenyUzivatel) {
    log.trace("zpracovatPredavaciProtokol()");

    PredavaciProtokol predavaciProtokol = vyuctovani.getPredavaciProtokol();
    List<PredavaciProtokolPolozka> predavaciProtokolPolozkaAll = predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaAll(predavaciProtokol.getId());
    for (PredavaciProtokolPolozka predavaciProtokolPolozka : predavaciProtokolPolozkaAll) {

      if (predavaciProtokolPolozka.getPolozkaTyp().getId().equals(polozkaTyp.getId())) {
        VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();
        vyuctovaniPolozka.setPolozkaTyp(polozkaTyp);
        vyuctovaniPolozka.setVyuctovani(vyuctovani);
        vyuctovaniPolozka.setUzivatel(prihlasenyUzivatel);
        vyuctovaniPolozka.setZdroj("Předávací protokol: ");
        CasovyInterval zuctovaciObdobi = new CasovyInterval();
        zuctovaciObdobi.setZacatek(predavaciProtokol.getDatumPodpisu());
        zuctovaciObdobi.setKonec(new Date());
        vyuctovaniPolozka.setZuctovaciObdobi(zuctovaciObdobi);

        Cislo pocatecniStav = new Cislo();
        pocatecniStav.setMnozstvi(Double.valueOf(predavaciProtokolPolozka.getStavMeraku()));
        pocatecniStav.setJednotka(predavaciProtokolPolozka.getJednotka());
        vyuctovaniPolozka.setPocatecniStav(pocatecniStav);
        // TODO: zvolit, dle vyšší faktury
        //vyuctovaniPolozka.setKoncovyStav(fakturaPolozka.getKoncovyStav());
        // TODO: dopočítat
        //vyuctovaniPolozka.setSpotreba(fakturaPolozka.getSpotreba());
        //vyuctovaniPolozka.setNaklady(fakturaPolozka.getNaklady());

        return vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
      }
    }
    return null;
  }

  private VyuctovaniPolozka zpracovatFaktury(PolozkaTyp polozkaTyp, Vyuctovani vyuctovani, Osoba prihlasenyUzivatel) {
    log.trace("zpracovatFaktury()");
    VyuctovaniPolozka prumernaVyuctovaniPolozkaDleFaktur = new VyuctovaniPolozka();
    prumernaVyuctovaniPolozkaDleFaktur.setPolozkaTyp(polozkaTyp);
    prumernaVyuctovaniPolozkaDleFaktur.setVyuctovani(vyuctovani);
    prumernaVyuctovaniPolozkaDleFaktur.setUzivatel(prihlasenyUzivatel);
    prumernaVyuctovaniPolozkaDleFaktur.setZdroj("Souhrn faktur: ");
    prumernaVyuctovaniPolozkaDleFaktur.setPocatecniStav(new Cislo());
    prumernaVyuctovaniPolozkaDleFaktur.setKoncovyStav(new Cislo());
    prumernaVyuctovaniPolozkaDleFaktur.setSpotreba(new Cislo());
    prumernaVyuctovaniPolozkaDleFaktur.setNaklady(new Cislo());
    prumernaVyuctovaniPolozkaDleFaktur.setZuctovaciObdobi(new CasovyInterval());

    for (Faktura faktura : vyuctovani.getSeznamVychozichFaktur()) {
      List<FakturaPolozka> fakturaPolozkaAll = fakturaPolozkaService.getFakturaPolozkaAll(faktura.getId());
      for (FakturaPolozka fakturaPolozka : fakturaPolozkaAll) {
        if (fakturaPolozka.getPolozkaTyp().getId().equals(polozkaTyp.getId())) {

          VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();
          vyuctovaniPolozka.setPolozkaTyp(polozkaTyp);
          vyuctovaniPolozka.setVyuctovani(vyuctovani);
          vyuctovaniPolozka.setUzivatel(prihlasenyUzivatel);
          vyuctovaniPolozka.setZdroj("Faktura: ");

          vyuctovaniPolozka.setZuctovaciObdobi(faktura.getZuctovaciObdobi());
          if (prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getZacatek() == null || faktura.getZuctovaciObdobi().getZacatek().before(prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getZacatek())) {
            prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().setZacatek(faktura.getZuctovaciObdobi().getZacatek());
          }
          if (prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getKonec() == null || prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getKonec().before(faktura.getZuctovaciObdobi().getKonec())) {
            prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().setKonec(faktura.getZuctovaciObdobi().getKonec());
          }

          vyuctovaniPolozka.setPocatecniStav(fakturaPolozka.getPocatecniStav());
          prumernaVyuctovaniPolozkaDleFaktur.getPocatecniStav().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getPocatecniStav().getMnozstvi() + fakturaPolozka.getPocatecniStav().getMnozstvi());
          prumernaVyuctovaniPolozkaDleFaktur.getPocatecniStav().setJednotka(fakturaPolozka.getPocatecniStav().getJednotka());

          vyuctovaniPolozka.setKoncovyStav(fakturaPolozka.getKoncovyStav());
          prumernaVyuctovaniPolozkaDleFaktur.getKoncovyStav().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getKoncovyStav().getMnozstvi() + fakturaPolozka.getKoncovyStav().getMnozstvi());
          prumernaVyuctovaniPolozkaDleFaktur.getKoncovyStav().setJednotka(fakturaPolozka.getKoncovyStav().getJednotka());

          vyuctovaniPolozka.setSpotreba(fakturaPolozka.getSpotreba());
          prumernaVyuctovaniPolozkaDleFaktur.getSpotreba().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getSpotreba().getMnozstvi() + fakturaPolozka.getSpotreba().getMnozstvi());
          prumernaVyuctovaniPolozkaDleFaktur.getSpotreba().setJednotka(fakturaPolozka.getSpotreba().getJednotka());

          vyuctovaniPolozka.setNaklady(fakturaPolozka.getNaklady());
          prumernaVyuctovaniPolozkaDleFaktur.getNaklady().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getNaklady().getMnozstvi() + fakturaPolozka.getNaklady().getMnozstvi());
          prumernaVyuctovaniPolozkaDleFaktur.getNaklady().setJednotka(fakturaPolozka.getNaklady().getJednotka());

          Cislo prumernaCenaZaJednotku = new Cislo();
          prumernaCenaZaJednotku.setMnozstvi(vyuctovaniPolozka.getNaklady().getMnozstvi() / vyuctovaniPolozka.getSpotreba().getMnozstvi());
          prumernaCenaZaJednotku.setJednotka(vyuctovaniPolozka.getNaklady().getJednotka());
          vyuctovaniPolozka.setPrumernaCenaZaJednotku(prumernaCenaZaJednotku);

          vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
        }
      }
    }
    Cislo prumernaCenaZaJednotku = new Cislo();
    prumernaCenaZaJednotku.setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getNaklady().getMnozstvi() / prumernaVyuctovaniPolozkaDleFaktur.getSpotreba().getMnozstvi());
    prumernaCenaZaJednotku.setJednotka(prumernaVyuctovaniPolozkaDleFaktur.getNaklady().getJednotka());
    prumernaVyuctovaniPolozkaDleFaktur.setPrumernaCenaZaJednotku(prumernaCenaZaJednotku);
    return vyuctovaniPolozkaService.postVyuctovaniPolozka(prumernaVyuctovaniPolozkaDleFaktur);
  }

  private void vypocetVyuctovaniPolozka(PolozkaTyp polozkaTyp, Vyuctovani vyuctovani, Osoba prihlasenyUzivatel) {
    log.trace("vypocetVyuctovaniPolozka()");

    // vytahnuti a vygenerovani VyuctovaniPolozka z predavaciho protokolu (ale v pripade, ze u me nekde bydli delsi dobu, tak uz pro vyuctovani nezohlednuju predavaci protokol, ale posledni/predchozi vyuctovani)
    VyuctovaniPolozka prumerPoslenihoVyuctovani = zpracovatPosledniVyuctovani(polozkaTyp, vyuctovani, prihlasenyUzivatel);

    VyuctovaniPolozka prumerPredavacihoProtokolu = zpracovatPredavaciProtokol(polozkaTyp, vyuctovani, prihlasenyUzivatel);

    VyuctovaniPolozka prumerFaktur = zpracovatFaktury(polozkaTyp, vyuctovani, prihlasenyUzivatel);


    // vypocet vyuctovani dane polozky
    VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();
    vyuctovaniPolozka.setPolozkaTyp(polozkaTyp);
    vyuctovaniPolozka.setVyuctovani(vyuctovani);
    vyuctovaniPolozka.setUzivatel(prihlasenyUzivatel);
    vyuctovaniPolozka.setZvyraznit(true);
    vyuctovaniPolozka.setZdroj("Vypočteno: ");
    vyuctovaniPolozka.setZuctovaciObdobi(vyuctovani.getZuctovaciObdobi());

    Cislo pocatecniStav = new Cislo();
    // TODO: zvolit bud tu z predavaciho protokolu anebo z posledniho vyuctovani

    List<PredavaciProtokolPolozka> predavaciProtokolPolozkaList = predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaList(vyuctovani.getPredavaciProtokol().getId(), polozkaTyp.getId());

    // TODO: udelat nejaky souhrnny radek
    PredavaciProtokolPolozka predavaciProtokolPolozka = predavaciProtokolPolozkaList.get(0);

    pocatecniStav.setMnozstvi(Double.valueOf(predavaciProtokolPolozka.getStavMeraku()));
    pocatecniStav.setJednotka(predavaciProtokolPolozka.getJednotka());
    vyuctovaniPolozka.setPocatecniStav(pocatecniStav);
    // TODO: zvolit, dle vyšší faktury
    Cislo koncovyStav = new Cislo();
    koncovyStav.setMnozstvi(Double.valueOf("10000"));
    koncovyStav.setJednotka(pocatecniStav.getJednotka());
    vyuctovaniPolozka.setKoncovyStav(koncovyStav);
    // TODO: dopočítat
    Cislo spotreba = new Cislo();
    spotreba.setMnozstvi(koncovyStav.getMnozstvi() - pocatecniStav.getMnozstvi());
    spotreba.setJednotka(koncovyStav.getJednotka());
    vyuctovaniPolozka.setSpotreba(spotreba);
    Cislo naklady = new Cislo();
    naklady.setMnozstvi(spotreba.getMnozstvi() * prumerFaktur.getPrumernaCenaZaJednotku().getMnozstvi());
    naklady.setJednotka(prumerFaktur.getPrumernaCenaZaJednotku().getJednotka());
    vyuctovaniPolozka.setNaklady(naklady);

    Cislo prumernaCenaZaJednotku = new Cislo();
    prumernaCenaZaJednotku.setMnozstvi(prumerFaktur.getPrumernaCenaZaJednotku().getMnozstvi());
    prumernaCenaZaJednotku.setJednotka(prumerFaktur.getPrumernaCenaZaJednotku().getJednotka());
    vyuctovaniPolozka.setPrumernaCenaZaJednotku(prumernaCenaZaJednotku);

    vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
  }
}
