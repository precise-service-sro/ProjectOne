package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaByt;
import com.precise_service.project_one.repository.byt.vyuctovani_za_byt.VyuctovaniZaBytRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniZaBytService implements IVyuctovaniZaBytService {

  @Autowired
  private VyuctovaniZaBytRepository vyuctovaniZaBytRepository;

  @Override
  public VyuctovaniZaByt postVyuctovaniZaByt(VyuctovaniZaByt vyuctovaniZaByt) {
    log.trace("postVyuctovaniZaByt()");
    return vyuctovaniZaBytRepository.save(vyuctovaniZaByt);
  }

  @Override
  public VyuctovaniZaByt putVyuctovaniZaByt(VyuctovaniZaByt vyuctovaniZaByt) {
    log.trace("putVyuctovaniZaByt()");
    return vyuctovaniZaBytRepository.save(vyuctovaniZaByt);
  }

  @Override
  public VyuctovaniZaByt getVyuctovaniZaByt(String idVyuctovaniZaByt) {
    log.trace("getVyuctovaniZaByt()");
    return vyuctovaniZaBytRepository.findById(idVyuctovaniZaByt).get();
  }

  @Override
  public List<VyuctovaniZaByt> getVyuctovaniZaBytAll() {
    log.trace("getVyuctovaniZaBytAll()");
    return vyuctovaniZaBytRepository.findAll();
  }

  @Override
  public List<VyuctovaniZaByt> getVyuctovaniZaBytInRange(LocalDate from, LocalDate to) {
    log.trace("getVyuctovaniZaBytInRange()");
    return vyuctovaniZaBytRepository.getVyuctovaniZaBytInRange(from, to);
  }

  @Override
  public void deleteVyuctovaniZaByt(String idVyuctovaniZaByt) {
    log.trace("deleteVyuctovaniZaByt()");
    vyuctovaniZaBytRepository.deleteById(idVyuctovaniZaByt);
  }

  @Override
  public void deleteVyuctovaniZaBytAll() {
    log.trace("deleteVyuctovaniZaBytAll()");
    vyuctovaniZaBytRepository.deleteAll();
  }
}
