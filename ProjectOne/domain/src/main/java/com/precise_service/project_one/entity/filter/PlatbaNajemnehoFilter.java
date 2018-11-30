package com.precise_service.project_one.entity.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.CasovyInterval;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatbaNajemnehoFilter extends DataFilter {
  @JsonProperty("idOdesilatel")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idOdesilatel;

  @JsonProperty("idPrijemce")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String idPrijemce;
}
