package com.precise_service.project_one.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.byt.predavaciProtokol.PredavaciProtokolEntity;
import com.precise_service.project_one.repository.PredavaciProtokolEntityRepository;
import com.precise_service.project_one.service.IPredavaciProtokolService;

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
  public List<PredavaciProtokolEntity> getPredavaciProtokolEntityList() {
    log.trace("getPredavaciProtokolEntityList()");
    return predavaciProtokolEntityRepository.findAll();
  }
}
