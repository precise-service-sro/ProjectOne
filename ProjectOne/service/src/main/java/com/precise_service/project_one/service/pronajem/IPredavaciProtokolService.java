package com.precise_service.project_one.service.pronajem;

import java.util.List;

import com.precise_service.project_one.entity.pronajem.PredavaciProtokol;

public interface IPredavaciProtokolService {
  PredavaciProtokol postPredavaciProtokol(PredavaciProtokol predavaciProtokol);

  PredavaciProtokol putPredavaciProtokol(PredavaciProtokol predavaciProtokol);

  List<PredavaciProtokol> getPredavaciProtokolList();

  PredavaciProtokol getPredavaciProtokol(String idPredavaciProtokol);

  void deletePredavaciProtokol(String idPredavaciProtokol);

  List<PredavaciProtokol> getPredavaciProtokolAll();

  List<PredavaciProtokol> getPredavaciProtokolAll(String idPrihlasenyUzivatel);

  void deletePredavaciProtokolAll();
}
