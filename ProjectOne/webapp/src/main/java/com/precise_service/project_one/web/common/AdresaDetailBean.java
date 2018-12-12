package com.precise_service.project_one.web.common;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import com.precise_service.project_one.entity.adresa.AdresaTyp;
import com.precise_service.project_one.entity.adresa.Stat;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class AdresaDetailBean extends AbstractBean {

  private List<Stat> statList;
  private List<AdresaTyp> adresaTypList;

  public void init() {
    statList = Arrays.asList(Stat.values());
    adresaTypList = Arrays.asList(AdresaTyp.values());
  }
}
