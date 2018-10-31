package com.precise_service.project_one.entity.predavaci_protokol;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.Najemnik;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "predavaciProtokol")
public class PredavaciProtokol extends BaseEntity {

  @JsonProperty("nazev")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nazev;

  @JsonProperty("datumPodpisu")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate datumPodpisu;

  @DBRef
  @JsonProperty("nemovitost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Nemovitost nemovitost;

  @DBRef
  @JsonProperty("najemnik")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Najemnik najemnik;

  public String getPopis() {
    return "" + nazev + ", " + najemnik.getCeleJmeno() + ", " + datumPodpisu;
  }
}
