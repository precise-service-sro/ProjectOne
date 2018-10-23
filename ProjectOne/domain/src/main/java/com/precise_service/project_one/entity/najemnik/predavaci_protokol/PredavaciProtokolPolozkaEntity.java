package com.precise_service.project_one.entity.najemnik.predavaci_protokol;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "predavaciProtokolPolozka")
public class PredavaciProtokolPolozkaEntity extends BaseEntity {

  @DBRef
  @JsonProperty("predavaciProtokol")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PredavaciProtokolEntity predavaciProtokolEntity;

  @JsonProperty("nazev")
  private String nazev;

  @DBRef
  @JsonProperty("vyuctovaniPolozkaTyp")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity;

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
