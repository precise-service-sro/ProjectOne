package com.precise_service.project_one.entity.adresa;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Stat {
  CESKA_REPUBLIKA       ("Česká republika"),
  SLOVENSKA_REPUBLIKA   ("Slovenská republika"),
  ;

  private final String hodnota;
}
