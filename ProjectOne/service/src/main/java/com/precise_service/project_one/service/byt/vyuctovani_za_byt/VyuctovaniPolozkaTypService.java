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
  public VyuctovaniPolozkaTypEntity postVyuctovaniPolozkaTypEntity(VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity) {
    log.trace("postVyuctovaniPolozkaTypEntity()");
    return vyuctovaniPolozkaTypEntityRepository.save(vyuctovaniPolozkaTypEntity);
  }

  @Override
  public VyuctovaniPolozkaTypEntity putVyuctovaniPolozkaTypEntity(VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity) {
    log.trace("putVyuctovaniPolozkaTypEntity()");
    return vyuctovaniPolozkaTypEntityRepository.save(vyuctovaniPolozkaTypEntity);
  }

  @Override
  public VyuctovaniPolozkaTypEntity getVyuctovaniPolozkaTypEntity(String idVyuctovaniPolozkaTypEntity) {
    log.trace("getVyuctovaniPolozkaTypEntity()");
    return vyuctovaniPolozkaTypEntityRepository.findById(idVyuctovaniPolozkaTypEntity).get();
  }

  @Override
  public List<VyuctovaniPolozkaTypEntity> getVyuctovaniPolozkaTypEntityAll() {
    log.trace("getVyuctovaniPolozkaTypEntityAll()");
    return vyuctovaniPolozkaTypEntityRepository.findAll();
  }

  @Override
  public void deleteVyuctovaniPolozkaTypEntity(String idVyuctovaniPolozkaTypEntity) {
    log.trace("deleteVyuctovaniPolozkaTypEntity()");
    vyuctovaniPolozkaTypEntityRepository.deleteById(idVyuctovaniPolozkaTypEntity);
  }

  @Override
  public void deleteVyuctovaniPolozkaTypEntityAll() {
    log.trace("deleteVyuctovaniPolozkaTypEntityAll()");
    vyuctovaniPolozkaTypEntityRepository.deleteAll();
  }
}
