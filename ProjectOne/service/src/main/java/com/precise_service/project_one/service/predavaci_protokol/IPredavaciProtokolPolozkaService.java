package com.precise_service.project_one.service.predavaci_protokol;

import java.util.List;

import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokolPolozka;

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
