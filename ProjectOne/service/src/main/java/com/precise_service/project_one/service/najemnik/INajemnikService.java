package com.precise_service.project_one.service.najemnik;

import java.util.List;

import com.precise_service.project_one.entity.Najemnik;

public interface INajemnikService {

  Najemnik postNajemnik(Najemnik najemnik);

  Najemnik putNajemnik(Najemnik najemnik);

  Najemnik getNajemnik(String idNajemnik);

  List<Najemnik> getNajemnikAll();

  void deleteNajemnikAll();

  void deleteNajemnik(String idNajemnik);
}
