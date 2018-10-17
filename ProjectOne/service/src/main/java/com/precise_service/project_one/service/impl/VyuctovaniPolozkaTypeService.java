package com.precise_service.project_one.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.repository.VyuctovaniPolozkaTypEntityRepository;
import com.precise_service.project_one.service.IVyuctovaniPolozkaTypeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniPolozkaTypeService implements IVyuctovaniPolozkaTypeService {

  @Autowired
  private VyuctovaniPolozkaTypEntityRepository vyuctovaniPolozkaTypEntityRepository;

  @Override
  public VyuctovaniPolozkaTypEntity getVyuctovaniPolozkaTypEntity(String id) {
    log.trace("getVyuctovaniPolozkaTypEntity()");
    return vyuctovaniPolozkaTypEntityRepository.findById(id).get();
  }

  @Override
  public VyuctovaniPolozkaTypEntity postVyuctovaniPolozkaTypEntity(VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity) {
    log.trace("postVyuctovaniPolozkaTypEntity()");
    return vyuctovaniPolozkaTypEntityRepository.save(vyuctovaniPolozkaTypEntity);
  }
}
