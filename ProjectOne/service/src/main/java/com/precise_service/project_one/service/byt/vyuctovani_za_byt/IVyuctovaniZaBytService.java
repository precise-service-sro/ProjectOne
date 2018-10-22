package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.time.LocalDate;
import java.util.List;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniEntity;

public interface IVyuctovaniZaBytService {

  VyuctovaniEntity postVyuctovaniZaBytEntity(VyuctovaniEntity vyuctovaniZaBytEntity);

  VyuctovaniEntity putVyuctovaniZaBytEntity(VyuctovaniEntity vyuctovaniZaBytEntity);

  VyuctovaniEntity getVyuctovaniZaBytEntity(String idVyuctovaniZaBytEntity);

  List<VyuctovaniEntity> getVyuctovaniZaBytEntityAll();

  List<VyuctovaniEntity> getVyuctovaniZaBytEntityInRange(LocalDate from, LocalDate to);

  void deleteVyuctovaniZaBytEntityAll();
}
