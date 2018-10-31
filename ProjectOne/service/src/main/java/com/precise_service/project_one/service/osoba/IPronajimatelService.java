package com.precise_service.project_one.service.osoba;

import java.util.List;

import com.precise_service.project_one.entity.osoba.Pronajimatel;

public interface IPronajimatelService {

  Pronajimatel postPronajimatel(Pronajimatel pronajimatel);

  Pronajimatel putPronajimatel(Pronajimatel pronajimatel);

  Pronajimatel getPronajimatel(String idPronajimatel);

  List<Pronajimatel> getPronajimatelAll();

  void deletePronajimatelAll();

  void deletePronajimatel(String idPronajimatel);
}
