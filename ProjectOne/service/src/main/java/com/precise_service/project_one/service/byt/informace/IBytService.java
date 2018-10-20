package com.precise_service.project_one.service.byt.informace;

import java.util.List;

import com.precise_service.project_one.entity.byt.informace.BytEntity;

public interface IBytService {

  BytEntity postBytEntity(BytEntity bytEntity);

  BytEntity getBytEntity(String idBytEntity);

  List<BytEntity> getBytEntityAll();

  void deleteBytEntityAll();
}
