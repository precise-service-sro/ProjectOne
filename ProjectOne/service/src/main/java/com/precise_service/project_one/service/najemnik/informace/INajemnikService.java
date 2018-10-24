package com.precise_service.project_one.service.najemnik.informace;

import java.util.List;

import com.precise_service.project_one.entity.najemnik.informace.Najemnik;

public interface INajemnikService {

  Najemnik postNajemnik(Najemnik najemnik);

  Najemnik getNajemnik(String idNajemnik);

  List<Najemnik> getNajemnikAll();

  void deleteNajemnikAll();
}
