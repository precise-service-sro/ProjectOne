package com.precise_service.project_one.web.faktura;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.web.URL_CONST.FAKTURA_DETAIL_URL;

@Slf4j
@Data
@Named
public class FakturaPrehledBean extends AbstractBean {

  private CasovyInterval zuctovaciObdobi;
  private List<Faktura> fakturaList;
  private List<Nemovitost> nemovitostList;
  private List<Faktura> filtrovanyFakturaList;
  private int fakturaListSize;
  private int filtrovanyFakturaListSize;

  public void init() {
    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    zuctovaciObdobi = new CasovyInterval();

    if (zuctovaciObdobi == null || zuctovaciObdobi.getZacatek() == null || zuctovaciObdobi.getKonec() == null) {
      // nemuzu filrovat a musim zobrazit vsechny faktury
      /*
      zuctovaciObdobi = new CasovyInterval();
      zuctovaciObdobi.setZacatek(DateFormatterBean.parseDate("01-01-2017"));
      zuctovaciObdobi.setKonec(DateFormatterBean.parseDate("31-12-2017"));
      */
      fakturaList = fakturaService.getSeznamFaktur(prihlasenyUzivatel);

    } else {
      fakturaList = fakturaService.getSeznamFakturVeZuctovacimObdobi(prihlasenyUzivatel, zuctovaciObdobi);
    }

    nemovitostList = nemovitostService.getNemovitostAll(prihlasenyUzivatel.getId());
    filtrovanyFakturaList = null;
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    Faktura faktura = (Faktura) event.getObject();

    fakturaService.putFaktura(faktura);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", faktura.getDodavatel());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((Faktura) event.getObject()).getDodavatel());
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

    faktura.setDodavatel(null);
    faktura.setNemovitost(null);
    faktura.setZuctovaciObdobi(new CasovyInterval());
    faktura.setUzivatel(loginBean.getPrihlasenyUzivatel());

    Faktura saved = fakturaService.postFaktura(faktura);
    showInfoMessage("Přidána nová faktura", saved.getId());

    init();
  }

  public void deleteRow(Faktura deletedFaktura) {
    log.trace("deleteRow()");

    if (deletedFaktura == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedFaktura.toString());

    fakturaService.deleteFaktura(deletedFaktura.getId());

    showInfoMessage("Faktura smazána", deletedFaktura.getDodavatel());
    init();
  }

  public void zuctovaciObdobiZacatekDateSelect() {
    log.trace("zuctovaciObdobiZacatekDateSelect()");
    showInfoMessage("Zvolen nový začátek zúčtovacího období", dateFormatterBean.formatDate(zuctovaciObdobi.getZacatek()));
    init();
  }

  public void zuctovaciObdobiKonecDateSelect() {
    log.trace("zuctovaciObdobiKonecDateSelect()");
    showInfoMessage("Zvolen nový konec zúčtovacího období", dateFormatterBean.formatDate(zuctovaciObdobi.getKonec()));
    init();
  }

  public int getFakturaListSize() {
    if (fakturaList == null) {
      return 0;
    }
    return fakturaList.size();
  }

  public int getFiltrovanyFakturaListSize() {
    if (filtrovanyFakturaList == null) {
      return getFakturaListSize();
    }
    return filtrovanyFakturaList.size();
  }
}
