package com.precise_service.project_one.web.pronajem;

import java.util.List;

import javax.inject.Named;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.filter.PlatbaNajemnehoFilter;
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
  private int platbaNajemnehoListSize;
  private int filtrovanyPlatbaNajemnehoListSize;

  public void init() {
    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    zuctovaciObdobi = new CasovyInterval();
    platbaNajemnehoList = platbaNajemnehoService.getPlatbaNajemnehoList(new PlatbaNajemnehoFilter().setIdPrihlasenyUzivatel(prihlasenyUzivatel.getId()));
    filtrovanyPlatbaNajemnehoList = null;
  }

  public void pridatPlatbaNajemneho() {
    log.trace("pridatPlatbaNajemneho()");

    PlatbaNajemneho platbaNajemneho = new PlatbaNajemneho();
    platbaNajemneho.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());

    platbaNajemnehoService.postPlatbaNajemneho(platbaNajemneho);
    showInfoMessage("Přidáno", "Přidána nová platba nájemného");

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

    showInfoMessage("Smazáno", "Platba nájemného smazána");
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
      return this.getPlatbaNajemnehoListSize();
    }
    return filtrovanyPlatbaNajemnehoList.size();
  }
}
