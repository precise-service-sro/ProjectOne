package com.precise_service.project_one.web.najemnik.predavaci_protokol;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTyp;
import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozka;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniPolozkaTypService;
import com.precise_service.project_one.service.najemnik.predavaci_protokol.IPredavaciProtokolPolozkaService;
import com.precise_service.project_one.service.najemnik.predavaci_protokol.IPredavaciProtokolService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PredavaciProtokolBean implements Serializable {

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  @Autowired
  private IPredavaciProtokolPolozkaService predavaciProtokolPolozkaService;

  @Autowired
  private IVyuctovaniPolozkaTypService vyuctovaniPolozkaTypService;

  private String nazev;
  private LocalDate datumPodpisu;
  private PredavaciProtokol predavaciProtokol;
  private List<PredavaciProtokolPolozka> radky;
  private List<VyuctovaniPolozkaTyp> vyuctovaniPolozkaTypList;

  @PostConstruct
  public void init() {
    log.trace("init()");
    List<PredavaciProtokol> predavaciProtokolList = predavaciProtokolService.getPredavaciProtokolList();
    predavaciProtokol = predavaciProtokolList.get(0);

    nazev = predavaciProtokol.getNazev();
    datumPodpisu = predavaciProtokol.getDatumPodpisu();

    radky = predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaAll(predavaciProtokol.getId());

    vyuctovaniPolozkaTypList = vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTypAll();
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
    predavaciProtokolPolozka.setVyuctovaniPolozkaTyp(null);
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
