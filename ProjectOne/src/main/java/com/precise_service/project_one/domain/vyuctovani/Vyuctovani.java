package com.precise_service.project_one.domain.vyuctovani;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vyuctovani {

  @Id
  public String id;

  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("seznamPolozek")
  private List<PolozkaVyuctovani> seznamPolozek;
}
