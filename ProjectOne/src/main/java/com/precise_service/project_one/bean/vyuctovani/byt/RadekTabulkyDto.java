package com.precise_service.project_one.bean.vyuctovani.byt;

import lombok.Data;

@Data
public class RadekTabulkyDto {
  private String nazev;
  private Double spotrebaMnozstvi;
  private String spotrebaJednotka;

  private Double zalohy;
  private Double naklady;
  private Double rozdil;
}
