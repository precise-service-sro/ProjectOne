package com.precise_service.project_one.service.najemnik.predavaci_protokol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozkaEntity;
import com.precise_service.project_one.repository.najemnik.predavaci_protokol.PredavaciProtokolPolozkaEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PredavaciProtokolPolozkaService implements IPredavaciProtokolPolozkaService {

  @Autowired
  private PredavaciProtokolPolozkaEntityRepository predavaciProtokolPolozkaEntityRepository;

  @Override
  public PredavaciProtokolPolozkaEntity postPredavaciProtokolPolozkaEntity(PredavaciProtokolPolozkaEntity predavaciProtokolEntity) {
    log.trace("postPredavaciProtokolPolozkaEntity()");
    return predavaciProtokolPolozkaEntityRepository.save(predavaciProtokolEntity);
  }

  @Override
  public PredavaciProtokolPolozkaEntity putPredavaciProtokolPolozkaEntity(PredavaciProtokolPolozkaEntity predavaciProtokolEntity) {
    log.trace("putPredavaciProtokolPolozkaEntity()");
    return predavaciProtokolPolozkaEntityRepository.save(predavaciProtokolEntity);
  }

  @Override
  public List<PredavaciProtokolPolozkaEntity> getPredavaciProtokolPolozkaEntityList() {
    log.trace("getPredavaciProtokolPolozkaEntityList()");
    return predavaciProtokolPolozkaEntityRepository.findAll();
  }

  @Override
  public PredavaciProtokolPolozkaEntity getPredavaciProtokolPolozkaEntity(String idPredavaciProtokolPolozkaEntity) {
    log.trace("getPredavaciProtokolPolozkaEntity()");
    return predavaciProtokolPolozkaEntityRepository.findById(idPredavaciProtokolPolozkaEntity).get();
  }

  @Override
  public List<PredavaciProtokolPolozkaEntity> getPredavaciProtokolPolozkaEntityAll() {
    log.trace("getPredavaciProtokolPolozkaEntityAll()");
    return predavaciProtokolPolozkaEntityRepository.findAll();
  }

  @Override
  public List<PredavaciProtokolPolozkaEntity> getPredavaciProtokolPolozkaEntityAll(String idPredavaciProtokolEntity) {
    log.trace("getPredavaciProtokolPolozkaEntityAll()");
    return predavaciProtokolPolozkaEntityRepository.getAllPredavaciProtokolPolozkaEntityAll(idPredavaciProtokolEntity);
  }

  @Override
  public void deletePredavaciProtokolPolozkaEntity(String idPredavaciProtokolPolozkaEntity) {
    log.trace("deletePredavaciProtokolPolozkaEntity()");
    predavaciProtokolPolozkaEntityRepository.deleteById(idPredavaciProtokolPolozkaEntity);
  }

  @Override
  public void deletePredavaciProtokolPolozkaEntityAll() {
    log.trace("deletePredavaciProtokolPolozkaEntityAll()");
    predavaciProtokolPolozkaEntityRepository.deleteAll();
  }
}
