package com.precise_service.project_one.service.najemnik.predavaci_protokol;

import java.util.List;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolEntity;

public interface IPredavaciProtokolService {
  PredavaciProtokolEntity postPredavaciProtokol(PredavaciProtokolEntity predavaciProtokolEntity);

  List<PredavaciProtokolEntity> getPredavaciProtokolEntityList();

  PredavaciProtokolEntity postPredavaciProtokolNew();

  PredavaciProtokolEntity getPredavaciProtokol(String id);

  List<PredavaciProtokolEntity> getPredavaciProtokolAll();

  void deletePredavaciProtokolAll();
}
