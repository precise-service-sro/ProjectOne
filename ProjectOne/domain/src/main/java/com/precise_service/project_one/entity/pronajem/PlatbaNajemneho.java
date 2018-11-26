package com.precise_service.project_one.entity.pronajem;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.osoba.Osoba;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "platbaNajemneho")
public class PlatbaNajemneho extends BaseEntity {

  public PlatbaNajemneho() {
    castka = new Cislo();
  }

  @JsonProperty("datumPlatby")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date datumPlatby;

  @JsonProperty("castka")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Cislo castka;

  @DBRef(lazy = true)
  @JsonProperty("odesilatel")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Osoba odesilatel;

  @DBRef(lazy = true)
  @JsonProperty("prijemce")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Osoba prijemce;

  @DBRef(lazy = true)
  @JsonProperty("najemniSmlouva")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private NajemniSmlouva najemniSmlouva;
}
