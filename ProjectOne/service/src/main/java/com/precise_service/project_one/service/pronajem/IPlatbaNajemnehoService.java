package com.precise_service.project_one.service.pronajem;

import java.util.List;

import com.precise_service.project_one.entity.filter.DataFilter;
import com.precise_service.project_one.entity.pronajem.PlatbaNajemneho;

public interface IPlatbaNajemnehoService {

  PlatbaNajemneho postPlatbaNajemneho(PlatbaNajemneho platbaNajemneho);

  PlatbaNajemneho putPlatbaNajemneho(PlatbaNajemneho platbaNajemneho);

  PlatbaNajemneho getPlatbaNajemneho(String idPlatbaNajemneho);

  List<PlatbaNajemneho> getPlatbaNajemnehoList(DataFilter dataFilter);

  void deletePlatbaNajemnehoAll();

  void deletePlatbaNajemneho(String idPlatbaNajemneho);
}
