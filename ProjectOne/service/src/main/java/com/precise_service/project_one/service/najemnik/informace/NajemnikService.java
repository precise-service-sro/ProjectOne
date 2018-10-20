package com.precise_service.project_one.service.najemnik.informace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.najemnik.informace.NajemnikEntity;
import com.precise_service.project_one.repository.najemnik.informace.NajemnikEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NajemnikService implements INajemnikService {

  @Autowired
  private NajemnikEntityRepository najemnikEntityRepository;

  @Override
  public NajemnikEntity postNajemnikEntity(NajemnikEntity najemnikEntity) {
    log.trace("postNajemnikEntity()");
    return najemnikEntityRepository.save(najemnikEntity);
  }

  @Override
  public NajemnikEntity getNajemnikEntity(String idNajemnikEntity) {
    log.trace("getNajemnikEntity()");
    return najemnikEntityRepository.findById(idNajemnikEntity).get();
  }

  @Override
  public List<NajemnikEntity> getNajemnikEntityAll() {
    log.trace("getNajemnikEntityAll()");
    return najemnikEntityRepository.findAll();
  }

  @Override
  public void deleteNajemnikEntityAll() {
    log.trace("deleteNajemnikEntityAll()");
    najemnikEntityRepository.deleteAll();
  }
}
