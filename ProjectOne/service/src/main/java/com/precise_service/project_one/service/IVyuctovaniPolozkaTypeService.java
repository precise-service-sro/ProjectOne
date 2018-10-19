package com.precise_service.project_one.service;

import java.util.List;

import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniPolozkaTypEntity;

public interface IVyuctovaniPolozkaTypeService {

  VyuctovaniPolozkaTypEntity getVyuctovaniPolozkaTypEntity(String id);

  VyuctovaniPolozkaTypEntity postVyuctovaniPolozkaTypEntity(VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity);

  List<VyuctovaniPolozkaTypEntity> getVyuctovaniPolozkaTypEntityList();
}
