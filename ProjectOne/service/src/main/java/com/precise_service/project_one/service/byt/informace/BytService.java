package com.precise_service.project_one.service.byt.informace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.byt.informace.Byt;
import com.precise_service.project_one.repository.byt.informace.BytRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BytService implements IBytService {

  @Autowired
  private BytRepository bytRepository;

  @Override
  public Byt postByt(Byt byt) {
    log.trace("postByt()");
    return bytRepository.save(byt);
  }

  @Override
  public Byt getByt(String idByt) {
    log.trace("getByt()");
    return bytRepository.findById(idByt).get();
  }

  @Override
  public List<Byt> getBytAll() {
    log.trace("getBytAll()");
    return bytRepository.findAll();
  }

  @Override
  public void deleteBytAll() {
    log.trace("deleteBytAll()");
    bytRepository.deleteAll();
  }
}
