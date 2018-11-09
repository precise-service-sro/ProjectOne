package com.precise_service.project_one.web.vyuctovani;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.common.DateFormatter;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.web.URL_CONST.VYUCTOVANI_DETAIL_URL;

@Slf4j
@Data
@Named
public class VyuctovaniPrehledBean extends AbstractBean {

  private CasovyInterval zuctovaciObdobi;
  private List<Vyuctovani> vyuctovaniList;
  private List<Nemovitost> nemovitostList;

  public void init() {

    if (zuctovaciObdobi == null) {
      zuctovaciObdobi = new CasovyInterval();
      zuctovaciObdobi.setZacatek(DateFormatter.parseDate("01-01-2017"));
      zuctovaciObdobi.setKonec(DateFormatter.parseDate("31-12-2017"));
    }

    // getVyuctovaniInRange
    Osoba prihlasenyUzivatel = Util.getPrihlasenyUzivatel();
    vyuctovaniList = vyuctovaniService.getVyuctovaniListInRange(prihlasenyUzivatel, zuctovaciObdobi);

    nemovitostList = nemovitostService.getNemovitostAll(prihlasenyUzivatel.getId());
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    Vyuctovani vyuctovani = (Vyuctovani) event.getObject();

    vyuctovaniService.putVyuctovani(vyuctovani);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", vyuctovani.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((Vyuctovani) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void showVyuctovaniDetailBean(Vyuctovani vyuctovani) throws IOException {
    vyuctovaniDetailBean.setVyuctovani(vyuctovani);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(VYUCTOVANI_DETAIL_URL);
  }

  public void addRow() {
    log.trace("addRow()");

    Vyuctovani vyuctovani = new Vyuctovani();

    vyuctovani.setNazev("!!! Upravit název !!!");
    vyuctovani.setNemovitost(null);
    vyuctovani.setZuctovaciObdobi(zuctovaciObdobi);
    vyuctovani.setUzivatel(Util.getPrihlasenyUzivatel());

    Vyuctovani saved = vyuctovaniService.postVyuctovani(vyuctovani);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(Vyuctovani deletedVyuctovani) {
    log.trace("deleteRow()");

    if (deletedVyuctovani == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedVyuctovani.toString());

    vyuctovaniService.deleteVyuctovani(deletedVyuctovani.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedVyuctovani.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }

  public void zuctovaciObdobiZacatekDateSelect(SelectEvent event) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zvolen nový začátek zúčtovacího období", DateFormatter.formatDate((Date) event.getObject())));
    init();
  }

  public void zuctovaciObdobiKonecDateSelect(SelectEvent event) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zvolen nový konec zúčtovacího období", DateFormatter.formatDate((Date) event.getObject())));
    init();
  }

}
