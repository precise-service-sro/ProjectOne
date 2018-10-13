package com.precise_service.project_one.entity.byt.vyuctovani;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vyuctovani")
public class VyuctovaniEntity extends BaseEntity {

  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("zuctovaciObdobi")
  private ZuctovaciObdobiEntity zuctovaciObdobi;

  @JsonProperty("seznamPolozek")
  private List<PolozkaVyuctovaniEntity> seznamPolozek;
}
