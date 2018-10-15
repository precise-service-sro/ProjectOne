package com.precise_service.project_one.service;

import java.time.LocalDate;
import java.util.List;

import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniPolozkaEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniEntity;

public interface IVyuctovaniService {

  /**
   * Complete database cleanup before new initialization
   * */
  void deleteVyuctovaniAll();

  VyuctovaniEntity getVyuctovani(String id);

  VyuctovaniEntity postVyuctovani(VyuctovaniEntity vyuctovani);

  List<VyuctovaniPolozkaEntity> getSeznamPolozek();

  List<VyuctovaniEntity> getVyuctovaniInRange(LocalDate from, LocalDate to);
}
