package com.precise_service.project_one.service.faktura;

import java.util.List;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.osoba.Osoba;

public interface IFakturaService {

  Faktura postFaktura(Faktura faktura);

  Faktura putFaktura(Faktura faktura);

  Faktura getFaktura(String idFaktura);

  List<Faktura> getFakturaAll();

  List<Faktura> getSeznamFakturVeZuctovacimObdobi(Osoba prihlasenyUzivatel, CasovyInterval zuctovaciObdobi);

  List<Faktura> getSeznamFaktur(Osoba prihlasenyUzivatel);

  void deleteFaktura(String idFaktura);

  void deleteFakturaAll();
}
