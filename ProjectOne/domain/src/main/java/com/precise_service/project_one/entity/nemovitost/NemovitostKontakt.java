package com.precise_service.project_one.entity.nemovitost;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.osoba.Osoba;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "nemovitostKontakt")
public class NemovitostKontakt extends BaseEntity {

  @JsonProperty("jmeno")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String jmeno;

  @JsonProperty("prijmeni")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String prijmeni;

  public String getCeleJmeno(){
    return "" + jmeno + " " + prijmeni;
  }

  @JsonProperty("telefon")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String telefon;

  @JsonProperty("email")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String email;

  @JsonProperty("poznamky")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String poznamky;

  @DBRef(lazy = true)
  @JsonProperty("nemovitost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Nemovitost nemovitost;
}