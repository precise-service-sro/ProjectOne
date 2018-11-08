package com.precise_service.project_one.web.nemovitost;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostKontakt;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.nemovitost.INemovitostKontaktService;
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
  private INemovitostKontaktService nemovitostKontaktService;

  @Autowired
  private IOsobaService osobaService;

  private Nemovitost nemovitost;
  private List<Osoba> osobaList;

  private List<NemovitostKontakt> nemovitostNemovitostKontaktList;

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

    nemovitostNemovitostKontaktList = nemovitostKontaktService.getNemovitostKontaktAll(nemovitost.getId(), prihlasenyUzivatel.getId());
  }

  public void ulozitPoznamky(){
    log.warn("ulozitPoznamky()");
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

  public void pridatNemovitostKontakt() {
    log.trace("pridatNemovitostKontakt()");

    NemovitostKontakt nemovitostKontakt = new NemovitostKontakt();
    nemovitostKontakt.setJmeno("!!! Upravit jméno !!!");
    nemovitostKontakt.setPrijmeni("!!! Upravit příjmení !!!");
    nemovitostKontakt.setTelefon("!!! Upravit telefon !!!");
    nemovitostKontakt.setEmail("!!! Upravit email !!!");
    nemovitostKontakt.setUzivatel(Util.getPrihlasenyUzivatel());
    nemovitostKontakt.setNemovitost(nemovitost);

    nemovitostNemovitostKontaktList.add(nemovitostKontakt);

    Nemovitost saved = nemovitostService.putNemovitost(this.nemovitost);

    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
