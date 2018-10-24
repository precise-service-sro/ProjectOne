package com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniCislo;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VyuctovaniProNajemnikaPolozka extends BaseEntity {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("idVyuctovaniPolozkaTyp")
  private String idVyuctovaniPolozkaTyp;

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

  @JsonProperty("naklady")
  private VyuctovaniCislo naklady;

}
