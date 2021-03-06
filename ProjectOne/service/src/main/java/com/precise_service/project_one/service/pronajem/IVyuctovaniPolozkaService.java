package com.precise_service.project_one.service.pronajem;

import java.util.List;

import com.precise_service.project_one.entity.pronajem.VyuctovaniPolozka;

public interface IVyuctovaniPolozkaService {

  VyuctovaniPolozka postVyuctovaniPolozka(VyuctovaniPolozka vyuctovaniPolozka);

  VyuctovaniPolozka putVyuctovaniPolozka(VyuctovaniPolozka vyuctovaniPolozka);

  VyuctovaniPolozka getVyuctovaniPolozka(String idVyuctovaniPolozka);

  List<VyuctovaniPolozka> getVyuctovaniPolozkaList(String idPolozkaTyp, String idVyuctovani);

  List<VyuctovaniPolozka> getVyuctovaniPolozkaAll();

  List<VyuctovaniPolozka> getVyuctovaniPolozkaAll(String idVyuctovani);

  List<VyuctovaniPolozka> getVypocitanaVyuctovaniPolozkaList(String idVyuctovani);

  void deleteVyuctovaniPolozka(String idVyuctovaniPolozka);

  void deleteVyuctovaniPolozkaAll();

  void deleteVyuctovaniPolozkaAll(String idVyuctovani);
}
