package com.precise_service.project_one.web.najemnik.predavaci_protokol.dto;

import java.io.Serializable;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozkaEntity;

import lombok.Data;

@Data
public class PredavaciProtokolRadkaDto implements Serializable {
  private PredavaciProtokolPolozkaEntity predavaciProtokolPolozkaEntity;

  private String nazev;
  private VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity;
  private String cisloMeraku;
  private String stavMeraku;
  private String jednotka;
}
