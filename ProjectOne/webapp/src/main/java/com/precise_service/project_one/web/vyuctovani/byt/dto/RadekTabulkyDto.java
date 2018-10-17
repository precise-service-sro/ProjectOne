package com.precise_service.project_one.web.vyuctovani.byt.dto;

import lombok.Data;

@Data
public class RadekTabulkyDto {
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
