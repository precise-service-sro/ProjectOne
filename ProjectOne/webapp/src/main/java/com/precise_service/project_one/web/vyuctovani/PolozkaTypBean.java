package com.precise_service.project_one.web.vyuctovani;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.service.vyuctovani.IPolozkaTypService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PolozkaTypBean implements Serializable {

  @Autowired
  private IPolozkaTypService polozkaTypService;

  private List<PolozkaTyp> polozkaTypList;

  public void init(){
    log.trace("init()");
    polozkaTypList = polozkaTypService.getPolozkaTypAll();
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    PolozkaTyp polozkaTyp = (PolozkaTyp) event.getObject();

    polozkaTypService.putPolozkaTyp(polozkaTyp);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", polozkaTyp.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((PolozkaTyp) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void addRow() {
    log.trace("addRow()");

    PolozkaTyp polozkaTyp = new PolozkaTyp();
    polozkaTyp.setNazev("!!! Upravit název !!!");
    polozkaTyp.setPopis("!!! Upravit popis !!!");

    PolozkaTyp saved = polozkaTypService.postPolozkaTyp(polozkaTyp);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(String idPolozkaTyp) {
    log.trace("deleteRow()");

    if (idPolozkaTyp == null){
      log.warn("deleted id is null");
      return;
    }

    log.trace("deleting row with: " + idPolozkaTyp);
    polozkaTypService.deletePolozkaTyp(idPolozkaTyp);

    FacesMessage msg = new FacesMessage("Smazán řádek", idPolozkaTyp);
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
