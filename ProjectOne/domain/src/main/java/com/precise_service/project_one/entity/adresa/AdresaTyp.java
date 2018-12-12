package com.precise_service.project_one.entity.adresa;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AdresaTyp {
  ADRESA_NEMOVITOSTI          ("Adresa nemovitosti"),
  ADRESA_TRVALEHO_BYDLISTE    ("Trvalé bydliště"),
  POSTOVNI_DORUCOVACI_ADRESA  ("Poštovní doručovací adresa"),
  ;

  private final String hodnota;
}