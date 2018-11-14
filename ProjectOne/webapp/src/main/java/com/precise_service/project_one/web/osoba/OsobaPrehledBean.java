package com.precise_service.project_one.web.osoba;

import java.util.List;

import javax.inject.Named;

import com.precise_service.project_one.entity.adresa.Adresa;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class OsobaPrehledBean extends AbstractBean {

  private List<Osoba> osobaList;
  private List<Osoba> filtrovanyOsobaList;

  public void init() {
    Osoba prihlasenyUzivatel = Util.getPrihlasenyUzivatel();
    osobaList = osobaService.getOsobaAll(prihlasenyUzivatel.getId());
    filtrovanyOsobaList = null;
  }

  public void addRow() {
    log.trace("addRow()");

    Osoba osoba = new Osoba();
    osoba.setUzivatel(Util.getPrihlasenyUzivatel());
    osoba.setTrvaleBydliste(new Adresa());

    Osoba saved = osobaService.postOsoba(osoba);
    init();

    showInfoMessage("Přidána nová osoba", saved.getId());
  }

  public void deleteRow(Osoba deletedOsoba) {
    log.trace("deleteRow()");

    if (deletedOsoba == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedOsoba.toString());

    osobaService.deleteOsoba(deletedOsoba.getId());

    showInfoMessage("Smazán osoba", deletedOsoba.getCeleJmeno());
    init();
  }
}
