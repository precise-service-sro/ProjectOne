package com.precise_service.project_one.service.osoba;

import java.util.List;

import com.precise_service.project_one.entity.osoba.Osoba;

public interface IOsobaService {

  Osoba postOsoba(Osoba osoba);

  Osoba putOsoba(Osoba osoba);

  Osoba getOsoba(String idOsoba);

  Osoba getOsobaByPrihlasovaciJmenoAndHeslo(String prihlasovaciJmeno, String heslo);

  List<Osoba> getOsobaAll(String idPrihlasenyUzivatel);

  List<Osoba> getOsobaAll();

  void deleteOsobaAll();

  void deleteOsoba(String idOsoba);
}
