package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.time.LocalDate;
import java.util.List;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaByt;

public interface IVyuctovaniZaBytService {

  VyuctovaniZaByt postVyuctovaniZaByt(VyuctovaniZaByt vyuctovaniZaByt);

  VyuctovaniZaByt putVyuctovaniZaByt(VyuctovaniZaByt vyuctovaniZaByt);

  VyuctovaniZaByt getVyuctovaniZaByt(String idVyuctovaniZaByt);

  List<VyuctovaniZaByt> getVyuctovaniZaBytAll();

  List<VyuctovaniZaByt> getVyuctovaniZaBytInRange(LocalDate from, LocalDate to);

  void deleteVyuctovaniZaByt(String idVyuctovaniZaByt);

  void deleteVyuctovaniZaBytAll();
}
