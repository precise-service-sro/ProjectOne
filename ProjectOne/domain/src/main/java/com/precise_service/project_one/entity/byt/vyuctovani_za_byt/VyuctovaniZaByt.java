package com.precise_service.project_one.entity.byt.vyuctovani_za_byt;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.byt.informace.Byt;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vyuctovaniZaByt")
public class VyuctovaniZaByt extends BaseEntity {

  @JsonProperty("nazev")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nazev;

  @JsonProperty("zuctovaciObdobi")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VyuctovaniZuctovaciObdobi zuctovaciObdobi;

  @DBRef
  @JsonProperty("byt")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Byt byt;
}
