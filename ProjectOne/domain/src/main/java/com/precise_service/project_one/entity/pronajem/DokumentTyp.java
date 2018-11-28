package com.precise_service.project_one.entity.pronajem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DokumentTyp {

  NAJEMNI_SMLOUVA                                 ("Nájemní smlouva"),
  NAJEMNI_SMLOUVA_DODATEK                         ("Dodatek k nájemní smlouvě"),
  NAJEMNI_SMLOUVA_UPOMINKA                        ("Upomínka k nájemní smlouvě"),
  NAJEMNI_SMLOUVA_VYPOVED                         ("Výpověď nájemní smlouvy"),

  POTVRZENI_O_POJISTENI_NEMOVITOSTI_PRONAJIMATEL  (""),
  POTVRZENI_O_POJISTENI_DOMACNOSTI_NAJEMNIK       (""),
  POTVRZENI_O_POJISTENI_ODPOVEDNOSTI_NAJEMNIK     (""),

  PREDAVACI_PROTOKOL_ZACATEK_NAJMU                ("Předávací protokol"),
  PREBIRACI_PROTOKOL_KONEC_NAJMU                  ("Přebírací protokol"),

  ;

  private final String popis;

}
