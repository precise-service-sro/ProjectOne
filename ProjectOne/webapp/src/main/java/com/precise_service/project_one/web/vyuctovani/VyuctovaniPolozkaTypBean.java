package com.precise_service.project_one.web.vyuctovani;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozkaTyp;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniPolozkaTypService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniPolozkaTypBean implements Serializable {

  @Autowired
  private IVyuctovaniPolozkaTypService vyuctovaniPolozkaTypService;

  private List<VyuctovaniPolozkaTyp> vyuctovaniPolozkaTypList;

  public void init(){
    log.trace("init()");
    vyuctovaniPolozkaTypList = vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTypAll();
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp = (VyuctovaniPolozkaTyp) event.getObject();

    vyuctovaniPolozkaTypService.putVyuctovaniPolozkaTyp(vyuctovaniPolozkaTyp);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", vyuctovaniPolozkaTyp.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((VyuctovaniPolozkaTyp) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void addRow() {
    log.trace("addRow()");

    VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp = new VyuctovaniPolozkaTyp();
    vyuctovaniPolozkaTyp.setNazev("!!! Upravit název !!!");
    vyuctovaniPolozkaTyp.setPopis("!!! Upravit popis !!!");

    VyuctovaniPolozkaTyp saved = vyuctovaniPolozkaTypService.postVyuctovaniPolozkaTyp(vyuctovaniPolozkaTyp);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(String idVyuctovaniPolozkaTyp) {
    log.trace("deleteRow()");

    if (idVyuctovaniPolozkaTyp == null){
      log.warn("deleted id is null");
      return;
    }

    log.trace("deleting row with: " + idVyuctovaniPolozkaTyp);
    vyuctovaniPolozkaTypService.deleteVyuctovaniPolozkaTyp(idVyuctovaniPolozkaTyp);

    FacesMessage msg = new FacesMessage("Smazán řádek", idVyuctovaniPolozkaTyp);
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
