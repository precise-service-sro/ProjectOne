package com.precise_service.project_one.entity.najemnik.informace;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.byt.informace.BytEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "najemnik")
public class NajemnikEntity extends BaseEntity {

  @JsonProperty("jmeno")
  private String jmeno;

  @JsonProperty("prijmeni")
  private String prijmeni;

  @DBRef
  @JsonProperty("byt")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BytEntity byt;

  @JsonProperty("najemniSmlouva")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String najemniSmlouva;

  @JsonProperty("poznamky")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String poznamky;
}