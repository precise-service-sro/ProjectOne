package com.precise_service.project_one.entity.byt.vyuctovani;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CisloNaVyuctovaniEntity {

  @JsonProperty("mnozstvi")
  private Double mnozstvi;

  @JsonProperty("jednotka")
  private String jednotka;
}
