package com.precise_service.project_one.service.nemovitost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.repository.NemovitostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NemovitostService implements INemovitostService {

  @Autowired
  private NemovitostRepository nemovitostRepository;

  @Override
  public Nemovitost postNemovitost(Nemovitost nemovitost) {
    log.trace("postNemovitost()");
    return nemovitostRepository.save(nemovitost);
  }

  @Override
  public Nemovitost putNemovitost(Nemovitost nemovitost) {
    log.trace("putNemovitost()");
    return nemovitostRepository.save(nemovitost);
  }

  @Override
  public Nemovitost getNemovitost(String idNemovitost) {
    log.trace("getNemovitost()");
    return nemovitostRepository.findById(idNemovitost).get();
  }

  @Override
  public List<Nemovitost> getNemovitostAll() {
    log.trace("getNemovitostAll()");
    return nemovitostRepository.findAll();
  }

  @Override
  public void deleteNemovitost(String idNemovitost) {
    log.trace("deleteNemovitost()");
    nemovitostRepository.deleteById(idNemovitost);
  }

  @Override
  public void deleteNemovitostAll() {
    log.trace("deleteNemovitostAll()");
    nemovitostRepository.deleteAll();
  }
}
