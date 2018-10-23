package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.time.LocalDate;
import java.util.List;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytEntity;

public interface IVyuctovaniZaBytService {

  VyuctovaniZaBytEntity postVyuctovaniZaBytEntity(VyuctovaniZaBytEntity vyuctovaniZaBytEntity);

  VyuctovaniZaBytEntity putVyuctovaniZaBytEntity(VyuctovaniZaBytEntity vyuctovaniZaBytEntity);

  VyuctovaniZaBytEntity getVyuctovaniZaBytEntity(String idVyuctovaniZaBytEntity);

  List<VyuctovaniZaBytEntity> getVyuctovaniZaBytEntityAll();

  List<VyuctovaniZaBytEntity> getVyuctovaniZaBytEntityInRange(LocalDate from, LocalDate to);

  void deleteVyuctovaniZaBytEntityAll();
}
