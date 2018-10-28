package com.precise_service.project_one.service.vyuctovani;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozka;
import com.precise_service.project_one.repository.VyuctovaniPolozkaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniPolozkaService implements IVyuctovaniPolozkaService {

  @Autowired
  private VyuctovaniPolozkaRepository vyuctovaniPolozkaRepository;

  @Override
  public VyuctovaniPolozka postVyuctovaniPolozka(VyuctovaniPolozka vyuctovani) {
    log.trace("postVyuctovaniPolozka()");
    return vyuctovaniPolozkaRepository.save(vyuctovani);
  }

  @Override
  public VyuctovaniPolozka putVyuctovaniPolozka(VyuctovaniPolozka vyuctovani) {
    log.trace("putVyuctovaniPolozka()");
    return vyuctovaniPolozkaRepository.save(vyuctovani);
  }

  @Override
  public VyuctovaniPolozka getVyuctovaniPolozka(String idVyuctovaniPolozka) {
    log.trace("getVyuctovaniPolozka()");
    return vyuctovaniPolozkaRepository.findById(idVyuctovaniPolozka).get();
  }

  @Override
  public List<VyuctovaniPolozka> getVyuctovaniPolozkaAll() {
    log.trace("getVyuctovaniPolozkaAll()");
    return vyuctovaniPolozkaRepository.findAll();
  }

  @Override
  public List<VyuctovaniPolozka> getVyuctovaniPolozkaAll(String idVyuctovani) {
    log.trace("getVyuctovaniPolozkaAll()");
    return vyuctovaniPolozkaRepository.getVyuctovaniPolozkaAll(idVyuctovani);
  }

  @Override
  public void deleteVyuctovaniPolozka(String idVyuctovaniPolozka) {
    log.trace("deleteVyuctovaniPolozka()");
    vyuctovaniPolozkaRepository.deleteById(idVyuctovaniPolozka);
  }

  @Override
  public void deleteVyuctovaniPolozkaAll() {
    log.trace("deleteVyuctovaniPolozkaAll()");
    vyuctovaniPolozkaRepository.deleteAll();
  }
}
