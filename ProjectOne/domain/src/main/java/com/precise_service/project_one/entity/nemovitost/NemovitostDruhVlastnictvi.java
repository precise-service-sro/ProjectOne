package com.precise_service.project_one.entity.nemovitost;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NemovitostDruhVlastnictvi {
  OV ("Osobní Vlastnisctví", "Osobní vlastnictví na základě zápisu zápisu na katastru"),
  DV ("Družstevní Vlastnictví", "Družstevní vlastniství na základě družstevního dekrektu / členství ve družstvu"),
  ;

  private final String hodnota;
  private final String popis;
}
