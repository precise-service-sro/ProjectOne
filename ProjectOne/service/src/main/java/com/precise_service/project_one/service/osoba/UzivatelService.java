package com.precise_service.project_one.service.osoba;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.osoba.Uzivatel;
import com.precise_service.project_one.repository.UzivatelRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UzivatelService implements IUzivatelService {

  @Autowired
  private UzivatelRepository uzivatelRepository;

  @Override
  public Uzivatel postUzivatel(Uzivatel uzivatel) {
    log.trace("postUzivatel()");
    return uzivatelRepository.save(uzivatel);
  }

  @Override
  public Uzivatel putUzivatel(Uzivatel uzivatel) {
    log.trace("putUzivatel()");
    return uzivatelRepository.save(uzivatel);
  }

  @Override
  public Uzivatel getUzivatel(String idUzivatel) {
    log.trace("getUzivatel()");
    return uzivatelRepository.findById(idUzivatel).get();
  }

  @Override
  public List<Uzivatel> getUzivatelAll() {
    log.trace("getUzivatelAll()");
    return uzivatelRepository.findAll();
  }

  @Override
  public void deleteUzivatelAll() {
    log.trace("deleteUzivatelAll()");
    uzivatelRepository.deleteAll();
  }

  @Override
  public void deleteUzivatel(String idUzivatel) {
    log.trace("deleteUzivatel()");
    uzivatelRepository.deleteById(idUzivatel);
  }
}
