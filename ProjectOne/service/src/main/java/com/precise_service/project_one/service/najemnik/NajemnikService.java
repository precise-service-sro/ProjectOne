package com.precise_service.project_one.service.najemnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.Najemnik;
import com.precise_service.project_one.repository.NajemnikRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NajemnikService implements INajemnikService {

  @Autowired
  private NajemnikRepository najemnikRepository;

  @Override
  public Najemnik postNajemnik(Najemnik najemnik) {
    log.trace("postNajemnik()");
    return najemnikRepository.save(najemnik);
  }

  @Override
  public Najemnik putNajemnik(Najemnik najemnik) {
    log.trace("putNajemnik()");
    return najemnikRepository.save(najemnik);
  }

  @Override
  public Najemnik getNajemnik(String idNajemnik) {
    log.trace("getNajemnik()");
    return najemnikRepository.findById(idNajemnik).get();
  }

  @Override
  public List<Najemnik> getNajemnikAll() {
    log.trace("getNajemnikAll()");
    return najemnikRepository.findAll();
  }

  @Override
  public void deleteNajemnikAll() {
    log.trace("deleteNajemnikAll()");
    najemnikRepository.deleteAll();
  }

  @Override
  public void deleteNajemnik(String idNajemnik) {
    log.trace("deleteNajemnik()");
    najemnikRepository.deleteById(idNajemnik);
  }
}