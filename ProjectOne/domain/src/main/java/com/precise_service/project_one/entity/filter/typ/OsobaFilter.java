package com.precise_service.project_one.entity.filter.typ;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OsobaFilter {

  @JsonProperty("idOsoba")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String isOsoba;

  @JsonProperty("jmeno")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String jmeno;

  @JsonProperty("prijmeni")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String prijmeni;
}
