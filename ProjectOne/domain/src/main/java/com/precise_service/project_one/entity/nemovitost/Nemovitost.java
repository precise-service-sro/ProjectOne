package com.precise_service.project_one.entity.nemovitost;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.Adresa;
import com.precise_service.project_one.entity.osoba.Osoba;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "nemovitost")
public class Nemovitost extends BaseEntity {

  @JsonProperty("nazev")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nazev;

  @JsonProperty("adresa")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Adresa adresa;

  @JsonProperty("poznamky")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String poznamky;

  @JsonProperty("nemovitostTyp")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private NemovitostTyp nemovitostTyp;

  @DBRef(lazy = true)
  @JsonProperty("vlastnik")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Osoba vlastnik;
}