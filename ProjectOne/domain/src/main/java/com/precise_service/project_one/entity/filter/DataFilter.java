package com.precise_service.project_one.entity.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.CasovyInterval;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class DataFilter {

  @JsonProperty("idPrihlasenyUzivatel")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idPrihlasenyUzivatel;

  @JsonProperty("casovyInterval")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private CasovyInterval casovyInterval;

  @JsonProperty("idFaktura")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idFaktura;

  @JsonProperty("idFakturaPolozka")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idFakturaPolozka;

  @JsonProperty("idNemovitost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idNemovitost;

  @JsonProperty("idNemovitostKontakt")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idNemovitostKontakt;

  @JsonProperty("idOsoba")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String isOsoba;

  @JsonProperty("idPlatbaNajemneho")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idPlatbaNajemneho;
}