package com.precise_service.project_one.service.najemnik.informace;

import java.util.List;

import com.precise_service.project_one.entity.najemnik.informace.NajemnikEntity;

public interface INajemnikService {

  NajemnikEntity postNajemnikEntity(NajemnikEntity najemnikEntity);

  NajemnikEntity getNajemnikEntity(String idNajemnikEntity);

  List<NajemnikEntity> getNajemnikEntityAll();

  void deleteNajemnikEntityAll();
}
