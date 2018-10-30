package com.precise_service.project_one.service.faktura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.faktura.FakturaPolozka;
import com.precise_service.project_one.repository.FakturaPolozkaRepository;
import com.precise_service.project_one.service.faktura.IFakturaPolozkaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakturaPolozkaService implements IFakturaPolozkaService {

  @Autowired
  private FakturaPolozkaRepository fakturaPolozkaRepository;

  @Override
  public FakturaPolozka postFakturaPolozka(FakturaPolozka faktura) {
    log.trace("postFakturaPolozka()");
    return fakturaPolozkaRepository.save(faktura);
  }

  @Override
  public FakturaPolozka putFakturaPolozka(FakturaPolozka faktura) {
    log.trace("putFakturaPolozka()");
    return fakturaPolozkaRepository.save(faktura);
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
