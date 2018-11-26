package com.precise_service.project_one.service.pronajem;

import java.util.List;

import com.precise_service.project_one.entity.pronajem.PredavaciProtokolPolozka;

public interface IPredavaciProtokolPolozkaService {
  PredavaciProtokolPolozka postPredavaciProtokolPolozka(PredavaciProtokolPolozka predavaciProtokolPolozka);

  PredavaciProtokolPolozka putPredavaciProtokolPolozka(PredavaciProtokolPolozka predavaciProtokolPolozka);

  List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaList();

  PredavaciProtokolPolozka getPredavaciProtokolPolozka(String idPredavaciProtokolPolozka);

  List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaList(String idPredavaciProtokol, String idPolozkaTyp);

  void deletePredavaciProtokolPolozka(String idPredavaciProtokolPolozka);

  List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaAll();

  List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaAll(String idPredavaciProtokol);

  void deletePredavaciProtokolPolozkaAll();
}
