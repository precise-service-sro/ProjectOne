package com.precise_service.project_one.web.najemnik;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.osoba.Najemnik;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.service.osoba.INajemnikService;
import com.precise_service.project_one.service.nemovitost.INemovitostService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NajemnikPrehledBean implements Serializable {

  @Autowired
  private INajemnikService najemnikService;

  @Autowired
  private INemovitostService nemovitostService;

  @Autowired
  private NajemnikDetailBean najemnikDetailBean;

  private List<Najemnik> najemnikList;
  private List<Nemovitost> nemovitostList;
  
  public void init() {
    najemnikList = najemnikService.getNajemnikAll();
    nemovitostList = nemovitostService.getNemovitostAll();
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    Najemnik najemnik = (Najemnik) event.getObject();

    najemnikService.putNajemnik(najemnik);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", najemnik.getCeleJmeno());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((Najemnik) event.getObject()).getCeleJmeno());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void showNajemnikDetailBean(Najemnik najemnik) throws IOException {
    najemnikDetailBean.setNajemnik(najemnik);
    Faces.getFlash().setRedirect(true);
    Faces.redirect("/najemnik/detail.xhtml");
  }

  public void addRow() {
    log.trace("addRow()");

    Najemnik najemnik = new Najemnik();

    najemnik.setJmeno("!!! Upravit !!!");
    najemnik.setPrijmeni("!!! Upravit !!!");

    Najemnik saved = najemnikService.postNajemnik(najemnik);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(Najemnik deletedNajemnik) {
    log.trace("deleteRow()");

    if (deletedNajemnik == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedNajemnik.toString());

    najemnikService.deleteNajemnik(deletedNajemnik.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedNajemnik.getCeleJmeno());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
