package com.precise_service.project_one.entity.byt.vyuctovani_za_byt;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "cislo")
public class VyuctovaniCislo {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("mnozstvi")
  private Double mnozstvi;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("jednotka")
  private String jednotka;
}
