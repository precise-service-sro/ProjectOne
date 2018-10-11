package com.precise_service.project_one.bean.vyuctovani;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class PolozkaVyuctovaniBean {
  private final String nazev;
  private final CisloNaVyuctovaniBean zalohy;
  private final CisloNaVyuctovaniBean naklady;
  private final CisloNaVyuctovaniBean spotreba;
}
