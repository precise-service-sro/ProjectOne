package com.precise_service.project_one.entity.nemovitost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NemovitostTyp {
  BYT             ("Byt",             "Byt v panelovém anebo cihlovém domě"),
  DŮM             ("Dům",             "Rodinný dům"),
  POZEMEK         ("Pozemek",         "Pozemek"),
  GARÁŽ           ("Garáž",           "Plnohodnotná uzamykatelná garáž"),
  PARKOVACÍ_STÁNÍ ("Parkovací stání", "Vyhrazené parkovací místo"),
  ;

  private final String hodnota;
  private final String popis;

}
