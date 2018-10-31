package com.precise_service.project_one.service.osoba;

import java.util.List;

import com.precise_service.project_one.entity.osoba.Uzivatel;

public interface IUzivatelService {

  Uzivatel postUzivatel(Uzivatel uzivatel);

  Uzivatel putUzivatel(Uzivatel uzivatel);

  Uzivatel getUzivatel(String idUzivatel);

  List<Uzivatel> getUzivatelAll();

  void deleteUzivatelAll();

  void deleteUzivatel(String idUzivatel);
}
