package com.precise_service.project_one.entity.filter.typ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FakturaFilter {

  @JsonProperty("idFaktura")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idFaktura;

  @JsonProperty("idFakturaPolozka")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idFakturaPolozka;

  @JsonProperty("cisloUctu")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String cisloUctu;

  @JsonProperty("datumSplatnosti")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String datumSplatnosti;

  @JsonProperty("variabilniSymbol")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String variabilniSymbol;
}
