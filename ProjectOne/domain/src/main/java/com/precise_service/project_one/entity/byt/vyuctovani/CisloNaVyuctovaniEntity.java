package com.precise_service.project_one.entity.byt.vyuctovani;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CisloNaVyuctovaniEntity {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("pocatecniStav")
  private Double pocatecniStav;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("koncovyStav")
  private Double koncovyStav;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("mnozstvi")
  private Double mnozstvi;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("jednotka")
  private String jednotka;
}
