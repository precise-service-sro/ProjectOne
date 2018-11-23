package com.precise_service.project_one.service.faktura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.faktura.FakturaPolozka;
import com.precise_service.project_one.repository.FakturaPolozkaRepository;
import com.precise_service.project_one.service.faktura.IFakturaPolozkaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakturaPolozkaService implements IFakturaPolozkaService {

  @Autowired
  private FakturaPolozkaRepository fakturaPolozkaRepository;

  @Autowired
  private IFakturaService fakturaService;

  @Override
  public FakturaPolozka postFakturaPolozka(FakturaPolozka fakturaPolozka) {
    log.trace("postFakturaPolozka()");
    return fakturaPolozkaRepository.save(fakturaPolozka);
  }

  @Override
  public FakturaPolozka zduplikovatFakturaPolozka(FakturaPolozka fakturaPolozka) {
    log.trace("zduplikovatFakturaPolozka()");
    fakturaPolozka.setId(null);
    return fakturaPolozkaRepository.save(fakturaPolozka);
  }

  @Override
  public void zduplikovatFakturaPolozkaList(String idFakturaOriginal, Faktura novaFaktura) {
    log.trace("zduplikovatFakturaPolozkaList()");

    List<FakturaPolozka> fakturaPolozkaAll = getFakturaPolozkaAll(idFakturaOriginal);
    for (FakturaPolozka fakturaPolozka : fakturaPolozkaAll) {
      fakturaPolozka.setId(null);
      fakturaPolozka.setFaktura(novaFaktura);
      fakturaPolozkaRepository.save(fakturaPolozka);
    }
  }

  @Override
  public FakturaPolozka putFakturaPolozka(FakturaPolozka fakturaPolozka) {
    log.trace("putFakturaPolozka()");
    return fakturaPolozkaRepository.save(fakturaPolozka);
  }

  @Override
  public FakturaPolozka getFakturaPolozka(String idFakturaPolozka) {
    log.trace("getFakturaPolozka()");
    return fakturaPolozkaRepository.findById(idFakturaPolozka).get();
  }

  @Override
  public List<FakturaPolozka> getFakturaPolozkaAll() {
    log.trace("getFakturaPolozkaAll()");
    return fakturaPolozkaRepository.findAll();
  }

  @Override
  public List<FakturaPolozka> getFakturaPolozkaAll(String idFaktura) {
    log.trace("getFakturaPolozkaAll()");
    return fakturaPolozkaRepository.getFakturaPolozkaAll(idFaktura);
  }

  @Override
  public boolean existujeNaFakturePolozkaDanehoTypu(String idFaktura, String idPolozkaTyp) {
    log.trace("existujeNaFakturePolozkaDanehoTypu()");
    return (!fakturaPolozkaRepository.findFakturaPolozkaList(idFaktura, idPolozkaTyp).isEmpty());
  }

  @Override
  public void deleteFakturaPolozka(String idFakturaPolozka) {
    log.trace("deleteFakturaPolozka()");
    fakturaPolozkaRepository.deleteById(idFakturaPolozka);
  }

  @Override
  public void deleteFakturaPolozkaAll() {
    log.trace("deleteFakturaPolozkaAll()");
    fakturaPolozkaRepository.deleteAll();
  }
}
