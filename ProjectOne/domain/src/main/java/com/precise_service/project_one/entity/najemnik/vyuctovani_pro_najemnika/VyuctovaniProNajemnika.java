package com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.BaseEntity;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZuctovaciObdobi;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "vyuctovaniProNajemnika")
public class VyuctovaniProNajemnika extends BaseEntity {

  @JsonProperty("nazev")
  private String nazev;

  @JsonProperty("zuctovaciObdobi")
  private VyuctovaniZuctovaciObdobi zuctovaciObdobi;

  @JsonProperty("seznamPolozek")
  private List<VyuctovaniProNajemnikaPolozka> seznamPolozek;
}
