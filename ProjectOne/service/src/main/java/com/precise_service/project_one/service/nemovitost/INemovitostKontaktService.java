package com.precise_service.project_one.service.nemovitost;

import java.util.List;

import com.precise_service.project_one.entity.nemovitost.NemovitostKontakt;

public interface INemovitostKontaktService {

  NemovitostKontakt postNemovitostKontakt(String idNemovitost, NemovitostKontakt nemovitostKontakt);

  NemovitostKontakt putNemovitostKontakt(String idNemovitost, NemovitostKontakt nemovitostKontakt);

  NemovitostKontakt getNemovitostKontakt(String idNemovitost, String idNemovitostKontakt);

  List<NemovitostKontakt> getNemovitostKontaktAll(String idNemovitost, String idPrihlasenyUzivatel);

  void deleteNemovitostKontakt(String idNemovitost, String idNemovitostKontakt);

  void deleteNemovitostKontaktAll(String idNemovitost);
}
