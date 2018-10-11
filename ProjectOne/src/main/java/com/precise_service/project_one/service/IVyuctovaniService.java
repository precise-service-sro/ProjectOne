package com.precise_service.project_one.service;

import java.util.List;

import com.precise_service.project_one.domain.vyuctovani.PolozkaVyuctovani;
import com.precise_service.project_one.domain.vyuctovani.Vyuctovani;

public interface IVyuctovaniService {

  /**
   * Complete database cleanup before new initialization
   * */
  void deleteVyuctovaniAll();

  Vyuctovani getVyuctovani(String id);

  void createVyuctovani(Vyuctovani vyuctovani);

  List<PolozkaVyuctovani> getSeznamPolozek();
}
