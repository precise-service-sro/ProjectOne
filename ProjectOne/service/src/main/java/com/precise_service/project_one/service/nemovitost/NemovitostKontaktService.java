package com.precise_service.project_one.service.nemovitost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostKontakt;
import com.precise_service.project_one.repository.NemovitostKontaktRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NemovitostKontaktService implements INemovitostKontaktService {

  @Autowired
  private NemovitostService nemovitostService;

  @Autowired
  private NemovitostKontaktRepository nemovitostKontaktRepository;

  @Override
  public NemovitostKontakt postNemovitostKontakt(String idNemovitost, NemovitostKontakt nemovitostKontakt) {
    log.trace("postNemovitostKontakt()");
    Nemovitost nemovitost = nemovitostService.getNemovitost(idNemovitost);
    nemovitostKontakt.setNemovitost(nemovitost);
    return nemovitostKontaktRepository.save(nemovitostKontakt);
  }

  @Override
  public NemovitostKontakt putNemovitostKontakt(String idNemovitost, NemovitostKontakt nemovitostKontakt) {
    log.trace("putNemovitostKontakt()");
    Nemovitost nemovitost = nemovitostService.getNemovitost(idNemovitost);
    nemovitostKontakt.setNemovitost(nemovitost);
    return nemovitostKontaktRepository.save(nemovitostKontakt);
  }

  @Override
  public NemovitostKontakt getNemovitostKontakt(String idNemovitost, String idNemovitostKontakt) {
    log.trace("getNemovitostKontakt()");
    return nemovitostKontaktRepository.findById(idNemovitostKontakt).get();
  }

  @Override
  public List<NemovitostKontakt> getNemovitostKontaktAll(String idNemovitost, String idPrihlasenyUzivatel) {
    log.trace("getNemovitostKontaktAll()");
    return nemovitostKontaktRepository.getNemovitostKontaktAll(idNemovitost, idPrihlasenyUzivatel);
  }

  @Override
  public void deleteNemovitostKontakt(String idNemovitost, String idNemovitostKontakt) {
    log.trace("deleteNemovitostKontakt()");
    nemovitostKontaktRepository.deleteById(idNemovitostKontakt);
  }

  @Override
  public void deleteNemovitostKontaktAll(String idNemovitost) {
    log.trace("deleteNemovitostKontaktAll()");
    nemovitostKontaktRepository.deleteAll();
  }
}
