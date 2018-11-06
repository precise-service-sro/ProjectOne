package com.precise_service.project_one.web.osoba;

import java.io.Serializable;

import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.osoba.IOsobaService;
import com.precise_service.project_one.web.common.component.EditorTextuBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class OsobaDetailBean implements Serializable {

  @Autowired
  private EditorTextuBean editorTextuBean;

  @Autowired
  private IOsobaService osobaService;

  private Osoba osoba;

  public void init() {
    if (osoba != null) {
      // jenom reload dat z databaze
      osoba = osobaService.getOsoba(osoba.getId());
    }

    if (osoba == null) {
      osoba = osobaService.getOsoba(Util.getPrihlasenyUzivatel().getId());
      log.trace("Není vybraná žádná osoba ke zobrazení detailů. (zobrazuji aktuálního přihlášeného uživatele)");
    }

    // editovatelne poznamky
    editorTextuBean.setText(osoba.getPoznamky());
  }

  public void ulozit(){
    log.trace("ulozit()");
    String newText = editorTextuBean.getText();
    osoba.setPoznamky(newText);
    osobaService.postOsoba(osoba);

    FacesMessage msg = new FacesMessage("Úprava uložena", newText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void ulozitZmenuOsoby() {
    log.trace("ulozitZmenuOsoby()");
    osoba = osobaService.putOsoba(osoba);
  }
}
