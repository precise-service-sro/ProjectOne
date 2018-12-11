package com.precise_service.project_one.service.faktura;

import java.util.List;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.filter.DataFilter;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;

public interface IFakturaService {

  Faktura postFaktura(Faktura faktura);

  Faktura zduplikovatFaktura(Faktura faktura);

  Faktura putFaktura(Faktura faktura);

  Faktura getFaktura(String idFaktura);

  List<Faktura> getFakturaList(DataFilter dataFilter);

  void deleteFaktura(String idFaktura);

  void deleteFakturaAll();
}
