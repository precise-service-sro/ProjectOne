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

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolEntity;
import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozkaEntity;
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
  private PredavaciProtokolEntity predavaciProtokolEntity;
  private List<PredavaciProtokolPolozkaEntity> radky;
  private List<VyuctovaniPolozkaTypEntity> vyuctovaniPolozkaTypEntityList;

  @PostConstruct
  public void init() {
    log.trace("init()");
    List<PredavaciProtokolEntity> predavaciProtokolEntityList = predavaciProtokolService.getPredavaciProtokolEntityList();
    predavaciProtokolEntity = predavaciProtokolEntityList.get(0);

    nazev = predavaciProtokolEntity.getNazev();
    datumPodpisu = predavaciProtokolEntity.getDatumPodpisu();

    radky = predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaEntityAll(predavaciProtokolEntity.getId());

    vyuctovaniPolozkaTypEntityList = vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTypEntityAll();
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    PredavaciProtokolPolozkaEntity predavaciProtokolPolozkaEntity = (PredavaciProtokolPolozkaEntity) event.getObject();

    predavaciProtokolPolozkaService.putPredavaciProtokolPolozkaEntity(predavaciProtokolPolozkaEntity);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", predavaciProtokolPolozkaEntity.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((PredavaciProtokolPolozkaEntity) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void addRow() {
    log.trace("addRow()");

    PredavaciProtokolPolozkaEntity predavaciProtokolPolozkaEntity = new PredavaciProtokolPolozkaEntity();

    predavaciProtokolPolozkaEntity.setPredavaciProtokolEntity(predavaciProtokolEntity);
    predavaciProtokolPolozkaEntity.setNazev("!!! Upravit název !!!");
    predavaciProtokolPolozkaEntity.setVyuctovaniPolozkaTypEntity(null);
    predavaciProtokolPolozkaEntity.setCisloMeraku("!!! Upravit popis !!!");
    predavaciProtokolPolozkaEntity.setStavMeraku("!!! Upravit popis !!!");

    PredavaciProtokolPolozkaEntity saved = predavaciProtokolPolozkaService.postPredavaciProtokolPolozkaEntity(predavaciProtokolPolozkaEntity);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(PredavaciProtokolPolozkaEntity deletedPredavaciProtokolPolozkaEntity) {
    log.trace("deleteRow()");

    if (deletedPredavaciProtokolPolozkaEntity == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedPredavaciProtokolPolozkaEntity.toString());

    predavaciProtokolPolozkaService.deletePredavaciProtokolPolozkaEntity(deletedPredavaciProtokolPolozkaEntity.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedPredavaciProtokolPolozkaEntity.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
