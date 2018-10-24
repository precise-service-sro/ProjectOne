package com.precise_service.project_one.entity.byt.informace;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "byt")
public class Byt extends BaseEntity {

  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("adresa")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String adresa;

  @JsonProperty("poznamky")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String poznamky;
}