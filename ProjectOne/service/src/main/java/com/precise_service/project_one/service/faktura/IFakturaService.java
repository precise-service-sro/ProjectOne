package com.precise_service.project_one.service.faktura;

import java.util.Date;
import java.util.List;

import com.precise_service.project_one.entity.faktura.Faktura;

public interface IFakturaService {

  Faktura postFaktura(Faktura faktura);

  Faktura putFaktura(Faktura faktura);

  Faktura getFaktura(String idFaktura);

  List<Faktura> getFakturaAll();

  List<Faktura> getFakturaListInRange(Date from, Date to);

  void deleteFaktura(String idFaktura);

  void deleteFakturaAll();
}
