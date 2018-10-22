package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.util.List;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntity;

public interface IVyuctovaniPolozkaTypService {

  VyuctovaniPolozkaTypEntity postVyuctovaniPolozkaTypEntity(VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity);

  VyuctovaniPolozkaTypEntity putVyuctovaniPolozkaTypEntity(VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity);

  VyuctovaniPolozkaTypEntity getVyuctovaniPolozkaTypEntity(String idVyuctovaniPolozkaTypEntity);

  List<VyuctovaniPolozkaTypEntity> getVyuctovaniPolozkaTypEntityAll();

  void deleteVyuctovaniPolozkaTypEntityAll();
}
