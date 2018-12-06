package com.precise_service.project_one.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SouborTyp {
  AVATAR_FOTO                     ("Avatar / Foto", "Osobní fotka uživatele"),

  FOTOGRAFIE_NEMOVITOSTI          ("Fotografie nemovitosti", "Fotografie nemovitosti"),

  NAJEMNI_SMLOUVA                 ("", ""),
  PREDAVACI_PROTOKOL              ("", ""),
  DOKLAD_O_POJISTENI_ODPOVEDNOSTI ("", ""),
  DOKLAD_O_POJISTENI_DOMACNOSTI   ("", ""),

  OBCANSKY_PRUKAZ                 ("", ""),
  CESTOVNI_PAS                    ("", ""),
  PRUKAZ_TOTOZNOSTI               ("", ""),

  FAKTURA                         ("Faktura", "Faktrura za energie anebo služby"),
  VYUCTOVANI_PRO_NAJEMNIKA        ("", ""),

  KUPNI_SMLOUVA                   ("", ""),
  VYPIS_Z_KATASTRU                ("", ""),
  DOKLAD_O_NEMOVITOSTI            ("", ""),


  BANKOVNI_VYPIS                  ("", ""),
  ;

  private final String hodnota;
  private final String popis;
}
