package com.precise_service.project_one.service.faktura;

import java.util.List;

import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.faktura.FakturaPolozka;

public interface IFakturaPolozkaService {

  FakturaPolozka postFakturaPolozka(FakturaPolozka fakturaPolozka);

  FakturaPolozka zduplikovatFakturaPolozka(FakturaPolozka fakturaPolozka);

  void zduplikovatFakturaPolozkaList(String idFakturaOriginal, Faktura novaFaktura);

  FakturaPolozka putFakturaPolozka(FakturaPolozka fakturaPolozka);

  FakturaPolozka getFakturaPolozka(String idFakturaPolozka);

  List<FakturaPolozka> getFakturaPolozkaAll();

  List<FakturaPolozka> getFakturaPolozkaAll(String idFaktura);

  void deleteFakturaPolozka(String idFakturaPolozka);

  void deleteFakturaPolozkaAll();
}
