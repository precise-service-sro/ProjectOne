package com.precise_service.project_one.service.vyuctovani;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.NajemniSmlouva;
import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.faktura.FakturaPolozka;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;
import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozka;
import com.precise_service.project_one.repository.VyuctovaniRepository;
import com.precise_service.project_one.service.faktura.IFakturaPolozkaService;

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
    vyuctovani.setPronajimatel(nemovitost.getVlastnik());
    vyuctovani.setUzivatel(prihlasenyUzivatel);
    vyuctovani = postVyuctovani(vyuctovani);

    for (PolozkaTyp polozkaTyp : polozkaTypList) {
      VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();
      vyuctovaniPolozka.setVyuctovani(vyuctovani);
      vyuctovaniPolozka.setPolozkaTyp(polozkaTyp);
      vyuctovaniPolozka.setUzivatel(prihlasenyUzivatel);

      vypocetVyuctovaniPolozka(vyuctovaniPolozka, fakturaList);
      vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
    }

    return vyuctovani;
  }

  private void vypocetVyuctovaniPolozka(VyuctovaniPolozka vyuctovaniPolozka, List<Faktura> fakturaList) {
    log.trace("deleteVyuctovaniAll()");

    PolozkaTyp polozkaTyp = vyuctovaniPolozka.getPolozkaTyp();

    for (Faktura faktura : fakturaList) {
      log.debug("Zpracovávám fakturu: " + faktura.getNazev());
      List<FakturaPolozka> fakturaPolozkaAll = fakturaPolozkaService.getFakturaPolozkaAll(faktura.getId());
      for (FakturaPolozka fakturaPolozka : fakturaPolozkaAll) {
        if (fakturaPolozka.getPolozkaTyp().getId().equals(polozkaTyp.getId())) {
          log.debug("Zpracovávám polozku : " + fakturaPolozka.getNazev());
          log.debug("Spotreba: " + fakturaPolozka.getSpotreba().getMnozstvi() + fakturaPolozka.getSpotreba().getJednotka());
          vyuctovaniPolozka.setSpotreba(fakturaPolozka.getSpotreba());
          vyuctovaniPolozka.setNaklady(fakturaPolozka.getNaklady());
        }
      }
    }
  }
}
