package com.precise_service.project_one.entity.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.nemovitost.NemovitostDispozice;
import com.precise_service.project_one.entity.nemovitost.NemovitostDruhVlastnictvi;
import com.precise_service.project_one.entity.nemovitost.NemovitostTyp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NemovitostFilter extends DataFilter{

  @JsonProperty("nemovitostDruhVlastnictvi")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private NemovitostDruhVlastnictvi nemovitostDruhVlastnictvi;

  @JsonProperty("nemovitostDispozice")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private NemovitostDispozice nemovitostDispozice;

  @JsonProperty("nemovitostTyp")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private NemovitostTyp nemovitostTyp;
}
