package com.precise_service.project_one.web.nemovitost;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.service.osoba.IOsobaService;
import com.precise_service.project_one.web.common.component.EditorTextuBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NemovitostDetailBean implements Serializable {

  @Autowired
  private EditorTextuBean editorTextuBean;

  @Autowired
  private INemovitostService nemovitostService;

  @Autowired
  private IOsobaService osobaService;

  private Nemovitost nemovitost;
  private List<Osoba> osobaList;

  public void init() {
    // pokud nemam vybranou zadnou nemovitost, tak vytahuji prvni nemovitost z DB
    if (nemovitost == null) {
      log.error("Není vybrána žádná nemovitosti pro zobrazení detailu");
      return;
    }

    Osoba prihlasenyUzivatel = Util.getPrihlasenyUzivatel();
    osobaList = osobaService.getOsobaAll(prihlasenyUzivatel.getId());

    // editovatelne poznamky
    editorTextuBean.setText(nemovitost.getPoznamky());
  }

  public void ulozit(){
    log.warn("ulozit()");
    String newText = editorTextuBean.getText();
    nemovitost.setPoznamky(newText);
    nemovitostService.postNemovitost(nemovitost);

    FacesMessage msg = new FacesMessage("Úprava uložena", newText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void ulozitZmenuNemovitosti() {
    log.trace("ulozitZmenuNemovitosti()");
    nemovitost = nemovitostService.putNemovitost(nemovitost);
  }
}
