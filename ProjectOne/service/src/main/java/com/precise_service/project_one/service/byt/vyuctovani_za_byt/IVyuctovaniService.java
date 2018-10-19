package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.time.LocalDate;
import java.util.List;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniEntity;

public interface IVyuctovaniService {

  /**
   * Complete database cleanup before new initialization
   * */
  void deleteVyuctovaniAll();

  VyuctovaniEntity getVyuctovani(String id);

  VyuctovaniEntity postVyuctovani(VyuctovaniEntity vyuctovani);

  List<VyuctovaniEntity> getVyuctovaniInRange(LocalDate from, LocalDate to);
}
