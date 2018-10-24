package com.precise_service.project_one.service.najemnik.vyuctovani_pro_najemnika;

import java.util.List;

import com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika.VyuctovaniProNajemnika;

public interface IVyuctovaniProNajemnikaService {
  
  VyuctovaniProNajemnika postVyuctovaniProNajemnika(VyuctovaniProNajemnika vyuctovaniProNajemnika);

  VyuctovaniProNajemnika putVyuctovaniProNajemnika(VyuctovaniProNajemnika vyuctovaniProNajemnika);

  VyuctovaniProNajemnika getVyuctovaniProNajemnika(String idVyuctovaniProNajemnika);

  List<VyuctovaniProNajemnika> getVyuctovaniProNajemnikaAll();

  void deleteVyuctovaniProNajemnikaAll();
}
