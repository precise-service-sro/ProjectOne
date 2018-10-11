package com.precise_service.project_one.domain.vyuctovani;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolozkaVyuctovani {

  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("zalohy")
  private CisloNaVyuctovani zalohy;

  @JsonProperty("naklady")
  private CisloNaVyuctovani naklady;

  @JsonProperty("spotreba")
  private CisloNaVyuctovani spotreba;
}
