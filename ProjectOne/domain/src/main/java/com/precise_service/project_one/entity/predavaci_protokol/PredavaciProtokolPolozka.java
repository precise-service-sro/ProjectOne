package com.precise_service.project_one.entity.predavaci_protokol;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.PolozkaTyp;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "predavaciProtokolPolozka")
public class PredavaciProtokolPolozka extends BaseEntity {

  @DBRef
  @JsonProperty("predavaciProtokol")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PredavaciProtokol predavaciProtokol;

  @JsonProperty("nazev")
  private String nazev;

  @DBRef
  @JsonProperty("polozkaTyp")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PolozkaTyp polozkaTyp;

  @JsonProperty("cisloMeraku")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String cisloMeraku;

  @JsonProperty("stavMeraku")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String stavMeraku;

  @JsonProperty("jednotka")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String jednotka;
}
