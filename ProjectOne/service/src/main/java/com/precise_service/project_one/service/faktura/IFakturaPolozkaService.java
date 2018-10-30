package com.precise_service.project_one.service.faktura;

import java.util.List;

import com.precise_service.project_one.entity.faktura.FakturaPolozka;

public interface IFakturaPolozkaService {

  FakturaPolozka postFakturaPolozka(FakturaPolozka fakturaPolozka);

  FakturaPolozka putFakturaPolozka(FakturaPolozka fakturaPolozka);

  FakturaPolozka getFakturaPolozka(String idFakturaPolozka);

  List<FakturaPolozka> getFakturaPolozkaAll();

  List<FakturaPolozka> getFakturaPolozkaAll(String idFaktura);

  void deleteFakturaPolozka(String idFakturaPolozka);

  void deleteFakturaPolozkaAll();
}
