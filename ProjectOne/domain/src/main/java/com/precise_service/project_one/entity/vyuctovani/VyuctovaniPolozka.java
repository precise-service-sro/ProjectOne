package com.precise_service.project_one.entity.vyuctovani;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vyuctovaniPolozka")
public class VyuctovaniPolozka extends BaseEntity {

  @JsonProperty("nazev")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nazev;

  @DBRef
  @JsonProperty("vyuctovani")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Vyuctovani vyuctovani;

  @DBRef
  @JsonProperty("vyuctovaniPolozkaTyp")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp;

  @JsonProperty("pocatecniStav")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VyuctovaniCislo pocatecniStav;

  @JsonProperty("koncovyStav")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VyuctovaniCislo koncovyStav;

  @JsonProperty("spotreba")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VyuctovaniCislo spotreba;

  @JsonProperty("zalohy")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VyuctovaniCislo zalohy;

  @JsonProperty("naklady")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VyuctovaniCislo naklady;

  @JsonProperty("rozdil")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VyuctovaniCislo rozdil;
}
