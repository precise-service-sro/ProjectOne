package com.precise_service.project_one.service.vyuctovani;

import java.time.LocalDate;
import java.util.List;

import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;

public interface IVyuctovaniService {

  Vyuctovani postVyuctovani(Vyuctovani vyuctovani);

  Vyuctovani putVyuctovani(Vyuctovani vyuctovani);

  Vyuctovani getVyuctovani(String idVyuctovani);

  List<Vyuctovani> getVyuctovaniAll();

  List<Vyuctovani> getVyuctovaniInRange(LocalDate from, LocalDate to);

  void deleteVyuctovani(String idVyuctovani);

  void deleteVyuctovaniAll();
}
