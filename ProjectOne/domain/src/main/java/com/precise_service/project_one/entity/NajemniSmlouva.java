package com.precise_service.project_one.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Najemnik;
import com.precise_service.project_one.entity.osoba.Pronajimatel;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "najemniSmlouva")
public class NajemniSmlouva extends BaseEntity {

  @DBRef
  @JsonProperty("pronajimatel")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Pronajimatel pronajimatel;

  @DBRef
  @JsonProperty("nemovitost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Nemovitost nemovitost;

  @DBRef
  @JsonProperty("najemnik")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Najemnik najemnik;

  @JsonProperty("datumPodpisu")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date datumPodpisu;

  @JsonProperty("platnost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private CasovyInterval platnost;

  @JsonProperty("najemne")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo najemne;

  @JsonProperty("zalohy")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo zalohy;
}
