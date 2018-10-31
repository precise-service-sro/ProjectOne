package com.precise_service.project_one.service.vyuctovani;

import java.util.List;

import com.precise_service.project_one.entity.PolozkaTyp;

public interface IPolozkaTypService {

  PolozkaTyp postPolozkaTyp(PolozkaTyp polozkaTyp);

  PolozkaTyp putPolozkaTyp(PolozkaTyp polozkaTyp);

  PolozkaTyp getPolozkaTyp(String idPolozkaTyp);

  List<PolozkaTyp> getPolozkaTypAll();

  void deletePolozkaTyp(String idPolozkaTyp);

  void deletePolozkaTypAll();

  List<PolozkaTyp> getPolozkaTypListByIdNemovitost(String idNemovitost);
}
