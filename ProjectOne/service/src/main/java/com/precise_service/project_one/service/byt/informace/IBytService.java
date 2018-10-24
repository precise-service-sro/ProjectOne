package com.precise_service.project_one.service.byt.informace;

import java.util.List;

import com.precise_service.project_one.entity.byt.informace.Byt;

public interface IBytService {

  Byt postByt(Byt byt);

  Byt getByt(String idByt);

  List<Byt> getBytAll();

  void deleteBytAll();
}
