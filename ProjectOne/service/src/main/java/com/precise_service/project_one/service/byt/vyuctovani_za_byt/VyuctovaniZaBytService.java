package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytEntity;
import com.precise_service.project_one.repository.byt.vyuctovani_za_byt.VyuctovaniZaBytEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniZaBytService implements IVyuctovaniZaBytService {

  @Autowired
  private VyuctovaniZaBytEntityRepository vyuctovaniZaBytEntityRepository;

  @Override
  public VyuctovaniZaBytEntity postVyuctovaniZaBytEntity(VyuctovaniZaBytEntity vyuctovaniZaBytEntity) {
    log.trace("postVyuctovaniZaBytEntity()");
    return vyuctovaniZaBytEntityRepository.save(vyuctovaniZaBytEntity);
  }

  @Override
  public VyuctovaniZaBytEntity putVyuctovaniZaBytEntity(VyuctovaniZaBytEntity vyuctovaniZaBytEntity) {
    log.trace("putVyuctovaniZaBytEntity()");
    return vyuctovaniZaBytEntityRepository.save(vyuctovaniZaBytEntity);
  }

  @Override
  public VyuctovaniZaBytEntity getVyuctovaniZaBytEntity(String idVyuctovaniZaBytEntity) {
    log.trace("getVyuctovaniZaBytEntity()");
    return vyuctovaniZaBytEntityRepository.findById(idVyuctovaniZaBytEntity).get();
  }

  @Override
  public List<VyuctovaniZaBytEntity> getVyuctovaniZaBytEntityAll() {
    return vyuctovaniZaBytEntityRepository.findAll();
  }

  @Override
  public List<VyuctovaniZaBytEntity> getVyuctovaniZaBytEntityInRange(LocalDate from, LocalDate to) {
    return vyuctovaniZaBytEntityRepository.getVyuctovaniZaBytEntityInRange(from, to);
  }

  @Override
  public void deleteVyuctovaniZaBytEntityAll() {
    log.trace("deleteVyuctovaniZaBytEntityAll()");
    vyuctovaniZaBytEntityRepository.deleteAll();
  }
}
