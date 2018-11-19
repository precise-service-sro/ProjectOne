package com.precise_service.project_one.service.vyuctovani;

import java.util.List;

import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozka;

public interface IVyuctovaniPolozkaService {

  VyuctovaniPolozka postVyuctovaniPolozka(VyuctovaniPolozka vyuctovaniPolozka);

  VyuctovaniPolozka putVyuctovaniPolozka(VyuctovaniPolozka vyuctovaniPolozka);

  VyuctovaniPolozka getVyuctovaniPolozka(String idVyuctovaniPolozka);

  List<VyuctovaniPolozka> getVyuctovaniPolozkaList(String idPolozkaTyp, String idVyuctovani);

  List<VyuctovaniPolozka> getVyuctovaniPolozkaAll();

  List<VyuctovaniPolozka> getVyuctovaniPolozkaAll(String idVyuctovani);

  void deleteVyuctovaniPolozka(String idVyuctovaniPolozka);

  void deleteVyuctovaniPolozkaAll();
}
