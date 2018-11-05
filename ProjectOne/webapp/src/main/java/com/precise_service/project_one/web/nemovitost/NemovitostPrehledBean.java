package com.precise_service.project_one.web.nemovitost;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.Adresa;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostTyp;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.service.osoba.IOsobaService;
import com.precise_service.project_one.web.URL_CONST;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.web.URL_CONST.NEMOVITOST_DETAIL_URL;

@Slf4j
@Data
@Named
public class NemovitostPrehledBean implements Serializable {

  @Autowired
  private INemovitostService nemovitostService;

  @Autowired
  private NemovitostDetailBean nemovitostDetailBean;

  @Autowired
  private IOsobaService osobaService;

  private List<Nemovitost> nemovitostList;
  private List<NemovitostTyp> nemovitostTypList;

  public void init() {
    Osoba prihlasenyUzivatel = Util.getPrihlasenyUzivatel();
    nemovitostList = nemovitostService.getNemovitostAll(prihlasenyUzivatel.getId());
    nemovitostTypList = Arrays.asList(NemovitostTyp.values());
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

  public void showNemovitostDetailBean(Nemovitost nemovitost) throws IOException {
    nemovitostDetailBean.setNemovitost(nemovitost);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(NEMOVITOST_DETAIL_URL);
  }

  public void addRow() {
    log.trace("addRow()");

    Nemovitost nemovitost = new Nemovitost();

    nemovitost.setNazev("!!! Upravit název !!!");
    nemovitost.setAdresa(new Adresa());
    nemovitost.setNemovitostTyp(NemovitostTyp.BYT);
    nemovitost.setUzivatel(Util.getPrihlasenyUzivatel());

    Nemovitost saved = nemovitostService.postNemovitost(nemovitost);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(Nemovitost deletedNemovitost) {
    log.trace("deleteRow()");

    if (deletedNemovitost == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedNemovitost.toString());

    nemovitostService.deleteNemovitost(deletedNemovitost.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedNemovitost.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
