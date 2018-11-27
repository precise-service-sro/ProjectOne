package com.precise_service.project_one.service.nemovitost;

import java.util.List;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;

public interface INemovitostService {

  Nemovitost postNemovitost(Nemovitost nemovitost);

  Nemovitost putNemovitost(Nemovitost nemovitost);

  Nemovitost getNemovitost(String idNemovitost);

  List<Nemovitost> getNemovitostAll();

  List<Nemovitost> getNemovitostListByVlastnik(String idVlastnik);

  void deleteNemovitost(String idNemovitost);

  void deleteNemovitostAll();
}
