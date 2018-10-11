package com.precise_service.project_one.domain.vyuctovani;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.domain.BaseEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vyuctovani")
public class VyuctovaniEntity extends BaseEntity {

  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("seznamPolozek")
  private List<PolozkaVyuctovani> seznamPolozek;
}
