package com.precise_service.project_one.entity.filter.typ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.CasovyInterval;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CasovyFilter {

  @JsonProperty("casovyInterval")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private CasovyInterval casovyInterval;
}
