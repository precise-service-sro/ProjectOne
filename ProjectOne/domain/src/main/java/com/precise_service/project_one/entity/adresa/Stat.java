package com.precise_service.project_one.entity.adresa;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Stat {
  CESKA_REPUBLIKA       ("Česká Republika"),
  SLOVENSKA_REPUBLIKA   ("Slovenská Republika"),
  ;

  private final String hodnota;
}
