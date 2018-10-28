package com.precise_service.project_one.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Adresa {

  @JsonProperty("ulice")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String ulice;

  @JsonProperty("psc")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String psc;

  @JsonProperty("mesto")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String mesto;
}