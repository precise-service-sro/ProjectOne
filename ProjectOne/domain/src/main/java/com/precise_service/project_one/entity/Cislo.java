package com.precise_service.project_one.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cislo {

  public Cislo() {
    this.mnozstvi = 0.0;
    this.jednotka = "";
  }

  @JsonProperty("mnozstvi")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Double mnozstvi;

  @JsonProperty("jednotka")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String jednotka;
}
