package com.precise_service.project_one.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "polozkaTyp")
public class PolozkaTyp extends BaseEntity {

  @JsonProperty("nazev")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nazev;

  @JsonProperty("popis")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String popis;

  @JsonProperty("jednotka")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String jednotka;

  @DBRef
  @JsonProperty("nemovitost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Nemovitost nemovitost;
}
