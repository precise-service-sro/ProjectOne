package com.precise_service.project_one.service.pronajem;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.pronajem.NajemniSmlouva;
import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.faktura.FakturaPolozka;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.pronajem.PredavaciProtokol;
import com.precise_service.project_one.entity.pronajem.PredavaciProtokolPolozka;
import com.precise_service.project_one.entity.pronajem.Vyuctovani;
import com.precise_service.project_one.entity.pronajem.VyuctovaniPolozka;
import com.precise_service.project_one.repository.pronajem.VyuctovaniRepository;
import com.precise_service.project_one.service.faktura.IFakturaPolozkaService;
import com.precise_service.project_one.service.IPolozkaTypService;

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
    vyuctovani.setIdOsoba(prihlasenyUzivatel.getId());
    vyuctovani.setDatumVystaveni(new Date());
    vyuctovani.setPredavaciProtokol(predavaciProtokol);
    vyuctovani.setSeznamVychozichFaktur(fakturaList);
    vyuctovani = postVyuctovani(vyuctovani);

    for (PolozkaTyp polozkaTyp : polozkaTypList) {
      log.debug("Generuji vyuctovani pro polozku: " + polozkaTyp.getNazev());
      if (polozkaTyp.getNazev().equals("!!! Nevyúčtovávat !!!")) {
        log.debug("Přeskakuji polozku: " + polozkaTyp.getNazev());
        continue;
      }

      // existuje faktura s vyuctovavanou polozkou, pokud neexistuje tak vyuctovavani teto polozky preskakuju
      boolean existujiNezbytneFaktury = false;
      List<Faktura> seznamVychozichFaktur = vyuctovani.getSeznamVychozichFaktur();
      for (Faktura faktura : seznamVychozichFaktur) {
        if (fakturaPolozkaService.existujeNaFakturePolozkaDanehoTypu(faktura.getId(), polozkaTyp.getId())) {
          existujiNezbytneFaktury = true;
          break;
        }
      }

      if (!existujiNezbytneFaktury) {
        log.warn("Přeskakuji položku bez faktur: " + polozkaTyp.getNazev());
        continue;
      }

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

    VyuctovaniPolozka souhrnaRadkaPredavaciProtokol = new VyuctovaniPolozka();
    souhrnaRadkaPredavaciProtokol.setZdroj("Předávací protokoly - souhrn: ");
    CasovyInterval zuctovaciObdobi = new CasovyInterval();
    zuctovaciObdobi.setZacatek(predavaciProtokol.getDatumPodpisu());
    souhrnaRadkaPredavaciProtokol.setZuctovaciObdobi(zuctovaciObdobi);
    souhrnaRadkaPredavaciProtokol.setPocatecniStav(new Cislo());
    souhrnaRadkaPredavaciProtokol.setPolozkaTyp(polozkaTyp);
    souhrnaRadkaPredavaciProtokol.setIdOsoba(prihlasenyUzivatel.getId());
    souhrnaRadkaPredavaciProtokol.setVyuctovani(vyuctovani);

    List<PredavaciProtokolPolozka> predavaciProtokolPolozkaAll = predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaAll(predavaciProtokol.getId());
    for (PredavaciProtokolPolozka predavaciProtokolPolozka : predavaciProtokolPolozkaAll) {

      if (predavaciProtokolPolozka.getPolozkaTyp().getId().equals(polozkaTyp.getId())) {
        VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();
        vyuctovaniPolozka.setPolozkaTyp(polozkaTyp);
        vyuctovaniPolozka.setVyuctovani(vyuctovani);
        vyuctovaniPolozka.setIdOsoba(prihlasenyUzivatel.getId());
        vyuctovaniPolozka.setZdroj("Předávací protokol: ");
        zuctovaciObdobi.setZacatek(zuctovaciObdobi.getZacatek());
        vyuctovaniPolozka.setZuctovaciObdobi(zuctovaciObdobi);

        Cislo pocatecniStav = new Cislo();
        pocatecniStav.setMnozstvi(Double.valueOf(predavaciProtokolPolozka.getStavMeraku()));
        pocatecniStav.setJednotka(predavaciProtokolPolozka.getJednotka());
        vyuctovaniPolozka.setPocatecniStav(pocatecniStav);

        souhrnaRadkaPredavaciProtokol.getPocatecniStav().setMnozstvi(souhrnaRadkaPredavaciProtokol.getPocatecniStav().getMnozstvi() + vyuctovaniPolozka.getPocatecniStav().getMnozstvi());

        vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
      }
    }

    souhrnaRadkaPredavaciProtokol = vyuctovaniPolozkaService.postVyuctovaniPolozka(souhrnaRadkaPredavaciProtokol);
    return souhrnaRadkaPredavaciProtokol;
  }

  private VyuctovaniPolozka zpracovatFaktury(PolozkaTyp polozkaTyp, Vyuctovani vyuctovani, Osoba prihlasenyUzivatel) {
    log.trace("zpracovatFaktury()");
    VyuctovaniPolozka prumernaVyuctovaniPolozkaDleFaktur = new VyuctovaniPolozka();
    prumernaVyuctovaniPolozkaDleFaktur.setPolozkaTyp(polozkaTyp);
    prumernaVyuctovaniPolozkaDleFaktur.setVyuctovani(vyuctovani);
    prumernaVyuctovaniPolozkaDleFaktur.setIdOsoba(prihlasenyUzivatel.getId());
    prumernaVyuctovaniPolozkaDleFaktur.setZdroj("Faktury - souhrn: ");
    prumernaVyuctovaniPolozkaDleFaktur.setPocatecniStav(new Cislo());
    prumernaVyuctovaniPolozkaDleFaktur.getPocatecniStav().setMnozstvi(Double.MAX_VALUE);
    prumernaVyuctovaniPolozkaDleFaktur.getPocatecniStav().setJednotka(polozkaTyp.getJednotka());
    prumernaVyuctovaniPolozkaDleFaktur.setKoncovyStav(new Cislo());
    prumernaVyuctovaniPolozkaDleFaktur.getKoncovyStav().setJednotka(polozkaTyp.getJednotka());
    prumernaVyuctovaniPolozkaDleFaktur.setCelkovaSpotreba(new Cislo());
    prumernaVyuctovaniPolozkaDleFaktur.getCelkovaSpotreba().setJednotka(polozkaTyp.getJednotka());
    prumernaVyuctovaniPolozkaDleFaktur.setPrumernaSpotrebaZaDen(new Cislo());
    prumernaVyuctovaniPolozkaDleFaktur.getPrumernaSpotrebaZaDen().setJednotka(polozkaTyp.getJednotka());
    prumernaVyuctovaniPolozkaDleFaktur.setVyuctovatelnaSpotreba(new Cislo());
    prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().setJednotka(polozkaTyp.getJednotka());
    prumernaVyuctovaniPolozkaDleFaktur.setNaklady(new Cislo());
    prumernaVyuctovaniPolozkaDleFaktur.setZuctovaciObdobi(new CasovyInterval());

    List<Faktura> seznamVychozichFaktur = vyuctovani.getSeznamVychozichFaktur();
    seznamVychozichFaktur.sort(Comparator.comparing(o -> o.getZuctovaciObdobi().getZacatek()));

    for (Faktura faktura : seznamVychozichFaktur) {
      List<FakturaPolozka> fakturaPolozkaAll = fakturaPolozkaService.getFakturaPolozkaAll(faktura.getId());
      for (FakturaPolozka fakturaPolozka : fakturaPolozkaAll) {
        if (fakturaPolozka.getPolozkaTyp().getId().equals(polozkaTyp.getId())) {

          VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();
          vyuctovaniPolozka.setPolozkaTyp(polozkaTyp);
          vyuctovaniPolozka.setVyuctovani(vyuctovani);
          vyuctovaniPolozka.setIdOsoba(prihlasenyUzivatel.getId());
          vyuctovaniPolozka.setZdroj("Faktura: ");

          vyuctovaniPolozka.setZuctovaciObdobi(faktura.getZuctovaciObdobi());
          if (prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getZacatek() == null || faktura.getZuctovaciObdobi().getZacatek().before(prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getZacatek())) {
            prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().setZacatek(faktura.getZuctovaciObdobi().getZacatek());
          }
          if (prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getKonec() == null || prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getKonec().before(faktura.getZuctovaciObdobi().getKonec())) {
            prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().setKonec(faktura.getZuctovaciObdobi().getKonec());
          }

          vyuctovaniPolozka.setPocatecniStav(fakturaPolozka.getPocatecniStav());
          // hledam minimalni pocatecni spotrebu
          if (prumernaVyuctovaniPolozkaDleFaktur.getPocatecniStav().getMnozstvi() == null ||  prumernaVyuctovaniPolozkaDleFaktur.getPocatecniStav().getMnozstvi() > fakturaPolozka.getPocatecniStav().getMnozstvi()) {
            prumernaVyuctovaniPolozkaDleFaktur.getPocatecniStav().setMnozstvi(fakturaPolozka.getPocatecniStav().getMnozstvi());
          }

          vyuctovaniPolozka.setKoncovyStav(fakturaPolozka.getKoncovyStav());
          // hledam minimalni koncovou spotrebu
          if (prumernaVyuctovaniPolozkaDleFaktur.getKoncovyStav().getMnozstvi() == null || prumernaVyuctovaniPolozkaDleFaktur.getKoncovyStav().getMnozstvi() < fakturaPolozka.getKoncovyStav().getMnozstvi()) {
            prumernaVyuctovaniPolozkaDleFaktur.getKoncovyStav().setMnozstvi(fakturaPolozka.getKoncovyStav().getMnozstvi());
          }

          vyuctovaniPolozka.setCelkovaSpotreba(fakturaPolozka.getSpotreba());
          prumernaVyuctovaniPolozkaDleFaktur.getCelkovaSpotreba().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getKoncovyStav().getMnozstvi() - prumernaVyuctovaniPolozkaDleFaktur.getPocatecniStav().getMnozstvi());

          vyuctovaniPolozka.setNaklady(fakturaPolozka.getNaklady());
          prumernaVyuctovaniPolozkaDleFaktur.getNaklady().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getNaklady().getMnozstvi() + fakturaPolozka.getNaklady().getMnozstvi());
          prumernaVyuctovaniPolozkaDleFaktur.getNaklady().setJednotka(fakturaPolozka.getNaklady().getJednotka());

          Cislo prumernaCenaZaJednotku = new Cislo();
          prumernaCenaZaJednotku.setMnozstvi(vyuctovaniPolozka.getNaklady().getMnozstvi() / vyuctovaniPolozka.getCelkovaSpotreba().getMnozstvi());
          prumernaCenaZaJednotku.setJednotka(vyuctovaniPolozka.getNaklady().getJednotka());
          vyuctovaniPolozka.setPrumernaCenaZaJednotku(prumernaCenaZaJednotku);

          vyuctovaniPolozka.setPrumernaSpotrebaZaDen(new Cislo());
          long pocetFakturovanychDnu = ((faktura.getZuctovaciObdobi().getKonec().getTime() - faktura.getZuctovaciObdobi().getZacatek().getTime()) / (1000 * 60 * 60 * 24)) + 1;
          double prepoctenaSpotrebaNaJedenDen = fakturaPolozka.getSpotreba().getMnozstvi() / pocetFakturovanychDnu;
          vyuctovaniPolozka.getPrumernaSpotrebaZaDen().setMnozstvi(prepoctenaSpotrebaNaJedenDen);
          vyuctovaniPolozka.getPrumernaSpotrebaZaDen().setJednotka(polozkaTyp.getJednotka());

          vyuctovaniPolozka.setPocetVyuctovanychDnu((int) pocetFakturovanychDnu);

          // 1. typ faktury (zacatek faktury je pred vyuctovacim obdobim a konec faktury je ve vyuctovacim obdobi)
          if (
              (faktura.getZuctovaciObdobi().getZacatek().before(vyuctovani.getZuctovaciObdobi().getZacatek())
              || faktura.getZuctovaciObdobi().getZacatek().equals(vyuctovani.getZuctovaciObdobi().getZacatek()))
              &&
              (faktura.getZuctovaciObdobi().getKonec().before(vyuctovani.getZuctovaciObdobi().getKonec())
              || faktura.getZuctovaciObdobi().getKonec().equals(vyuctovani.getZuctovaciObdobi().getKonec()))
             ) {
            log.debug("1. typ faktury (zacatek faktury je pred vyuctovacim obdobim a konec faktury je ve vyuctovacim obdobi)");
            fakturaTypJedna(fakturaPolozka, vyuctovaniPolozka, prumernaVyuctovaniPolozkaDleFaktur);
            prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().getMnozstvi() + vyuctovaniPolozka.getVyuctovatelnaSpotreba().getMnozstvi());
            prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().setJednotka(vyuctovaniPolozka.getVyuctovatelnaSpotreba().getJednotka());
            vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
            continue;
          }

          // 2. typ faktury (zacatek i konec faktury je ve vyuctovacim obdobi)
          if (
              (vyuctovani.getZuctovaciObdobi().getZacatek().before(faktura.getZuctovaciObdobi().getZacatek())
              || vyuctovani.getZuctovaciObdobi().getZacatek().equals(faktura.getZuctovaciObdobi().getZacatek()))
              &&
              (faktura.getZuctovaciObdobi().getKonec().before(vyuctovani.getZuctovaciObdobi().getKonec())
              || faktura.getZuctovaciObdobi().getKonec().equals(vyuctovani.getZuctovaciObdobi().getKonec()))
             ) {
            log.debug("2. typ faktury (zacatek i konec faktury je ve vyuctovacim obdobi)");
            fakturaTypDva(fakturaPolozka, vyuctovaniPolozka, prumernaVyuctovaniPolozkaDleFaktur);
            prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().getMnozstvi() + vyuctovaniPolozka.getVyuctovatelnaSpotreba().getMnozstvi());
            prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().setJednotka(vyuctovaniPolozka.getVyuctovatelnaSpotreba().getJednotka());
            vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
            continue;
          }

          // 3. typ faktury (zacatek faktury je ve vyuctovacim obdobi a konec faktury je za vyuctovacim obdobim)
          if (
              (vyuctovani.getZuctovaciObdobi().getZacatek().before(faktura.getZuctovaciObdobi().getZacatek())
              || vyuctovani.getZuctovaciObdobi().getZacatek().equals(faktura.getZuctovaciObdobi().getZacatek()))
              &&
              (vyuctovani.getZuctovaciObdobi().getKonec().before(faktura.getZuctovaciObdobi().getKonec())
              || vyuctovani.getZuctovaciObdobi().getKonec().equals(faktura.getZuctovaciObdobi().getKonec()))
             ) {
            log.debug("3. typ faktury (zacatek faktury je ve vyuctovacim obdobi a konec faktury je za vyuctovacim obdobim)");
            fakturaTypTri(fakturaPolozka, vyuctovaniPolozka, prumernaVyuctovaniPolozkaDleFaktur);
            prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().getMnozstvi() + vyuctovaniPolozka.getVyuctovatelnaSpotreba().getMnozstvi());
            prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().setJednotka(vyuctovaniPolozka.getVyuctovatelnaSpotreba().getJednotka());
            vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
            continue;
          }

          // 4. typ faktury (zacatek faktury je pred vyuctovacim obdobim a konec faktury je za vyuctovacim obdobi) [muze nastat, napr. kdyz delam vyuctovani za 9 mesicu, ale fakturu mam za 12 mesicu]
          if (
              (faktura.getZuctovaciObdobi().getZacatek().before(vyuctovani.getZuctovaciObdobi().getZacatek())
              || faktura.getZuctovaciObdobi().getZacatek().equals(vyuctovani.getZuctovaciObdobi().getZacatek()))
              &&
              (vyuctovani.getZuctovaciObdobi().getKonec().before(faktura.getZuctovaciObdobi().getKonec())
              || vyuctovani.getZuctovaciObdobi().getKonec().equals(faktura.getZuctovaciObdobi().getKonec()))
             ) {
            log.debug("4. typ faktury (zacatek faktury je pred vyuctovacim obdobim a konec faktury je za vyuctovacim obdobi) [muze nastat, napr. kdyz delam vyuctovani za 9 mesicu, ale fakturu mam za 12 mesicu]");
            fakturaTypCtyri(fakturaPolozka, vyuctovaniPolozka, prumernaVyuctovaniPolozkaDleFaktur);
            prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().getMnozstvi() + vyuctovaniPolozka.getVyuctovatelnaSpotreba().getMnozstvi());
            prumernaVyuctovaniPolozkaDleFaktur.getVyuctovatelnaSpotreba().setJednotka(vyuctovaniPolozka.getVyuctovatelnaSpotreba().getJednotka());
            vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
            continue;
          }
        }
      }
    }

    if (prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getKonec() != null && prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getZacatek() != null) {
      long pocetFakturovanychDnu = ((prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getKonec().getTime() - prumernaVyuctovaniPolozkaDleFaktur.getZuctovaciObdobi().getZacatek().getTime()) / (1000 * 60 * 60 * 24)) + 1;
      prumernaVyuctovaniPolozkaDleFaktur.setPocetVyuctovanychDnu((int) pocetFakturovanychDnu);
      prumernaVyuctovaniPolozkaDleFaktur.getPrumernaSpotrebaZaDen().setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getCelkovaSpotreba().getMnozstvi() / prumernaVyuctovaniPolozkaDleFaktur.getPocetVyuctovanychDnu());
    }
    Cislo prumernaCenaZaJednotku = new Cislo();
    if (prumernaVyuctovaniPolozkaDleFaktur.getCelkovaSpotreba().getMnozstvi() != 0.0) {
      prumernaCenaZaJednotku.setMnozstvi(prumernaVyuctovaniPolozkaDleFaktur.getNaklady().getMnozstvi() / prumernaVyuctovaniPolozkaDleFaktur.getCelkovaSpotreba().getMnozstvi());
      prumernaCenaZaJednotku.setJednotka(prumernaVyuctovaniPolozkaDleFaktur.getNaklady().getJednotka());
    }
    prumernaVyuctovaniPolozkaDleFaktur.setPrumernaCenaZaJednotku(prumernaCenaZaJednotku);
    return vyuctovaniPolozkaService.postVyuctovaniPolozka(prumernaVyuctovaniPolozkaDleFaktur);
  }

  private void fakturaTypJedna(FakturaPolozka fakturaPolozka, VyuctovaniPolozka vyuctovaniPolozka, VyuctovaniPolozka prumernaVyuctovaniPolozkaDleFaktur) {

    Double prepoctenaSpotrebaNaJedenDen = spolecneZpracovaniFaktur(fakturaPolozka);

    Faktura faktura = fakturaPolozka.getFaktura();
    long pocetVyutovatelnychDnuZFaktury = (faktura.getZuctovaciObdobi().getKonec().getTime() - vyuctovaniPolozka.getVyuctovani().getZuctovaciObdobi().getZacatek().getTime()) / (1000 * 60 * 60 * 24);

    Cislo vyuctovatelnaSpotreba = new Cislo();
    vyuctovatelnaSpotreba.setMnozstvi(prepoctenaSpotrebaNaJedenDen * pocetVyutovatelnychDnuZFaktury);
    vyuctovatelnaSpotreba.setJednotka(fakturaPolozka.getSpotreba().getJednotka());
    vyuctovaniPolozka.setVyuctovatelnaSpotreba(vyuctovatelnaSpotreba);
  }

  private void fakturaTypDva(FakturaPolozka fakturaPolozka, VyuctovaniPolozka vyuctovaniPolozka, VyuctovaniPolozka prumernaVyuctovaniPolozkaDleFaktur) {
    Cislo vyuctovatelnaSpotreba = new Cislo();
    vyuctovatelnaSpotreba.setMnozstvi(fakturaPolozka.getSpotreba().getMnozstvi());
    vyuctovatelnaSpotreba.setJednotka(fakturaPolozka.getSpotreba().getJednotka());
    vyuctovaniPolozka.setVyuctovatelnaSpotreba(vyuctovatelnaSpotreba);

    spolecneZpracovaniFaktur(fakturaPolozka);
  }

  private void fakturaTypTri(FakturaPolozka fakturaPolozka, VyuctovaniPolozka vyuctovaniPolozka, VyuctovaniPolozka prumernaVyuctovaniPolozkaDleFaktur) {

    Double prepoctenaSpotrebaNaJedenDen = spolecneZpracovaniFaktur(fakturaPolozka);

    Faktura faktura = fakturaPolozka.getFaktura();
    long pocetVyutovatelnychDnuZFaktury = (vyuctovaniPolozka.getVyuctovani().getZuctovaciObdobi().getKonec().getTime() - faktura.getZuctovaciObdobi().getZacatek().getTime()) / (1000 * 60 * 60 * 24);

    Cislo vyuctovatelnaSpotreba = new Cislo();
    vyuctovatelnaSpotreba.setMnozstvi(prepoctenaSpotrebaNaJedenDen * pocetVyutovatelnychDnuZFaktury);
    vyuctovatelnaSpotreba.setJednotka(fakturaPolozka.getSpotreba().getJednotka());
    vyuctovaniPolozka.setVyuctovatelnaSpotreba(vyuctovatelnaSpotreba);
  }

  private void fakturaTypCtyri(FakturaPolozka fakturaPolozka, VyuctovaniPolozka vyuctovaniPolozka, VyuctovaniPolozka prumernaVyuctovaniPolozkaDleFaktur) {

    Double prepoctenaSpotrebaNaJedenDen = spolecneZpracovaniFaktur(fakturaPolozka);

    long pocetVyutovatelnychDnuZFaktury = (vyuctovaniPolozka.getVyuctovani().getZuctovaciObdobi().getKonec().getTime() - vyuctovaniPolozka.getVyuctovani().getZuctovaciObdobi().getZacatek().getTime()) / (1000 * 60 * 60 * 24);

    Cislo vyuctovatelnaSpotreba = new Cislo();
    vyuctovatelnaSpotreba.setMnozstvi(prepoctenaSpotrebaNaJedenDen * pocetVyutovatelnychDnuZFaktury);
    vyuctovatelnaSpotreba.setJednotka(fakturaPolozka.getSpotreba().getJednotka());
    vyuctovaniPolozka.setVyuctovatelnaSpotreba(vyuctovatelnaSpotreba);
  }

  private Double spolecneZpracovaniFaktur(FakturaPolozka fakturaPolozka) {
    Faktura faktura = fakturaPolozka.getFaktura();
    long pocetFakturovanychDnu = ((faktura.getZuctovaciObdobi().getKonec().getTime() - faktura.getZuctovaciObdobi().getZacatek().getTime()) / (1000 * 60 * 60 * 24)) + 1;
    return fakturaPolozka.getSpotreba().getMnozstvi() / pocetFakturovanychDnu;
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
    vyuctovaniPolozka.setIdOsoba(prihlasenyUzivatel.getId());
    vyuctovaniPolozka.setZvyraznit(true);
    vyuctovaniPolozka.setZdroj("Vypočteno: ");

    if (prumerPredavacihoProtokolu != null) {
      if (vyuctovani.getZuctovaciObdobi().getZacatek().before(prumerPredavacihoProtokolu.getZuctovaciObdobi().getZacatek())) {
        CasovyInterval zuctovaciObdobi = new CasovyInterval();
        zuctovaciObdobi.setZacatek(prumerPredavacihoProtokolu.getZuctovaciObdobi().getZacatek());
        zuctovaciObdobi.setKonec(vyuctovani.getZuctovaciObdobi().getKonec());
        vyuctovaniPolozka.setZuctovaciObdobi(zuctovaciObdobi);
      } else {
        vyuctovaniPolozka.setZuctovaciObdobi(vyuctovani.getZuctovaciObdobi());
      }

      vyuctovaniPolozka.setPocetVyuctovanychDnu((int) (((vyuctovaniPolozka.getZuctovaciObdobi().getKonec().getTime() - vyuctovaniPolozka.getZuctovaciObdobi().getZacatek().getTime()) / (1000 * 60 * 60 * 24)) + 1));

      Cislo pocatecniStav = urcitPocatecniStavVyuctovavaneSpotreby(prumerPredavacihoProtokolu, polozkaTyp, vyuctovani);
      vyuctovaniPolozka.setPocatecniStav(pocatecniStav);

      Cislo koncovyStav = vypocitatKoncovyStavSpotreby(vyuctovani, polozkaTyp, prumerFaktur);
      vyuctovaniPolozka.setKoncovyStav(koncovyStav);

      Cislo celkovaSpotreba = new Cislo();
      celkovaSpotreba.setMnozstvi(koncovyStav.getMnozstvi() - pocatecniStav.getMnozstvi());
      celkovaSpotreba.setJednotka(koncovyStav.getJednotka());
      vyuctovaniPolozka.setCelkovaSpotreba(celkovaSpotreba);

      Cislo naklady = new Cislo();
      naklady.setMnozstvi(celkovaSpotreba.getMnozstvi() * prumerFaktur.getPrumernaCenaZaJednotku().getMnozstvi());
      naklady.setJednotka(prumerFaktur.getPrumernaCenaZaJednotku().getJednotka());
      vyuctovaniPolozka.setNaklady(naklady);

      Cislo prumernaCenaZaJednotku = new Cislo();
      prumernaCenaZaJednotku.setMnozstvi(prumerFaktur.getPrumernaCenaZaJednotku().getMnozstvi());
      prumernaCenaZaJednotku.setJednotka(prumerFaktur.getPrumernaCenaZaJednotku().getJednotka());
      vyuctovaniPolozka.setPrumernaCenaZaJednotku(prumernaCenaZaJednotku);

      Cislo prumernaSpotrebaZaDen = new Cislo();
      prumernaSpotrebaZaDen.setMnozstvi(vyuctovaniPolozka.getCelkovaSpotreba().getMnozstvi() / vyuctovaniPolozka.getPocetVyuctovanychDnu());
      prumernaSpotrebaZaDen.setJednotka(vyuctovaniPolozka.getCelkovaSpotreba().getJednotka());
      vyuctovaniPolozka.setPrumernaSpotrebaZaDen(prumernaSpotrebaZaDen);
    }

    vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
  }

  private Cislo vypocitatKoncovyStavSpotreby(Vyuctovani vyuctovani, PolozkaTyp polozkaTyp, VyuctovaniPolozka prumerFaktur) {
    Cislo koncovyStav = new Cislo();

    List<Faktura> seznamVychozichFaktur = vyuctovani.getSeznamVychozichFaktur();
    seznamVychozichFaktur.sort(Comparator.comparing(o -> o.getZuctovaciObdobi().getZacatek()));

    for (Faktura faktura : seznamVychozichFaktur) {
      List<FakturaPolozka> fakturaPolozkaAll = fakturaPolozkaService.getFakturaPolozkaAll(faktura.getId());
      for (FakturaPolozka fakturaPolozka : fakturaPolozkaAll) {
        if (fakturaPolozka.getPolozkaTyp().getId().equals(polozkaTyp.getId())) {

          if (fakturaPolozka.getKoncovyStav().getMnozstvi() > koncovyStav.getMnozstvi()) {
            if (faktura.getZuctovaciObdobi().getKonec().before(vyuctovani.getZuctovaciObdobi().getKonec())
                || faktura.getZuctovaciObdobi().getKonec().equals(vyuctovani.getZuctovaciObdobi().getKonec())) {

              koncovyStav.setMnozstvi(fakturaPolozka.getKoncovyStav().getMnozstvi());

              System.out.println("----------------------------------");
              System.out.println("----------------------------------");
              System.out.println("prezvaty koncovyStav" + koncovyStav.getMnozstvi());
              System.out.println("----------------------------------");
              System.out.println("----------------------------------");
              break;
            }

            long pocetDni = ((vyuctovani.getZuctovaciObdobi().getKonec().getTime() - faktura.getZuctovaciObdobi().getKonec().getTime()) / (1000 * 60 * 60 * 24)) + 1;
            double dopoctenaSpotrebaDlePrumeruAPoctuDnu = pocetDni * prumerFaktur.getPrumernaSpotrebaZaDen().getMnozstvi();
            koncovyStav.setMnozstvi(koncovyStav.getMnozstvi() + dopoctenaSpotrebaDlePrumeruAPoctuDnu);

            System.out.println("----------------------------------");
            System.out.println("----------------------------------");
            System.out.println("dopocteny koncovyStav" + koncovyStav.getMnozstvi());
            System.out.println("----------------------------------");
            System.out.println("----------------------------------");
            break;
          }
        }
      }
    }

    koncovyStav.setJednotka(polozkaTyp.getJednotka());
    return koncovyStav;
  }

  private Cislo urcitPocatecniStavVyuctovavaneSpotreby(VyuctovaniPolozka sourhnnaRadkaPredavaciProtokol, PolozkaTyp polozkaTyp, Vyuctovani vyuctovani) {
    Cislo pocatecniStav = new Cislo();
    // TODO: zkusti vyhledat nejake posledni vyuctovani, asi podle datumu ci nevim jak a zvolit pocatecni hodnotu bud tu z predavaciho protokolu anebo z toho nalezeneho posledniho vyuctovani, podle toho ktera bude vyssi a tim padem aktualnejsi
    pocatecniStav.setMnozstvi(sourhnnaRadkaPredavaciProtokol.getPocatecniStav().getMnozstvi());
    pocatecniStav.setJednotka(sourhnnaRadkaPredavaciProtokol.getPocatecniStav().getJednotka());
    return pocatecniStav;
  }
}
