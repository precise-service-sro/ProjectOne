package com.precise_service.project_one.web.faktura;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.faktura.IFakturaService;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.common.DateFormatter;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.web.URL_CONST.FAKTURA_DETAIL_URL;

@Slf4j
@Data
@Named
public class FakturaPrehledBean extends AbstractBean {

  private CasovyInterval zuctovaciObdobi;
  private List<Faktura> fakturaList;
  private List<Faktura> filtrovanyFakturaList;
  private List<Nemovitost> nemovitostList;

  public void init() {

    if (zuctovaciObdobi == null) {
      zuctovaciObdobi = new CasovyInterval();
      zuctovaciObdobi.setZacatek(DateFormatter.parseDate("01-01-2017"));
      zuctovaciObdobi.setKonec(DateFormatter.parseDate("31-12-2017"));
    }

    Osoba prihlasenyUzivatel = Util.getPrihlasenyUzivatel();
    fakturaList = fakturaService.getSeznamFakturVeZuctovacimObdobi(prihlasenyUzivatel, zuctovaciObdobi);

    nemovitostList = nemovitostService.getNemovitostAll();
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    Faktura faktura = (Faktura) event.getObject();

    fakturaService.putFaktura(faktura);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", faktura.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((Faktura) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void showFakturaDetailBean(Faktura faktura) throws IOException {
    fakturaDetailBean.setFaktura(faktura);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(FAKTURA_DETAIL_URL);
  }

  public void addRow() {
    log.trace("addRow()");

    Faktura faktura = new Faktura();

    faktura.setNazev("!!! Upravit název !!!");
    faktura.setNemovitost(null);
    faktura.setZuctovaciObdobi(zuctovaciObdobi);
    faktura.setUzivatel(Util.getPrihlasenyUzivatel());

    Faktura saved = fakturaService.postFaktura(faktura);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(Faktura deletedFaktura) {
    log.trace("deleteRow()");

    if (deletedFaktura == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedFaktura.toString());

    fakturaService.deleteFaktura(deletedFaktura.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedFaktura.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }

  public void zuctovaciObdobiZacatekDateSelect() {
    log.trace("zuctovaciObdobiZacatekDateSelect()");
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zvolen nový začátek zúčtovacího období", DateFormatter.formatDate(zuctovaciObdobi.getZacatek())));
    init();
  }

  public void zuctovaciObdobiKonecDateSelect() {
    log.trace("zuctovaciObdobiKonecDateSelect()");
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zvolen nový konec zúčtovacího období", DateFormatter.formatDate(zuctovaciObdobi.getKonec())));
   init();
  }
}
