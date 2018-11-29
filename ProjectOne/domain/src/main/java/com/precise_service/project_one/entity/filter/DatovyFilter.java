package com.precise_service.project_one.entity.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.filter.typ.CasovyFilter;
import com.precise_service.project_one.entity.filter.typ.FakturaFilter;
import com.precise_service.project_one.entity.filter.typ.NemovitostFilter;
import com.precise_service.project_one.entity.filter.typ.OsobaFilter;
import com.precise_service.project_one.entity.filter.typ.PlatbaNajemnehoFilter;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatovyFilter {

  @JsonProperty("casovyFilter")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private CasovyFilter casovyFilter;

  @JsonProperty("fakturaFilter")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private FakturaFilter fakturaFilter;

  @JsonProperty("nemovitostFilter")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private NemovitostFilter nemovitostFilter;

  @JsonProperty("osobaFilter")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private OsobaFilter osobaFilter;

  @JsonProperty("platbaNajemnehoFilter")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PlatbaNajemnehoFilter platbaNajemnehoFilter;
}