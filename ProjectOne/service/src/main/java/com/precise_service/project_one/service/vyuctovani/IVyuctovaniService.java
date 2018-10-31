package com.precise_service.project_one.service.vyuctovani;

import java.util.Date;
import java.util.List;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Najemnik;
import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;

public interface IVyuctovaniService {

  Vyuctovani postVyuctovani(Vyuctovani vyuctovani);

  Vyuctovani putVyuctovani(Vyuctovani vyuctovani);

  Vyuctovani getVyuctovani(String idVyuctovani);

  List<Vyuctovani> getVyuctovaniAll();

  List<Vyuctovani> getVyuctovaniInRange(Date from, Date to);

  void deleteVyuctovani(String idVyuctovani);

  void deleteVyuctovaniAll();

  Vyuctovani generovatVyuctovani(String nazev, CasovyInterval zuctovaciObdobi, Nemovitost nemovitost, Najemnik najemnik, List<Faktura> filteredFakturaList);
}
