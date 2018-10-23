package com.precise_service.project_one.web.vyuctovavane_polozky;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniPolozkaTypService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovavanePolozkyBean implements Serializable {

  @Autowired
  private IVyuctovaniPolozkaTypService vyuctovaniPolozkaTypService;

  private List<VyuctovaniPolozkaTypEntity> radky;

  @PostConstruct
  private void init(){
    log.trace("init()");
    radky = vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTypEntityAll();
  }

  public List<VyuctovaniPolozkaTypEntity> getRadky() {
    log.trace("getRadky()");
    return radky;
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity = (VyuctovaniPolozkaTypEntity) event.getObject();

    vyuctovaniPolozkaTypService.putVyuctovaniPolozkaTypEntity(vyuctovaniPolozkaTypEntity);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", vyuctovaniPolozkaTypEntity.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((VyuctovaniPolozkaTypEntity) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void addRow() {
    log.trace("addRow()");

    VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity = new VyuctovaniPolozkaTypEntity();
    vyuctovaniPolozkaTypEntity.setNazev("!!! Upravit název !!!");
    vyuctovaniPolozkaTypEntity.setPopis("!!! Upravit popis !!!");

    VyuctovaniPolozkaTypEntity saved = vyuctovaniPolozkaTypService.postVyuctovaniPolozkaTypEntity(vyuctovaniPolozkaTypEntity);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
