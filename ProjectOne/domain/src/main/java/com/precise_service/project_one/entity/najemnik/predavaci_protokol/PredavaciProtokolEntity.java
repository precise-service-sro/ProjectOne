package com.precise_service.project_one.entity.najemnik.predavaci_protokol;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "predavaciProtokol")
public class PredavaciProtokolEntity extends BaseEntity {

  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("datumPodpisu")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate datumPodpisu;

  @JsonProperty("seznamPolozek")
  private List<PredavaciProtokolPolozkaEntity> seznamPolozek;
}
