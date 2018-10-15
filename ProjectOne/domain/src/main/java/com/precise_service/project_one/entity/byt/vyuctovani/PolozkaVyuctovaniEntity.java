package com.precise_service.project_one.entity.byt.vyuctovani;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolozkaVyuctovaniEntity {

  @JsonProperty("nazev")
  private String nazev;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("zalohy")
  private CisloNaVyuctovaniEntity zalohy;

  @JsonProperty("naklady")
  private CisloNaVyuctovaniEntity naklady;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("spotreba")
  private CisloNaVyuctovaniEntity spotreba;
}
