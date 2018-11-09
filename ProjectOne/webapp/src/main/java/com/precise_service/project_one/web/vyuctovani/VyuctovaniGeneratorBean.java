package com.precise_service.project_one.web.vyuctovani;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;
import com.precise_service.project_one.service.faktura.IFakturaService;
import com.precise_service.project_one.service.osoba.IOsobaService;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.service.predavaci_protokol.IPredavaciProtokolService;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniService;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.URL_CONST;
import com.precise_service.project_one.web.common.DateFormatter;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.web.URL_CONST.VYUCTOVANI_DETAIL_URL;

@Slf4j
@Data
@Named
public class VyuctovaniGeneratorBean extends AbstractBean {

  private List<Nemovitost> nemovitostList;
  private List<Osoba> seznamNajemniku;

  private Nemovitost nemovitost;
  private Osoba najemnik;
  private PredavaciProtokol predavaciProtokol;
  private CasovyInterval zuctovaciObdobi;
  private List<Vyuctovani> vyuctovaniList;
  private List<PredavaciProtokol> predavaciProtokolList;

  public void init() {
    log.trace("init()");

    if (zuctovaciObdobi == null) {
      zuctovaciObdobi = new CasovyInterval();
      zuctovaciObdobi.setZacatek(DateFormatter.parseDate("01-01-2017"));
      zuctovaciObdobi.setKonec(DateFormatter.parseDate("31-12-2017"));
    }

    nemovitostList = nemovitostService.getNemovitostAll();
    seznamNajemniku = osobaService.getOsobaAll();
    predavaciProtokolList = predavaciProtokolService.getPredavaciProtokolAll();
  }

  public void generate() throws IOException {
    log.trace("generate()");

    Osoba prihlasenyUzivatel = Util.getPrihlasenyUzivatel();
    List<Faktura> fakturaList = fakturaService.getSeznamFakturVeZuctovacimObdobi(prihlasenyUzivatel, zuctovaciObdobi);

    String nazev = "Nové vyúčtování ze dne " + LocalDateTime.now().toString();
    Vyuctovani vyuctovani = vyuctovaniService.generovatVyuctovani(nazev, zuctovaciObdobi, nemovitost, najemnik, fakturaList, predavaciProtokol, null, Util.getPrihlasenyUzivatel());

    vyuctovaniDetailBean.setVyuctovani(vyuctovani);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(VYUCTOVANI_DETAIL_URL);
  }

  public void zuctovaciObdobiZacatekDateSelect(SelectEvent event) {
    Date zacatek = (Date) event.getObject();
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zvolen nový začátek zúčtovacího období", DateFormatter.formatDate(zacatek)));
    init();
  }

  public void zuctovaciObdobiKonecDateSelect(SelectEvent event) {
    Date konec = (Date) event.getObject();
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zvolen nový konec zúčtovacího období", DateFormatter.formatDate(konec)));
    init();
  }
}
