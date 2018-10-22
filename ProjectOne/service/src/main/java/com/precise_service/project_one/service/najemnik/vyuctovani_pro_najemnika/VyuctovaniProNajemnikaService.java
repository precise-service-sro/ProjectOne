package com.precise_service.project_one.service.najemnik.vyuctovani_pro_najemnika;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika.VyuctovaniProNajemnikaEntity;
import com.precise_service.project_one.repository.najemnik.vyuctovani_pro_najemnika.VyuctovaniProNajemnikaEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniProNajemnikaService implements IVyuctovaniProNajemnikaService {

  @Autowired
  private VyuctovaniProNajemnikaEntityRepository vyuctovaniProNajemnikaEntityRepository;


  @Override
  public VyuctovaniProNajemnikaEntity postVyuctovaniProNajemnikaEntity(VyuctovaniProNajemnikaEntity vyuctovaniProNajemnikaEntity) {
    log.trace("postVyuctovaniProNajemnikaEntity()");
    return vyuctovaniProNajemnikaEntityRepository.save(vyuctovaniProNajemnikaEntity);
  }

  @Override
  public VyuctovaniProNajemnikaEntity putVyuctovaniProNajemnikaEntity(VyuctovaniProNajemnikaEntity vyuctovaniProNajemnikaEntity) {
    log.trace("postVyuctovaniProNajemnikaEntity()");
    return vyuctovaniProNajemnikaEntityRepository.save(vyuctovaniProNajemnikaEntity);
  }

  @Override
  public VyuctovaniProNajemnikaEntity getVyuctovaniProNajemnikaEntity(String idVyuctovaniProNajemnikaEntity) {
    log.trace("getVyuctovaniProNajemnikaEntity()");
    return vyuctovaniProNajemnikaEntityRepository.findById(idVyuctovaniProNajemnikaEntity).get();
  }

  @Override
  public List<VyuctovaniProNajemnikaEntity> getVyuctovaniProNajemnikaEntityAll() {
    log.trace("getVyuctovaniProNajemnikaEntity()");
    return vyuctovaniProNajemnikaEntityRepository.findAll();
  }

  @Override
  public void deleteVyuctovaniProNajemnikaEntityAll() {
    log.trace("deleteVyuctovaniProNajemnikaEntityAll()");
    vyuctovaniProNajemnikaEntityRepository.deleteAll();
  }
}
