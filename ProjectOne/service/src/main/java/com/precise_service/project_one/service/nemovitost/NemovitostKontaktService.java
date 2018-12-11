package com.precise_service.project_one.service.nemovitost;

import java.util.List;

import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.nemovitost.NemovitostKontakt;
import com.precise_service.project_one.service.common.AbstractService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NemovitostKontaktService extends AbstractService implements INemovitostKontaktService {

  @Override
  public NemovitostKontakt postNemovitostKontakt(NemovitostKontakt nemovitostKontakt) {
    log.trace("postNemovitostKontakt()");
    return nemovitostKontaktRepository.save(nemovitostKontakt);
  }

  @Override
  public NemovitostKontakt putNemovitostKontakt(NemovitostKontakt nemovitostKontakt) {
    log.trace("putNemovitostKontakt()");
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
  public void deleteNemovitostKontakt(String idNemovitostKontakt) {
    log.trace("deleteNemovitostKontakt()");
    nemovitostKontaktRepository.deleteById(idNemovitostKontakt);
  }

  @Override
  public void deleteNemovitostKontaktAll(String idNemovitost) {
    log.trace("deleteNemovitostKontaktAll()");
    nemovitostKontaktRepository.deleteAll();
  }
}
