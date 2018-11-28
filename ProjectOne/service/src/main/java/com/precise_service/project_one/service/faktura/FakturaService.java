package com.precise_service.project_one.service.faktura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.repository.FakturaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakturaService implements IFakturaService {

  @Autowired
  private FakturaRepository fakturaRepository;

  @Override
  public Faktura postFaktura(Faktura faktura) {
    log.trace("postFaktura()");
    return fakturaRepository.save(faktura);
  }

  @Override
  public Faktura zduplikovatFaktura(Faktura faktura) {
    log.trace("zduplikovatFaktura()");
    faktura.setId(null);
    return fakturaRepository.save(faktura);
  }

  @Override
  public Faktura putFaktura(Faktura faktura) {
    log.trace("putFaktura()");
    return fakturaRepository.save(faktura);
  }

  @Override
  public Faktura getFaktura(String idFaktura) {
    log.trace("getFaktura()");
    return fakturaRepository.findById(idFaktura).get();
  }

  @Override
  public List<Faktura> getFakturaAll() {
    log.trace("getFakturaAll()");
    return fakturaRepository.findAll();
  }

  @Override
  public List<Faktura> getSeznamFakturVeZuctovacimObdobi(Osoba prihlasenyUzivatel, CasovyInterval zuctovaciObdobi) {
    log.trace("getSeznamFakturVeZuctovacimObdobi()");
    return fakturaRepository.getSeznamFakturVeZuctovacimObdobi(zuctovaciObdobi.getZacatek(), zuctovaciObdobi.getKonec(), prihlasenyUzivatel.getId());
  }

  @Override
  public List<Faktura> getSeznamFaktur(Osoba prihlasenyUzivatel) {
    log.trace("getSeznamFaktur()");
    return fakturaRepository.getSeznamFaktur(prihlasenyUzivatel.getId());
  }

  @Override
  public void deleteFaktura(String idFaktura) {
    log.trace("deleteFaktura()");
    fakturaRepository.deleteById(idFaktura);
  }

  @Override
  public void deleteFakturaAll() {
    log.trace("deleteFakturaAll()");
    fakturaRepository.deleteAll();
  }

  @Override
  public List<Faktura> getAllFakturaListByNemovitost(Nemovitost nemovitost) {
    log.trace("getAllFakturaListByNemovitost()");
    return fakturaRepository.getAllFakturaListByNemovitost(nemovitost.getId());
  }
}
