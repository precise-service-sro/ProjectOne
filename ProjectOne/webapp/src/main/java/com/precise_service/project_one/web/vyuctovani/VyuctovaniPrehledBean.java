package com.precise_service.project_one.web.vyuctovani;

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
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniPrehledBean implements Serializable {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @Autowired
  private INemovitostService nemovitostService;

  @Autowired
  private VyuctovaniDetailBean vyuctovaniDetailBean;

  public static final String ZUCTOVACI_OBDOBI_DATE_FORMAT = "dd/MM/yyyy";

  private CasovyInterval zuctovaciObdobi;
  private List<Vyuctovani> vyuctovaniList;
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

    // getVyuctovaniInRange
    //vyuctovaniList = vyuctovaniService.getVyuctovaniInRange(zacatek, konec);

    // getVyuctovaniAll
    vyuctovaniList = vyuctovaniService.getVyuctovaniAll();

    nemovitostList = nemovitostService.getNemovitostAll();
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
    Faces.redirect("/vyuctovani/detail.xhtml");
  }

  public void addRow() throws ParseException {
    log.trace("addRow()");

    Vyuctovani vyuctovani = new Vyuctovani();

    vyuctovani.setNazev("!!! Upravit název !!!");
    vyuctovani.setNemovitost(null);
    vyuctovani.setZuctovaciObdobi(new CasovyInterval());

    Vyuctovani saved = vyuctovaniService.postVyuctovani(vyuctovani);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(Vyuctovani deletedVyuctovani) throws ParseException {
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
