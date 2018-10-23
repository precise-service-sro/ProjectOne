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
  public PredavaciProtokolEntity postPredavaciProtokolEntity(PredavaciProtokolEntity predavaciProtokolEntity) {
    log.trace("postPredavaciProtokolEntity()");
    return predavaciProtokolEntityRepository.save(predavaciProtokolEntity);
  }

  @Override
  public PredavaciProtokolEntity putPredavaciProtokolEntity(PredavaciProtokolEntity predavaciProtokolEntity) {
    log.trace("putPredavaciProtokolEntity()");
    return predavaciProtokolEntityRepository.save(predavaciProtokolEntity);
  }

  @Override
  public List<PredavaciProtokolEntity> getPredavaciProtokolEntityList() {
    log.trace("getPredavaciProtokolEntityList()");
    return predavaciProtokolEntityRepository.findAll();
  }

  @Override
  public PredavaciProtokolEntity getPredavaciProtokolEntity(String idPredavaciProtokolEntity) {
    log.trace("getPredavaciProtokolEntity()");
    return predavaciProtokolEntityRepository.findById(idPredavaciProtokolEntity).get();
  }

  @Override
  public List<PredavaciProtokolEntity> getPredavaciProtokolEntityAll() {
    log.trace("getPredavaciProtokolEntityAll()");
    return predavaciProtokolEntityRepository.findAll();
  }

  @Override
  public void deletePredavaciProtokolEntity(String idPredavaciProtokolEntity) {
    log.trace("deletePredavaciProtokolEntity()");
    predavaciProtokolEntityRepository.deleteById(idPredavaciProtokolEntity);
  }

  @Override
  public void deletePredavaciProtokolEntityAll() {
    log.trace("deletePredavaciProtokolEntityAll()");
    predavaciProtokolEntityRepository.deleteAll();
  }
}
