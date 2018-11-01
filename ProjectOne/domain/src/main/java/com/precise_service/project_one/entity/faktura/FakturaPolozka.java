package com.precise_service.project_one.entity.faktura;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.PolozkaTyp;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "fakturaPolozka")
public class FakturaPolozka extends BaseEntity {

  @JsonProperty("nazev")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nazev;

  @DBRef(lazy = true)
  @JsonProperty("faktura")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Faktura faktura;

  @DBRef(lazy = true)
  @JsonProperty("polozkaTyp")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PolozkaTyp polozkaTyp;

  @JsonProperty("pocatecniStav")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo pocatecniStav;

  @JsonProperty("koncovyStav")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo koncovyStav;

  @JsonProperty("spotreba")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo spotreba;

  @JsonProperty("zalohy")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo zalohy;

  @JsonProperty("naklady")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo naklady;

  @JsonProperty("rozdil")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo rozdil;
}
