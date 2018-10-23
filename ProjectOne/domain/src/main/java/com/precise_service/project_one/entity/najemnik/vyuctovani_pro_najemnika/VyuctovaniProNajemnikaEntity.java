package com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZuctovaciObdobiEntity;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vyuctovaniProNajemnika")
public class VyuctovaniProNajemnikaEntity extends BaseEntity {

  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("zuctovaciObdobi")
  private VyuctovaniZuctovaciObdobiEntity zuctovaciObdobi;

  @JsonProperty("seznamPolozek")
  private List<VyuctovaniProNajemnikaPolozkaEntity> seznamPolozek;
}
