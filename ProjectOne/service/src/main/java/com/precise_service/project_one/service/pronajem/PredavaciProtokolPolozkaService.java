package com.precise_service.project_one.service.pronajem;

import java.util.List;

import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.pronajem.PredavaciProtokolPolozka;
import com.precise_service.project_one.service.common.AbstractService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PredavaciProtokolPolozkaService extends AbstractService implements IPredavaciProtokolPolozkaService {

  @Override
  public PredavaciProtokolPolozka postPredavaciProtokolPolozka(PredavaciProtokolPolozka predavaciProtokol) {
    log.trace("postPredavaciProtokolPolozka()");
    return predavaciProtokolPolozkaRepository.save(predavaciProtokol);
  }

  @Override
  public PredavaciProtokolPolozka putPredavaciProtokolPolozka(PredavaciProtokolPolozka predavaciProtokol) {
    log.trace("putPredavaciProtokolPolozka()");
    return predavaciProtokolPolozkaRepository.save(predavaciProtokol);
  }

  @Override
  public List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaList() {
    log.trace("getPredavaciProtokolPolozkaList()");
    return predavaciProtokolPolozkaRepository.findAll();
  }

  @Override
  public PredavaciProtokolPolozka getPredavaciProtokolPolozka(String idPredavaciProtokolPolozka) {
    log.trace("getPredavaciProtokolPolozka()");
    return predavaciProtokolPolozkaRepository.findById(idPredavaciProtokolPolozka).get();
  }

  @Override
  public List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaList(String idPredavaciProtokol, String idPolozkaTyp) {
    log.trace("getPredavaciProtokolPolozka()");
    return predavaciProtokolPolozkaRepository.getPredavaciProtokolPolozkaList(idPredavaciProtokol, idPolozkaTyp);
  }


  @Override
  public List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaAll() {
    log.trace("getPredavaciProtokolPolozkaAll()");
    return predavaciProtokolPolozkaRepository.findAll();
  }

  @Override
  public List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaAll(String idPredavaciProtokol) {
    log.trace("getPredavaciProtokolPolozkaAll()");
    return predavaciProtokolPolozkaRepository.getPredavaciProtokolPolozkaAll(idPredavaciProtokol);
  }

  @Override
  public void deletePredavaciProtokolPolozka(String idPredavaciProtokolPolozka) {
    log.trace("deletePredavaciProtokolPolozka()");
    predavaciProtokolPolozkaRepository.deleteById(idPredavaciProtokolPolozka);
  }

  @Override
  public void deletePredavaciProtokolPolozkaAll() {
    log.trace("deletePredavaciProtokolPolozkaAll()");
    predavaciProtokolPolozkaRepository.deleteAll();
  }
}
