package com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniCisloEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VyuctovaniProNajemnikaPolozkaEntity {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("idVyuctovaniPolozkaTyp")
  private String idVyuctovaniPolozkaTyp;

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
