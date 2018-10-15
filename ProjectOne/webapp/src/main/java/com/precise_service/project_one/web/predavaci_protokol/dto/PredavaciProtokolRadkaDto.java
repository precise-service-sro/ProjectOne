package com.precise_service.project_one.web.predavaci_protokol.dto;

import lombok.Data;

@Data
public class PredavaciProtokolRadkaDto {
  private String nazev;
  private Double spotrebaMnozstvi;
  private String spotrebaJednotka;

  private Double zalohy;
  private Double naklady;
  private Double rozdil;
}
