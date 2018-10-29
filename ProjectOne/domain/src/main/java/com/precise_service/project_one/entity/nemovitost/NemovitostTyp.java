package com.precise_service.project_one.entity.nemovitost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NemovitostTyp {
  BYT ("Byt v panelovém anebo cihlovém domě"),
  DŮM ("Rodinný dům"),
  POZEMEK ("Pozemek"),
  GARÁŽ ("Plnohodnotná uzamykatelná garáž"),
  PARKOVACÍ_STÁNÍ ("Vyhrazené parkovací místo"),
  ;

  private final String popis;

}
