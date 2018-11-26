package com.precise_service.project_one.service.pronajem;

import java.util.Date;
import java.util.List;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.pronajem.NajemniSmlouva;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.pronajem.PredavaciProtokol;
import com.precise_service.project_one.entity.pronajem.Vyuctovani;

public interface IVyuctovaniService {

  Vyuctovani postVyuctovani(Vyuctovani vyuctovani);

  Vyuctovani putVyuctovani(Vyuctovani vyuctovani);

  Vyuctovani getVyuctovani(String idVyuctovani);

  List<Vyuctovani> getVyuctovaniAll();

  List<Vyuctovani> getVyuctovaniInRange(Date from, Date to);

  List<Vyuctovani> getVyuctovaniListInRange(Osoba prihlasenyUzivatel, CasovyInterval zuctovaciObdobi);

  List<Vyuctovani> getVyuctovaniListInRange(Osoba prihlasenyUzivatel);

  void deleteVyuctovani(String idVyuctovani);

  void deleteVyuctovaniAll();

  Vyuctovani generovatVyuctovani(String nazev, CasovyInterval zuctovaciObdobi, Nemovitost nemovitost, Osoba najemnik, List<Faktura> fakturaList, PredavaciProtokol predavaciProtokol, NajemniSmlouva najemniSmlouva, Osoba prihlasenyUzivatel);
}
