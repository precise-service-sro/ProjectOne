package com.precise_service.project_one.service.predavaci_protokol;

import java.util.List;

import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;

public interface IPredavaciProtokolService {
  PredavaciProtokol postPredavaciProtokol(PredavaciProtokol predavaciProtokol);

  PredavaciProtokol putPredavaciProtokol(PredavaciProtokol predavaciProtokol);

  List<PredavaciProtokol> getPredavaciProtokolList();

  PredavaciProtokol getPredavaciProtokol(String idPredavaciProtokol);

  void deletePredavaciProtokol(String idPredavaciProtokol);

  List<PredavaciProtokol> getPredavaciProtokolAll();

  void deletePredavaciProtokolAll();
}
