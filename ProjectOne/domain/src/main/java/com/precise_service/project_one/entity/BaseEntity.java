package com.precise_service.project_one.entity;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public abstract class BaseEntity {

  @Id
  public String id;

  @JsonProperty("idOsoba")
  private String idOsoba;

  @JsonProperty("poznamky")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String poznamky;
}
