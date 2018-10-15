package com.precise_service.project_one.entity.byt.vyuctovani;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "polozka")
public class VyuctovaniPolozkaEntity {

  @JsonProperty("nazev")
  private String nazev;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("pocatecniStav")
  private VyuctovaniCisloEntity pocatecniStav;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("koncovyStav")
  private VyuctovaniCisloEntity koncovyStav;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("spotreba")
  private VyuctovaniCisloEntity spotreba;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("zalohy")
  private VyuctovaniCisloEntity zalohy;

  @JsonProperty("naklady")
  private VyuctovaniCisloEntity naklady;

}
