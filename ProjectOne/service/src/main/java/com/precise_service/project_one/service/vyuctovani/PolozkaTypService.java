package com.precise_service.project_one.service.vyuctovani;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.repository.PolozkaTypRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PolozkaTypService implements IPolozkaTypService {

  @Autowired
  private PolozkaTypRepository polozkaTypRepository;

  @Override
  public PolozkaTyp postPolozkaTyp(PolozkaTyp polozkaTyp) {
    log.trace("postPolozkaTyp()");
    return polozkaTypRepository.save(polozkaTyp);
  }

  @Override
  public PolozkaTyp putPolozkaTyp(PolozkaTyp polozkaTyp) {
    log.trace("putPolozkaTyp()");
    return polozkaTypRepository.save(polozkaTyp);
  }

  @Override
  public PolozkaTyp getPolozkaTyp(String idPolozkaTyp) {
    log.trace("getPolozkaTyp()");
    return polozkaTypRepository.findById(idPolozkaTyp).get();
  }

  @Override
  public List<PolozkaTyp> getPolozkaTypAll() {
    log.trace("getPolozkaTypAll()");
    return polozkaTypRepository.findAll();
  }

  @Override
  public void deletePolozkaTyp(String idPolozkaTyp) {
    log.trace("deletePolozkaTyp()");
    polozkaTypRepository.deleteById(idPolozkaTyp);
  }

  @Override
  public void deletePolozkaTypAll() {
    log.trace("deletePolozkaTypAll()");
    polozkaTypRepository.deleteAll();
  }

  @Override
  public List<PolozkaTyp> getPolozkaTypListByIdNemovitost(String idNemovitost) {
    log.trace("getPolozkaTypListByIdNemovitost()");
    return polozkaTypRepository.getPolozkaTypListByIdNemovitost(idNemovitost);
  }


}
