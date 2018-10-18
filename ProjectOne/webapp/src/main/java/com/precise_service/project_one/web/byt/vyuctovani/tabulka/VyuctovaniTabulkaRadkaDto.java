package com.precise_service.project_one.web.byt.vyuctovani.tabulka;

import java.io.Serializable;

import lombok.Data;

@Data
public class VyuctovaniTabulkaRadkaDto implements Serializable {
  private String nazev;
  private String popis;
  private String vyuctovatJako;
  private String spotrebaJednotka;

  // spotreba
  private Double pocatecniStav;
  private Double koncovyStav;
  private Double spotrebaMnozstvi;

  private Double zalohy;
  private Double naklady;
  private Double rozdil;
}
