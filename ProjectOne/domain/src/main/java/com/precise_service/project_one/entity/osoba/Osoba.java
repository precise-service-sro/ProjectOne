package com.precise_service.project_one.entity.osoba;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Osoba extends BaseEntity {

  @JsonProperty("jmeno")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String jmeno;

  @JsonProperty("prijmeni")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String prijmeni;

  public String getCeleJmeno(){
    return "" + jmeno + " " + prijmeni;
  }

  @JsonProperty("datumNarozeni")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Date datumNarozeni;

  @JsonProperty("poznamky")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String poznamky;

  @JsonProperty("telefon")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String telefon;

  @JsonProperty("email")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String email;
}
