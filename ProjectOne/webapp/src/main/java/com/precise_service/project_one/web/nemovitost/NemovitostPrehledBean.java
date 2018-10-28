package com.precise_service.project_one.web.nemovitost;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.Adresa;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostTyp;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniService;
import com.precise_service.project_one.web.vyuctovani.VyuctovaniDetailBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NemovitostPrehledBean implements Serializable {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @Autowired
  private INemovitostService nemovitostService;

  @Autowired
  private VyuctovaniDetailBean vyuctovaniDetailBean;

  private List<Nemovitost> nemovitostList;

  @PostConstruct
  public void init() throws ParseException {
    nemovitostList = nemovitostService.getNemovitostAll();
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

  public void showVyuctovaniDetailBean(Nemovitost nemovitost) throws IOException {
    //vyuctovaniDetailBean.setVyuctovani(byt);
    Faces.getFlash().setRedirect(true);
    Faces.redirect("/vyuctovani/detail.xhtml");
  }

  public void addRow() throws ParseException {
    log.trace("addRow()");

    Nemovitost nemovitost = new Nemovitost();

    nemovitost.setNazev("!!! Upravit název !!!");
    nemovitost.setAdresa(new Adresa());
    nemovitost.setNemovitostTyp(NemovitostTyp.BYT);

    Nemovitost saved = nemovitostService.postNemovitost(nemovitost);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(Nemovitost deletedNemovitost) throws ParseException {
    log.trace("deleteRow()");

    if (deletedNemovitost == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedNemovitost.toString());

    vyuctovaniService.deleteVyuctovani(deletedNemovitost.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedNemovitost.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
