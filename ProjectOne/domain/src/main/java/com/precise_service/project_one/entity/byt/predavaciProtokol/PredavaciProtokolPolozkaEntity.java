package com.precise_service.project_one.entity.byt.predavaciProtokol;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniCisloEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "polozka")
public class PredavaciProtokolPolozkaEntity {

  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("cisloMeraku")
  private String cisloMeraku;

  @JsonProperty("stavMeraku")
  private String stavMeraku;

  @JsonProperty("jednotka")
  private String jednotka;
}
