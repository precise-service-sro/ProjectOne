package com.precise_service.project_one.service.pronajem;

import java.util.List;

import com.precise_service.project_one.entity.filter.DatovyFilter;
import com.precise_service.project_one.entity.pronajem.PlatbaNajemneho;
import com.precise_service.project_one.entity.osoba.Osoba;

public interface IPlatbaNajemnehoService {

  PlatbaNajemneho postPlatbaNajemneho(PlatbaNajemneho platbaNajemneho);

  PlatbaNajemneho putPlatbaNajemneho(PlatbaNajemneho platbaNajemneho);

  PlatbaNajemneho getPlatbaNajemneho(String idPlatbaNajemneho);

  List<PlatbaNajemneho> getPlatbaNajemnehoList(DatovyFilter datovyFilter);

  List<PlatbaNajemneho> getPlatbaNajemnehoList(Osoba prihlasenyUzivatel);

  void deletePlatbaNajemnehoAll();

  void deletePlatbaNajemneho(String idPlatbaNajemneho);
}
