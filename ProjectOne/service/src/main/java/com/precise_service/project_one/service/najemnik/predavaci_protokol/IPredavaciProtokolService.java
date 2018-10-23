package com.precise_service.project_one.service.najemnik.predavaci_protokol;

import java.util.List;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolEntity;

public interface IPredavaciProtokolService {
  PredavaciProtokolEntity postPredavaciProtokolEntity(PredavaciProtokolEntity predavaciProtokolEntity);

  PredavaciProtokolEntity putPredavaciProtokolEntity(PredavaciProtokolEntity predavaciProtokolEntity);

  List<PredavaciProtokolEntity> getPredavaciProtokolEntityList();

  PredavaciProtokolEntity getPredavaciProtokolEntity(String idPredavaciProtokolEntity);

  void deletePredavaciProtokolEntity(String idPredavaciProtokolEntity);

  List<PredavaciProtokolEntity> getPredavaciProtokolEntityAll();

  void deletePredavaciProtokolEntityAll();
}
