package com.precise_service.project_one.entity.byt.vyuctovani_za_byt;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vyuctovaniZaBytPolozka")
public class VyuctovaniZaBytPolozka extends BaseEntity {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("nazev")
  private String nazev;

  @DBRef
  @JsonProperty("vyuctovaniZaByt")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VyuctovaniZaByt vyuctovaniZaByt;

  @DBRef
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("vyuctovaniPolozkaTyp")
  private VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("pocatecniStav")
  private VyuctovaniCislo pocatecniStav;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("koncovyStav")
  private VyuctovaniCislo koncovyStav;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("spotreba")
  private VyuctovaniCislo spotreba;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("zalohy")
  private VyuctovaniCislo zalohy;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("naklady")
  private VyuctovaniCislo naklady;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("rozdil")
  private VyuctovaniCislo rozdil;
}
