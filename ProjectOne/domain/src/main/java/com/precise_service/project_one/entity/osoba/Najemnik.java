package com.precise_service.project_one.entity.osoba;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "najemnik")
public class Najemnik extends Osoba {

  // TODO: tuhle vazbu vyhodit, bude reseno pres najemni smlouvu
  @DBRef
  @JsonProperty("nemovitost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Nemovitost nemovitost;

  @DBRef
  @JsonProperty("najemniSmlouva")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String najemniSmlouva;
}