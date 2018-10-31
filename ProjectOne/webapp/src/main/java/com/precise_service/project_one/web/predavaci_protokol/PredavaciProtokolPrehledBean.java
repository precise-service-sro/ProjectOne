package com.precise_service.project_one.web.predavaci_protokol;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.osoba.Najemnik;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.service.osoba.INajemnikService;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.service.predavaci_protokol.IPredavaciProtokolService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PredavaciProtokolPrehledBean implements Serializable {

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  @Autowired
  private PredavaciProtokolDetailBean predavaciProtokolDetailBean;

  @Autowired
  private INemovitostService nemovitostService;

  @Autowired
  private INajemnikService najemnikService;

  private List<PredavaciProtokol> predavaciProtokolList;
  private List<Nemovitost> nemovitostList;
  private List<Najemnik> najemnikList;

  public void init() {
    log.trace("init()");
    predavaciProtokolList = predavaciProtokolService.getPredavaciProtokolAll();
    nemovitostList = nemovitostService.getNemovitostAll();
    najemnikList = najemnikService.getNajemnikAll();
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    PredavaciProtokol predavaciProtokol = (PredavaciProtokol) event.getObject();

    predavaciProtokolService.putPredavaciProtokol(predavaciProtokol);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", predavaciProtokol.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((PredavaciProtokol) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void addRow() {
    log.trace("addRow()");

    PredavaciProtokol predavaciProtokol = new PredavaciProtokol();

    predavaciProtokol.setNazev("!!! Upravit název !!!");
    predavaciProtokol.setDatumPodpisu(LocalDate.now());

    PredavaciProtokol saved = predavaciProtokolService.postPredavaciProtokol(predavaciProtokol);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(PredavaciProtokol deletedPredavaciProtokol) {
    log.trace("deleteRow()");

    if (deletedPredavaciProtokol == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedPredavaciProtokol.toString());

    predavaciProtokolService.deletePredavaciProtokol(deletedPredavaciProtokol.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedPredavaciProtokol.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }

  public void showPredavaciProtokolDetailBean(PredavaciProtokol predavaciProtokol) throws IOException {
    predavaciProtokolDetailBean.setPredavaciProtokol(predavaciProtokol);
    Faces.getFlash().setRedirect(true);
    Faces.redirect("/predavaciProtokol/detail.xhtml");
  }
}
