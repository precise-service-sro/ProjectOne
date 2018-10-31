package com.precise_service.project_one.service.osoba;

import java.util.List;

import com.precise_service.project_one.entity.osoba.Najemnik;

public interface INajemnikService {

  Najemnik postNajemnik(Najemnik najemnik);

  Najemnik putNajemnik(Najemnik najemnik);

  Najemnik getNajemnik(String idNajemnik);

  List<Najemnik> getNajemnikAll();

  void deleteNajemnikAll();

  void deleteNajemnik(String idNajemnik);
}
