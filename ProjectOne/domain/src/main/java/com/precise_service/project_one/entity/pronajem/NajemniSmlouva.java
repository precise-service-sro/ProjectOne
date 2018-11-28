package com.precise_service.project_one.entity.pronajem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "najemniSmlouva")
public class NajemniSmlouva extends BaseEntity {

  @DBRef(lazy = true)
  @JsonProperty("pronajimatel")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Osoba pronajimatel;

  @DBRef(lazy = true)
  @JsonProperty("nemovitost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Nemovitost nemovitost;

  @DBRef(lazy = true)
  @JsonProperty("predavaciProtokol")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PredavaciProtokol predavaciProtokol;

  @DBRef(lazy = true)
  @JsonProperty("seznamNajemniku")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Osoba> seznamNajemniku = new ArrayList<>(0);

  @JsonProperty("datumPodpisu")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date datumPodpisu;

  @JsonProperty("platnost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private CasovyInterval platnost = new CasovyInterval();

  @JsonProperty("najemne")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo najemne = new Cislo();

  @JsonProperty("zalohy")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo zalohy = new Cislo();

  @JsonProperty("dokumentTyp")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private DokumentTyp dokumentTyp;
}
