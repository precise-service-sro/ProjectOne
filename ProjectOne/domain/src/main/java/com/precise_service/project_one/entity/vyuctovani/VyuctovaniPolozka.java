package com.precise_service.project_one.entity.vyuctovani;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.PolozkaTyp;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vyuctovaniPolozka")
public class VyuctovaniPolozka extends BaseEntity {

  @DBRef(lazy = true)
  @JsonProperty("polozkaTyp")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PolozkaTyp polozkaTyp;

  @JsonProperty("zuctovaciObdobi")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private CasovyInterval zuctovaciObdobi;

  @JsonProperty("pocatecniStav")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo pocatecniStav;

  @JsonProperty("koncovyStav")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo koncovyStav;

  @JsonProperty("spotreba")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo spotreba;

  @JsonProperty("naklady")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo naklady;

  @DBRef(lazy = true)
  @JsonProperty("vyuctovani")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Vyuctovani vyuctovani;
}
