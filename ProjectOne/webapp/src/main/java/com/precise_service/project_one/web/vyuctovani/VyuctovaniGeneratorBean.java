package com.precise_service.project_one.web.vyuctovani;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.Najemnik;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;
import com.precise_service.project_one.entity.ZuctovaciObdobi;
import com.precise_service.project_one.service.faktura.IFakturaService;
import com.precise_service.project_one.service.najemnik.INajemnikService;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.service.predavaci_protokol.IPredavaciProtokolService;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniService;
import com.precise_service.project_one.web.common.DateFormatter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniGeneratorBean implements Serializable {

  @Autowired
  private INemovitostService nemovitostService;

  @Autowired
  private INajemnikService najemnikService;

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @Autowired
  private IFakturaService fakturaService;

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  @Autowired
  private VyuctovaniDetailBean vyuctovaniDetailBean;

  public static final String ZUCTOVACI_OBDOBI_DATE_FORMAT = "dd/MM/yyyy";

  private List<Nemovitost> nemovitostList;
  private List<Najemnik> najemnikList;

  private Nemovitost nemovitost;
  private Najemnik najemnik;
  private PredavaciProtokol predavaciProtokol;
  private ZuctovaciObdobi zuctovaciObdobi;
  private List<Vyuctovani> vyuctovaniList;
  private List<PredavaciProtokol> predavaciProtokolList;

  public void init() throws ParseException {
    log.trace("init()");

    if (zuctovaciObdobi == null) {
      zuctovaciObdobi = new ZuctovaciObdobi();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
      Date zacatek = simpleDateFormat.parse("01-01-2017");
      Date konec = simpleDateFormat.parse("31-12-2017");
      zuctovaciObdobi.setZacatek(zacatek);
      zuctovaciObdobi.setKonec(konec);
    }

    nemovitostList = nemovitostService.getNemovitostAll();
    najemnikList = najemnikService.getNajemnikAll();
    predavaciProtokolList = predavaciProtokolService.getPredavaciProtokolAll();
  }

  public void generate() throws IOException {
    log.trace("generate()");

    // TODO: vyfiltrovat na zahlade vsech vstupnich dat a prihlaseneho uzivatele
    List<Faktura> fakturaListInRange = fakturaService.getFakturaListInRange(zuctovaciObdobi.getZacatek(), zuctovaciObdobi.getKonec());

    String nazev = "Nové vyúčtování ze dne " + DateFormatter.formatDate(new Date());
    Vyuctovani vyuctovani = vyuctovaniService.generovatVyuctovani(nazev, zuctovaciObdobi, nemovitost, najemnik, fakturaListInRange);

    vyuctovaniDetailBean.setVyuctovani(vyuctovani);
    Faces.getFlash().setRedirect(true);
    Faces.redirect("/vyuctovani/detail.xhtml");
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
