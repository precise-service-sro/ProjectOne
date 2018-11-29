package com.precise_service.project_one.entity.filter.typ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatbaNajemnehoFilter {

  @JsonProperty("idOdesilatel")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idOdesilatel;

  @JsonProperty("idPrijemce")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idPrijemce;
}
