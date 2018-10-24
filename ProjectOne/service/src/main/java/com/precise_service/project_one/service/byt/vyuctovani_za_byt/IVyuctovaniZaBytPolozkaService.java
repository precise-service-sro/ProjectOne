package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.util.List;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytPolozka;

public interface IVyuctovaniZaBytPolozkaService {

  VyuctovaniZaBytPolozka postVyuctovaniZaBytPolozka(VyuctovaniZaBytPolozka vyuctovaniZaBytPolozka);

  VyuctovaniZaBytPolozka putVyuctovaniZaBytPolozka(VyuctovaniZaBytPolozka vyuctovaniZaBytPolozka);

  VyuctovaniZaBytPolozka getVyuctovaniZaBytPolozka(String idVyuctovaniZaBytPolozka);

  List<VyuctovaniZaBytPolozka> getVyuctovaniZaBytPolozkaAll();

  List<VyuctovaniZaBytPolozka> getVyuctovaniZaBytPolozkaAll(String idVyuctovaniZaByt);

  void deleteVyuctovaniZaBytPolozka(String idVyuctovaniZaBytPolozka);

  void deleteVyuctovaniZaBytPolozkaAll();
}
