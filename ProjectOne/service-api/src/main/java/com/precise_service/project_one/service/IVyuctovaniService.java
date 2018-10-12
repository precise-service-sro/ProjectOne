package com.precise_service.project_one.service;

import java.util.List;

import com.precise_service.project_one.entity.vyuctovani.PolozkaVyuctovani;
import com.precise_service.project_one.entity.vyuctovani.VyuctovaniEntity;

public interface IVyuctovaniService {

  /**
   * Complete database cleanup before new initialization
   * */
  void deleteVyuctovaniAll();

  VyuctovaniEntity getVyuctovani(String id);

  VyuctovaniEntity postVyuctovani(VyuctovaniEntity vyuctovani);

  List<PolozkaVyuctovani> getSeznamPolozek();
}
