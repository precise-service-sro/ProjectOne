package com.precise_service.project_one.web.najemnik.predavaci_protokol.dto;

import java.io.Serializable;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTyp;
import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozka;

import lombok.Data;

@Data
public class PredavaciProtokolRadkaDto implements Serializable {
  private PredavaciProtokolPolozka predavaciProtokolPolozka;

  private String nazev;
  private VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp;
  private String cisloMeraku;
  private String stavMeraku;
  private String jednotka;
}
