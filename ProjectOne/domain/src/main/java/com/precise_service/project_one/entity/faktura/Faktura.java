package com.precise_service.project_one.entity.faktura;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "faktura")
public class Faktura extends BaseEntity {

  @JsonProperty("nazev")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nazev;

  @JsonProperty("zuctovaciObdobi")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private CasovyInterval zuctovaciObdobi;

  @JsonProperty("datumSplatnosti")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Date datumSplatnosti;

  @DBRef(lazy = true)
  @JsonProperty("nemovitost")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Nemovitost nemovitost;
}
