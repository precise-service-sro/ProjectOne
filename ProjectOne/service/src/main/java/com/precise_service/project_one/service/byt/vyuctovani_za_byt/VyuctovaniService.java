package com.precise_service.project_one.service.byt.vyuctovani_za_byt;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniEntity;
import com.precise_service.project_one.repository.byt.vyuctovani_za_byt.VyuctovaniEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniService implements IVyuctovaniService {

  @Autowired
  private VyuctovaniEntityRepository vyuctovaniEntityRepository;

  @Override
  public void deleteVyuctovaniAll() {
    log.trace("deleteVyuctovaniAll()");
    vyuctovaniEntityRepository.deleteAll();
  }

  @Override
  public VyuctovaniEntity getVyuctovani(String id) {
    log.trace("getVyuctovani()");
    return vyuctovaniEntityRepository.findById(id).get();
  }

  @Override
  public VyuctovaniEntity postVyuctovani(VyuctovaniEntity vyuctovani) {
    log.trace("postVyuctovani()");
    return vyuctovaniEntityRepository.save(vyuctovani);
  }

  @Override
  public List<VyuctovaniEntity> getVyuctovaniInRange(LocalDate from, LocalDate to) {
    return vyuctovaniEntityRepository.getVyuctovaniInRange(from, to);
  }
}
