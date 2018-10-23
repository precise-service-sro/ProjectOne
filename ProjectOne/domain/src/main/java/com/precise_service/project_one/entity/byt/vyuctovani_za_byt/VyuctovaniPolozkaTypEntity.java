package com.precise_service.project_one.entity.byt.vyuctovani_za_byt;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vyuctovaniPolozkaTyp")
public class VyuctovaniPolozkaTypEntity extends BaseEntity {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("nazev")
  private String nazev;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("popis")
  private String popis;
}
