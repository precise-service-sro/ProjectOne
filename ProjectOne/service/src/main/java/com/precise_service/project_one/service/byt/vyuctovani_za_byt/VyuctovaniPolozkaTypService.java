package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.repository.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniPolozkaTypService implements IVyuctovaniPolozkaTypService {

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

  @Override
  public List<VyuctovaniPolozkaTypEntity> getVyuctovaniPolozkaTypEntityList() {
    log.trace("getVyuctovaniPolozkaTypEntityList()");
    return vyuctovaniPolozkaTypEntityRepository.findAll();
  }
}
