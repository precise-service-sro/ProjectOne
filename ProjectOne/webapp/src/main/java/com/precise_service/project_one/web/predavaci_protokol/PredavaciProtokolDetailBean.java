package com.precise_service.project_one.web.predavaci_protokol;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokolPolozka;
import com.precise_service.project_one.service.predavaci_protokol.IPredavaciProtokolPolozkaService;
import com.precise_service.project_one.service.predavaci_protokol.IPredavaciProtokolService;
import com.precise_service.project_one.service.vyuctovani.IPolozkaTypService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PredavaciProtokolDetailBean implements Serializable {

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  @Autowired
  private IPredavaciProtokolPolozkaService predavaciProtokolPolozkaService;

  @Autowired
  private IPolozkaTypService polozkaTypService;

  private PredavaciProtokol predavaciProtokol;
  private List<PredavaciProtokolPolozka> radky;
  private List<PolozkaTyp> polozkaTypList;

  public void init() {
    log.trace("init()");

    if (predavaciProtokol == null) {
      List<PredavaciProtokol> predavaciProtokolList = predavaciProtokolService.getPredavaciProtokolList();
      predavaciProtokol = predavaciProtokolList.get(0);
    }

    radky = predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaAll(predavaciProtokol.getId());

    polozkaTypList = polozkaTypService.getPolozkaTypAll();
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    PredavaciProtokolPolozka predavaciProtokolPolozka = (PredavaciProtokolPolozka) event.getObject();

    predavaciProtokolPolozkaService.putPredavaciProtokolPolozka(predavaciProtokolPolozka);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", predavaciProtokolPolozka.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((PredavaciProtokolPolozka) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void addRow() {
    log.trace("addRow()");

    PredavaciProtokolPolozka predavaciProtokolPolozka = new PredavaciProtokolPolozka();

    predavaciProtokolPolozka.setPredavaciProtokol(predavaciProtokol);
    predavaciProtokolPolozka.setNazev("!!! Upravit název !!!");
    predavaciProtokolPolozka.setPolozkaTyp(null);
    predavaciProtokolPolozka.setCisloMeraku("!!! Upravit popis !!!");
    predavaciProtokolPolozka.setStavMeraku("!!! Upravit popis !!!");

    PredavaciProtokolPolozka saved = predavaciProtokolPolozkaService.postPredavaciProtokolPolozka(predavaciProtokolPolozka);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(PredavaciProtokolPolozka deletedPredavaciProtokolPolozka) {
    log.trace("deleteRow()");

    if (deletedPredavaciProtokolPolozka == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedPredavaciProtokolPolozka.toString());

    predavaciProtokolPolozkaService.deletePredavaciProtokolPolozka(deletedPredavaciProtokolPolozka.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedPredavaciProtokolPolozka.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
