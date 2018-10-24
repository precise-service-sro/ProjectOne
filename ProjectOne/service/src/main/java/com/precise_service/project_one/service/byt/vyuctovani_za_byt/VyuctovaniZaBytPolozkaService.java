package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytPolozka;
import com.precise_service.project_one.repository.byt.vyuctovani_za_byt.VyuctovaniZaBytPolozkaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniZaBytPolozkaService implements IVyuctovaniZaBytPolozkaService {

  @Autowired
  private VyuctovaniZaBytPolozkaRepository vyuctovaniZaBytPolozkaRepository;

  @Override
  public VyuctovaniZaBytPolozka postVyuctovaniZaBytPolozka(VyuctovaniZaBytPolozka vyuctovaniZaByt) {
    log.trace("postVyuctovaniZaBytPolozka()");
    return vyuctovaniZaBytPolozkaRepository.save(vyuctovaniZaByt);
  }

  @Override
  public VyuctovaniZaBytPolozka putVyuctovaniZaBytPolozka(VyuctovaniZaBytPolozka vyuctovaniZaByt) {
    log.trace("putVyuctovaniZaBytPolozka()");
    return vyuctovaniZaBytPolozkaRepository.save(vyuctovaniZaByt);
  }

  @Override
  public VyuctovaniZaBytPolozka getVyuctovaniZaBytPolozka(String idVyuctovaniZaBytPolozka) {
    log.trace("getVyuctovaniZaBytPolozka()");
    return vyuctovaniZaBytPolozkaRepository.findById(idVyuctovaniZaBytPolozka).get();
  }

  @Override
  public List<VyuctovaniZaBytPolozka> getVyuctovaniZaBytPolozkaAll() {
    log.trace("getVyuctovaniZaBytPolozkaAll()");
    return vyuctovaniZaBytPolozkaRepository.findAll();
  }

  @Override
  public List<VyuctovaniZaBytPolozka> getVyuctovaniZaBytPolozkaAll(String idVyuctovaniZaByt) {
    log.trace("getVyuctovaniZaBytPolozkaAll()");
    return vyuctovaniZaBytPolozkaRepository.getVyuctovaniZaBytPolozkaAll(idVyuctovaniZaByt);
  }

  @Override
  public void deleteVyuctovaniZaBytPolozka(String idVyuctovaniZaBytPolozka) {
    log.trace("deleteVyuctovaniZaBytPolozka()");
    vyuctovaniZaBytPolozkaRepository.deleteById(idVyuctovaniZaBytPolozka);
  }

  @Override
  public void deleteVyuctovaniZaBytPolozkaAll() {
    log.trace("deleteVyuctovaniZaBytPolozkaAll()");
    vyuctovaniZaBytPolozkaRepository.deleteAll();
  }
}
