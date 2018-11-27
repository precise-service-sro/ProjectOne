package com.precise_service.project_one.entity.osoba;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.adresa.Adresa;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "osoba")
public class Osoba extends BaseEntity {

  @JsonProperty("avatarFotoObjectId")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private ObjectId avatarFotoObjectId;

  @JsonProperty("prihlasovaciJmeno")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String prihlasovaciJmeno;

  @JsonProperty("heslo")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String heslo;

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
    return "" + (StringUtils.isNotBlank(jmeno) ? jmeno : "") + (StringUtils.isNotBlank(prijmeni) ? " " + prijmeni : "");
  }

  @JsonProperty("datumNarozeni")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date datumNarozeni;

  @JsonProperty("rodneCislo")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String rodneCislo;

  @JsonProperty("trvaleBydliste")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Adresa trvaleBydliste;

  public Adresa getTrvaleBydliste() {
    if (trvaleBydliste == null) {
      trvaleBydliste = new Adresa();
    }
    return trvaleBydliste;
  }

  @JsonProperty("zamestnani")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String zamestnani;

  @JsonProperty("cisloOP")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String cisloOP;

  @JsonProperty("poznamky")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String poznamky;

  @JsonProperty("telefon")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String telefon;

  @JsonProperty("email")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String email;

  @JsonProperty("bankovniUcet")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String bankovniUcet;

  @JsonProperty("muzeSePrihlasit")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Boolean muzeSePrihlasit;
}
