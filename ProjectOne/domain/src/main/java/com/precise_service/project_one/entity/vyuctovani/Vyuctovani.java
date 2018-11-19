package com.precise_service.project_one.entity.vyuctovani;

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
import com.precise_service.project_one.entity.NajemniSmlouva;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vyuctovani")
public class Vyuctovani extends BaseEntity {

  @JsonProperty("nazev")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nazev;

  @JsonProperty("zuctovaciObdobi")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private CasovyInterval zuctovaciObdobi;

  @DBRef(lazy = true)
  @JsonProperty("nemovitost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Nemovitost nemovitost;

  @DBRef(lazy = true)
  @JsonProperty("najemnik")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Osoba najemnik;

  @DBRef(lazy = true)
  @JsonProperty("najemniSmlouva")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private NajemniSmlouva najemniSmlouva;

  @DBRef(lazy = true)
  @JsonProperty("seznamVychozichFaktur")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Faktura> seznamVychozichFaktur;

  @DBRef(lazy = true)
  @JsonProperty("predavaciProtokol")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PredavaciProtokol predavaciProtokol;

  @JsonProperty("datumVystaveni")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date datumVystaveni;
}
