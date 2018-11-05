package com.precise_service.project_one.service.predavaci_protokol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.repository.PredavaciProtokolRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PredavaciProtokolService implements IPredavaciProtokolService {

  @Autowired
  private PredavaciProtokolRepository predavaciProtokolRepository;

  @Override
  public PredavaciProtokol postPredavaciProtokol(PredavaciProtokol predavaciProtokol) {
    log.trace("postPredavaciProtokol()");
    return predavaciProtokolRepository.save(predavaciProtokol);
  }

  @Override
  public PredavaciProtokol putPredavaciProtokol(PredavaciProtokol predavaciProtokol) {
    log.trace("putPredavaciProtokol()");
    return predavaciProtokolRepository.save(predavaciProtokol);
  }

  @Override
  public List<PredavaciProtokol> getPredavaciProtokolList() {
    log.trace("getPredavaciProtokolList()");
    return predavaciProtokolRepository.findAll();
  }

  @Override
  public PredavaciProtokol getPredavaciProtokol(String idPredavaciProtokol) {
    log.trace("getPredavaciProtokol()");
    return predavaciProtokolRepository.findById(idPredavaciProtokol).get();
  }

  @Override
  public List<PredavaciProtokol> getPredavaciProtokolAll() {
    log.trace("getPredavaciProtokolAll()");
    return predavaciProtokolRepository.findAll();
  }

  @Override
  public List<PredavaciProtokol> getPredavaciProtokolAll(String idPrihlasenyUzivatel) {
    log.trace("getPredavaciProtokolAll()");
    return predavaciProtokolRepository.getPredavaciProtokolAll(idPrihlasenyUzivatel);
  }

  @Override
  public void deletePredavaciProtokol(String idPredavaciProtokol) {
    log.trace("deletePredavaciProtokol()");
    predavaciProtokolRepository.deleteById(idPredavaciProtokol);
  }

  @Override
  public void deletePredavaciProtokolAll() {
    log.trace("deletePredavaciProtokolAll()");
    predavaciProtokolRepository.deleteAll();
  }
}
