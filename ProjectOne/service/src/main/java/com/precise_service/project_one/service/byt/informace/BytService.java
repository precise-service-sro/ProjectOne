package com.precise_service.project_one.service.byt.informace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.byt.informace.BytEntity;
import com.precise_service.project_one.repository.byt.informace.BytEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BytService implements IBytService {

  @Autowired
  private BytEntityRepository bytEntityRepository;

  @Override
  public BytEntity postBytEntity(BytEntity bytEntity) {
    log.trace("postBytEntity()");
    return bytEntityRepository.save(bytEntity);
  }

  @Override
  public BytEntity getBytEntity(String idBytEntity) {
    log.trace("getBytEntity()");
    return bytEntityRepository.findById(idBytEntity).get();
  }

  @Override
  public List<BytEntity> getBytEntityAll() {
    log.trace("getBytEntityAll()");
    return bytEntityRepository.findAll();
  }

  @Override
  public void deleteBytEntityAll() {
    log.trace("deleteBytEntityAll()");
    bytEntityRepository.deleteAll();
  }
}
