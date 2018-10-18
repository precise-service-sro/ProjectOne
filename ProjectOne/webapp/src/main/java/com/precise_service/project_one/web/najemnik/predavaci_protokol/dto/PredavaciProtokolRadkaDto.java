package com.precise_service.project_one.web.najemnik.predavaci_protokol.dto;

import lombok.Data;

@Data
public class PredavaciProtokolRadkaDto {
  private String nazev;
  private String popis;
  private String vyuctovatJako;
  private String cisloMeraku;
  private String stavMeraku;
  private String jednotka;
  private String formatovanyStavMeraku;
}
