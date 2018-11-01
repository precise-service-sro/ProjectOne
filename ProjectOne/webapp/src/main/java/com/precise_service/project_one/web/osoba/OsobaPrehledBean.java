package com.precise_service.project_one.web.osoba;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.service.osoba.IOsobaService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class OsobaPrehledBean implements Serializable {

  @Autowired
  private IOsobaService osobaService;

  @Autowired
  private INemovitostService nemovitostService;

  @Autowired
  private OsobaDetailBean osobaDetailBean;

  private List<Osoba> osobaList;
  private List<Nemovitost> nemovitostList;
  
  public void init() {
    osobaList = osobaService.getOsobaAll();
    nemovitostList = nemovitostService.getNemovitostAll();
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    Osoba osoba = (Osoba) event.getObject();

    osobaService.putOsoba(osoba);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", osoba.getCeleJmeno());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((Osoba) event.getObject()).getCeleJmeno());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void showOsobaDetailBean(Osoba osoba) throws IOException {
    osobaDetailBean.setOsoba(osoba);
    Faces.getFlash().setRedirect(true);
    Faces.redirect("/osoba/detail.xhtml");
  }

  public void addRow() {
    log.trace("addRow()");

    Osoba osoba = new Osoba();

    osoba.setJmeno("!!! Upravit !!!");
    osoba.setPrijmeni("!!! Upravit !!!");

    Osoba saved = osobaService.postOsoba(osoba);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(Osoba deletedOsoba) {
    log.trace("deleteRow()");

    if (deletedOsoba == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedOsoba.toString());

    osobaService.deleteOsoba(deletedOsoba.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedOsoba.getCeleJmeno());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
