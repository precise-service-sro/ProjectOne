package com.precise_service.project_one.web.osoba;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

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

  public void init() {
    if (osoba == null) {
      osoba = osobaService.getOsoba(Util.getPrihlasenyUzivatel().getId());
      log.trace("Není vybraná žádná osoba ke zobrazení detailů. (zobrazuji aktuálního přihlášeného uživatele)");
    }

    // editovatelne poznamky
    editorTextuBean.setText(osoba.getPoznamky());
  }

  public void ulozitPoznamky(){
    log.trace("ulozitPoznamky()");
    String newText = editorTextuBean.getText();
    osoba.setPoznamky(newText);
    osobaService.postOsoba(osoba);

    FacesMessage msg = new FacesMessage("Úprava poznámky uložena", newText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void ulozitZmenuOsoby() {
    log.trace("ulozitZmenuOsoby()");
    osoba = osobaService.putOsoba(osoba);
  }
}
