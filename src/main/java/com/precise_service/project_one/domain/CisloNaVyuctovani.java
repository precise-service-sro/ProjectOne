package com.precise_service.project_one.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CisloNaVyuctovani {

  @JsonProperty("mnozstvi")
  private Double mnozstvi;

  @JsonProperty("jednotka")
  private String jednotka;
}
