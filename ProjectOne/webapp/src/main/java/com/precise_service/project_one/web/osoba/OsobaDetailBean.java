package com.precise_service.project_one.web.osoba;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.precise_service.project_one.entity.adresa.Stat;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class OsobaDetailBean extends AbstractBean {

  private Osoba osoba;
  private List<Stat> statList;

  public void init() {
    if (osoba == null) {
      osoba = osobaService.getOsoba(Util.getPrihlasenyUzivatel().getId());
      log.trace("Není vybraná žádná osoba ke zobrazení detailů. (zobrazuji aktuálního přihlášeného uživatele)");
    }
    statList = Arrays.asList(Stat.values());
  }

  public void ulozitZmenuOsoby() throws IOException {
    log.trace("ulozitZmenuOsoby()");
    osoba = osobaService.putOsoba(osoba);
    showMessage(FacesMessage.SEVERITY_INFO,"Uloženo", "Úprava osoby " + osoba.getCeleJmeno() + " byla uložena");
    routerBean.goToOsobaPrehledBean();
  }

  public void zrusitZmenuOsoby() throws IOException {
    log.trace("zrusitZmenuOsoby()");
    showMessage(FacesMessage.SEVERITY_INFO,"Zrušeno", "Úprava osoby " + osoba.getCeleJmeno() + " byla zrušena");
    routerBean.goToOsobaPrehledBean();
  }
}
