package com.precise_service.project_one.entity.nemovitost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NemovitostDispozice {
  JEDNA_PLUS_KK     ("1+kk", "1+kk (Garsonka)"),
  JEDNA_PLUS_JEDNA  ("1+1", ""),

  DVA_PLUS_KK       ("2+kk", ""),
  DVA_PLUS_JEDNA    ("2+1", ""),

  TRI_PLUS_KK       ("3+kk", ""),
  TRI_PLUS_JEDNA    ("3+1", ""),

  CTYRI_PLUS_KK     ("4+kk", ""),
  CTYRI_PLUS_JEDNA  ("4+1", ""),

  PET_PLUS_KK       ("5+kk", ""),
  PET_PLUS_JEDNA    ("5+1", ""),
  ;

  private final String hodnota;
  private final String popis;
}
