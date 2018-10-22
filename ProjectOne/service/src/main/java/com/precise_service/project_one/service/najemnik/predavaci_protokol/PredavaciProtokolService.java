package com.precise_service.project_one.service.najemnik.predavaci_protokol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolEntity;
import com.precise_service.project_one.repository.najemnik.predavaci_protokol.PredavaciProtokolEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PredavaciProtokolService implements IPredavaciProtokolService {

  @Autowired
  private PredavaciProtokolEntityRepository predavaciProtokolEntityRepository;

  @Override
  public PredavaciProtokolEntity postPredavaciProtokol(PredavaciProtokolEntity predavaciProtokolEntity) {
    log.trace("postPredavaciProtokol()");
    return predavaciProtokolEntityRepository.save(predavaciProtokolEntity);
  }

  @Override
  public PredavaciProtokolEntity putPredavaciProtokol(PredavaciProtokolEntity predavaciProtokolEntity) {
    log.trace("putPredavaciProtokol()");
    return predavaciProtokolEntityRepository.save(predavaciProtokolEntity);
  }

  @Override
  public List<PredavaciProtokolEntity> getPredavaciProtokolEntityList() {
    log.trace("getPredavaciProtokolEntityList()");
    return predavaciProtokolEntityRepository.findAll();
  }

  @Override
  public PredavaciProtokolEntity getPredavaciProtokol(String id) {
    log.trace("getPredavaciProtokol()");
    return predavaciProtokolEntityRepository.findById(id).get();
  }

  @Override
  public List<PredavaciProtokolEntity> getPredavaciProtokolAll() {
    log.trace("getPredavaciProtokolAll()");
    return predavaciProtokolEntityRepository.findAll();
  }

  @Override
  public void deletePredavaciProtokolAll() {
    log.trace("deletePredavaciProtokolAll()");
    predavaciProtokolEntityRepository.deleteAll();
  }
}
