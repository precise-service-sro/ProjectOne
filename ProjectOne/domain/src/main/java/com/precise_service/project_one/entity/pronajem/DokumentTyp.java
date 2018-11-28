package com.precise_service.project_one.entity.pronajem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DokumentTyp {

  NAJEMNI_SMLOUVA                                 ("Nájemní smlouva", "Nájemní smlouva"),
  NAJEMNI_SMLOUVA_DODATEK                         ("Dodatek k nájemní smlouvě", "Dodatek k nájemní smlouvě (k jake?)"),
  NAJEMNI_SMLOUVA_UPOMINKA                        ("Upomínka k nájemní smlouvě", "Upomínka k nájemní smlouvě"),
  NAJEMNI_SMLOUVA_VYPOVED                         ("Výpověď nájemní smlouvy", "Výpověď nájemní smlouvy"),

  POTVRZENI_O_POJISTENI_NEMOVITOSTI_PRONAJIMATEL  ("Potvrzení o pojištění nemovitosti", "Potvrzení o pojištění nemovitosti (pronajimatel)"),
  POTVRZENI_O_POJISTENI_DOMACNOSTI_NAJEMNIK       ("Potvrzení o pojištění domácnosti", "Potvrzení o pojištění domácnosti (najemnik)"),
  POTVRZENI_O_POJISTENI_ODPOVEDNOSTI_NAJEMNIK     ("Potvrzení o pojištění odpovědnosti", "Potvrzení o pojištění odpovědnosti (najemnik)"),

  PREDAVACI_PROTOKOL_ZACATEK_NAJMU                ("Předávací protokol", "Předávací protokol k začátku pronájmu"),
  PREBIRACI_PROTOKOL_KONEC_NAJMU                  ("Přebírací protokol", "Přebírací protokol k ukončení pronájmu"),

  // VYPIS_Z_KATASTRU
  // OSOBNI_DOKLADY (OP, ŘP, PAS, POVOLENÍ_K_POBYTU)
  //

  ;

  private final String nazev;
  private final String popis;

}
