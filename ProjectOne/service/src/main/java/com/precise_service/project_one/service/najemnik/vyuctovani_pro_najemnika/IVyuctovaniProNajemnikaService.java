package com.precise_service.project_one.service.najemnik.vyuctovani_pro_najemnika;

import java.util.List;

import com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika.VyuctovaniProNajemnikaEntity;

public interface IVyuctovaniProNajemnikaService {
  
  VyuctovaniProNajemnikaEntity postVyuctovaniProNajemnikaEntity(VyuctovaniProNajemnikaEntity vyuctovaniProNajemnikaEntity);

  VyuctovaniProNajemnikaEntity putVyuctovaniProNajemnikaEntity(VyuctovaniProNajemnikaEntity vyuctovaniProNajemnikaEntity);

  VyuctovaniProNajemnikaEntity getVyuctovaniProNajemnikaEntity(String idVyuctovaniProNajemnikaEntity);

  List<VyuctovaniProNajemnikaEntity> getVyuctovaniProNajemnikaEntityAll();

  void deleteVyuctovaniProNajemnikaEntityAll();
}
