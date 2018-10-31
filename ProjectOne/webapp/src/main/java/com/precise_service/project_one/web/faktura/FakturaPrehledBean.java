package com.precise_service.project_one.web.faktura;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.precise_service.project_one.service.faktura.IFakturaService;
import com.precise_service.project_one.service.nemovitost.INemovitostService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class FakturaPrehledBean implements Serializable {

  @Autowired
  private IFakturaService fakturaService;

  @Autowired
  private INemovitostService nemovitostService;

  @Autowired
  private FakturaDetailBean fakturaDetailBean;

  public static final String ZUCTOVACI_OBDOBI_DATE_FORMAT = "dd/MM/yyyy";

  private CasovyInterval zuctovaciObdobi;
  private List<Faktura> fakturaList;
  private List<Nemovitost> nemovitostList;

  public void init() throws ParseException {

    if (zuctovaciObdobi == null) {
      zuctovaciObdobi = new CasovyInterval();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
      Date zacatek = simpleDateFormat.parse("01-01-2017");
      Date konec = simpleDateFormat.parse("31-12-2017");
      zuctovaciObdobi.setZacatek(zacatek);
      zuctovaciObdobi.setKonec(konec);
    }

    // getFakturaInRange
    //fakturaList = fakturaService.getFakturaInRange(zacatek, konec);

    // getFakturaAll
    fakturaList = fakturaService.getFakturaAll();

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
    Faces.redirect("/faktura/detail.xhtml");
  }

  public void addRow() throws ParseException {
    log.trace("addRow()");

    Faktura faktura = new Faktura();

    faktura.setNazev("!!! Upravit název !!!");
    faktura.setNemovitost(null);
    faktura.setZuctovaciObdobi(new CasovyInterval());

    Faktura saved = fakturaService.postFaktura(faktura);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(Faktura deletedFaktura) throws ParseException {
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

  public void zuctovaciObdobiZacatekDateSelect(SelectEvent event) throws ParseException {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date zacatek = (Date) event.getObject();
    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zvolen nový začátek zúčtovacího období", simpleDateFormat.format(zacatek)));
    init();
  }

  public void zuctovaciObdobiKonecDateSelect(SelectEvent event) throws ParseException {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date konec = (Date) event.getObject();
    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zvolen nový konec zúčtovacího období", simpleDateFormat.format(konec)));
    init();
  }

}
