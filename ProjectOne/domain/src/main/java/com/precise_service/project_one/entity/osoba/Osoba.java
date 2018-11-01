package com.precise_service.project_one.entity.osoba;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "osoba")
public class Osoba extends BaseEntity {

  @JsonProperty("username")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String username;

  @JsonProperty("password")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String password;

  @JsonProperty("fotka")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String fotka;

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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
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

  @JsonProperty("muzeSePrihlasit")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Boolean muzeSePrihlasit;
}
