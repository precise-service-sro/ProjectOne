package com.precise_service.project_one.service;

import java.util.List;

import com.precise_service.project_one.entity.byt.predavaciProtokol.PredavaciProtokolEntity;

public interface IPredavaciProtokolService {
  PredavaciProtokolEntity postPredavaciProtokol(PredavaciProtokolEntity predavaciProtokolEntity);

  List<PredavaciProtokolEntity> getPredavaciProtokolEntityList();
}
