package com.precise_service.project_one.service.najemnik.predavaci_protokol;

import java.util.List;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozkaEntity;

public interface IPredavaciProtokolPolozkaService {
  PredavaciProtokolPolozkaEntity postPredavaciProtokolPolozkaEntity(PredavaciProtokolPolozkaEntity predavaciProtokolPolozkaEntity);

  PredavaciProtokolPolozkaEntity putPredavaciProtokolPolozkaEntity(PredavaciProtokolPolozkaEntity predavaciProtokolPolozkaEntity);

  List<PredavaciProtokolPolozkaEntity> getPredavaciProtokolPolozkaEntityList();

  PredavaciProtokolPolozkaEntity getPredavaciProtokolPolozkaEntity(String idPredavaciProtokolPolozkaEntity);

  void deletePredavaciProtokolPolozkaEntity(String idPredavaciProtokolPolozkaEntity);

  List<PredavaciProtokolPolozkaEntity> getPredavaciProtokolPolozkaEntityAll();

  List<PredavaciProtokolPolozkaEntity> getPredavaciProtokolPolozkaEntityAll(String idPredavaciProtokolEntity);

  void deletePredavaciProtokolPolozkaEntityAll();
}
