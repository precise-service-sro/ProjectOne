package com.precise_service.project_one.web.nemovitost;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.adresa.Adresa;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostTyp;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NemovitostPrehledBean extends AbstractBean {

  private List<Nemovitost> nemovitostList;
  private List<Nemovitost> filtrovanyNemovitostList;

  public void init() {
    Osoba prihlasenyUzivatel = Util.getPrihlasenyUzivatel();
    nemovitostList = nemovitostService.getNemovitostAll(prihlasenyUzivatel.getId());
  }

  public List<Nemovitost> getNemovitostList() {
    return nemovitostList;
  }

  public List<Nemovitost> getFiltrovanyNemovitostList() {
    return filtrovanyNemovitostList;
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    Nemovitost nemovitost = (Nemovitost) event.getObject();

    nemovitostService.putNemovitost(nemovitost);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", nemovitost.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((Nemovitost) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void addRow() {
    log.trace("addRow()");

    Nemovitost nemovitost = new Nemovitost();

    nemovitost.setNazev("!!! Upravit název !!!");
    nemovitost.setAdresa(new Adresa());
    nemovitost.setNemovitostTyp(NemovitostTyp.BYT);
    nemovitost.setUzivatel(Util.getPrihlasenyUzivatel());

    Nemovitost saved = nemovitostService.postNemovitost(nemovitost);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(Nemovitost deletedNemovitost) {
    log.trace("deleteRow()");

    if (deletedNemovitost == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedNemovitost.toString());

    nemovitostService.deleteNemovitost(deletedNemovitost.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedNemovitost.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
