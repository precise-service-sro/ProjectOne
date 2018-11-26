package com.precise_service.project_one.web.pronajem;

import java.util.List;

import javax.inject.Named;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.pronajem.PlatbaNajemneho;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PlatbaNajemnehoPrehledBean extends AbstractBean {

  private CasovyInterval zuctovaciObdobi;
  private List<PlatbaNajemneho> platbaNajemnehoList;
  private List<PlatbaNajemneho> filtrovanyPlatbaNajemnehoList;
  private int fakturaListSize;
  private int filtrovanyFakturaListSize;

  public void init() {
    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    zuctovaciObdobi = new CasovyInterval();
    platbaNajemnehoList = platbaNajemnehoService.getPlatbaNajemnehoList(prihlasenyUzivatel);
    filtrovanyPlatbaNajemnehoList = null;
  }

  public void pridatPlatbaNajemneho() {
    log.trace("pridatPlatbaNajemneho()");

    PlatbaNajemneho platbaNajemneho = new PlatbaNajemneho();
    platbaNajemneho.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());

    PlatbaNajemneho saved = platbaNajemnehoService.postPlatbaNajemneho(platbaNajemneho);
    showInfoMessage("Přidána nová platba nájemného", saved.getId());

    init();
  }

  public void smazatPlatbaNajemneho(PlatbaNajemneho platbaNajemneho) {
    log.trace("smazatPlatbaNajemneho()");

    if (platbaNajemneho == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + platbaNajemneho.getId());

    platbaNajemnehoService.deletePlatbaNajemneho(platbaNajemneho.getId());

    showInfoMessage("Platba nájemného smazána", platbaNajemneho.getId());
    init();
  }

  public int getPlatbaNajemnehoListSize() {
    if (platbaNajemnehoList == null) {
      return 0;
    }
    return platbaNajemnehoList.size();
  }

  public int getFiltrovanyPlatbaNajemnehoListSize() {
    if (filtrovanyPlatbaNajemnehoList == null) {
      return getFakturaListSize();
    }
    return filtrovanyPlatbaNajemnehoList.size();
  }
}
