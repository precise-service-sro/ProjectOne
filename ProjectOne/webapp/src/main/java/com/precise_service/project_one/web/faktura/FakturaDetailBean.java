package com.precise_service.project_one.web.faktura;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.faktura.FakturaPolozka;
import com.precise_service.project_one.service.faktura.IFakturaPolozkaService;
import com.precise_service.project_one.service.faktura.IFakturaService;
import com.precise_service.project_one.service.vyuctovani.IPolozkaTypService;
import com.precise_service.project_one.web.common.DateFormatter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class FakturaDetailBean implements Serializable {

  @Autowired
  private IFakturaService fakturaService;

  @Autowired
  private IFakturaPolozkaService fakturaPolozkaService;

  @Autowired
  private IPolozkaTypService polozkaTypService;

  private List<PolozkaTyp> polozkaTypList;
  private Faktura faktura;
  private List<FakturaPolozka> radkyFaktura;

  // TODO: tyhle celkovy soucty spocitat a ulozit na entitu celeho faktura, at se to tu nedela pokazde znova
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  public void init() {
    log.trace("init()");

    polozkaTypList = polozkaTypService.getPolozkaTypAll();

    Date zacatek = DateFormatter.parseDate("01-01-2017");
    Date konec = DateFormatter.parseDate("31-12-2017");

    if (faktura == null) {
      // TODO: tohle budu moct ve finale uplne smazat
      List<Faktura> fakturaInRange = fakturaService.getFakturaListInRange(zacatek, konec);
      faktura = fakturaInRange.get(0);
    }

    radkyFaktura = fakturaPolozkaService.getFakturaPolozkaAll(faktura.getId());

    for (FakturaPolozka fakturaPolozka : radkyFaktura) {
      if (fakturaPolozka.getPocatecniStav() == null) {
        Cislo pocatecniStav = new Cislo();
        pocatecniStav.setMnozstvi(0.0);
        pocatecniStav.setJednotka("ks");
        fakturaPolozka.setPocatecniStav(pocatecniStav);

        if (fakturaPolozka.getKoncovyStav() == null) {
          Cislo koncovyStav = new Cislo();
          koncovyStav.setMnozstvi(1.0);
          koncovyStav.setJednotka(pocatecniStav.getJednotka());
          fakturaPolozka.setKoncovyStav(koncovyStav);
        }
      }

      if (fakturaPolozka.getKoncovyStav() == null) {
        Cislo koncovyStav = new Cislo();
        koncovyStav.setMnozstvi(0.0);
        koncovyStav.setJednotka(fakturaPolozka.getPocatecniStav().getJednotka());
        fakturaPolozka.setKoncovyStav(koncovyStav);
      }

      Cislo spotreba = new Cislo();
      spotreba.setMnozstvi(fakturaPolozka.getKoncovyStav().getMnozstvi() - fakturaPolozka.getPocatecniStav().getMnozstvi());
      spotreba.setJednotka(fakturaPolozka.getKoncovyStav().getJednotka());
      fakturaPolozka.setSpotreba(spotreba);

      if (fakturaPolozka.getZalohy() == null) {
        Cislo zalohy = new Cislo();
        zalohy.setMnozstvi(0.0);
        zalohy.setJednotka("Kč");
        fakturaPolozka.setZalohy(zalohy);
      }

      Cislo rozdil = new Cislo();
      rozdil.setMnozstvi(fakturaPolozka.getZalohy().getMnozstvi() - fakturaPolozka.getNaklady().getMnozstvi());
      rozdil.setJednotka(fakturaPolozka.getNaklady().getJednotka());
      fakturaPolozka.setRozdil(rozdil);

      fakturaPolozkaService.putFakturaPolozka(fakturaPolozka);
    }

    spocitatSoucty();
  }

  private void spocitatSoucty(){
    celkemZalohy = 0.0;
    celkemNaklady = 0.0;
    celkemRozdil = 0.0;
    for (FakturaPolozka polozka : radkyFaktura){
      celkemZalohy += polozka.getZalohy().getMnozstvi();
      celkemNaklady += polozka.getNaklady().getMnozstvi();
      celkemRozdil += polozka.getRozdil().getMnozstvi();
    }
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    FakturaPolozka fakturaPolozka = (FakturaPolozka) event.getObject();

    fakturaPolozkaService.putFakturaPolozka(fakturaPolozka);
    init();

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", fakturaPolozka.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((FakturaPolozka) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void addRow() {
    log.trace("addRow()");

    FakturaPolozka fakturaPolozka = new FakturaPolozka();

    fakturaPolozka.setNazev("!!! Upravit název !!!");
    fakturaPolozka.setFaktura(faktura);
    fakturaPolozka.setPolozkaTyp(null);

    Cislo vychoziStav = new Cislo();
    vychoziStav.setMnozstvi(0.0);
    vychoziStav.setJednotka("Ks");
    fakturaPolozka.setPocatecniStav(vychoziStav);
    fakturaPolozka.setKoncovyStav(vychoziStav);

    Cislo vychoziZalohyNeboNaklady = new Cislo();
    vychoziZalohyNeboNaklady.setMnozstvi(0.0);
    vychoziZalohyNeboNaklady.setJednotka("Kč");
    fakturaPolozka.setZalohy(vychoziZalohyNeboNaklady);
    fakturaPolozka.setNaklady(vychoziZalohyNeboNaklady);

    FakturaPolozka saved = fakturaPolozkaService.postFakturaPolozka(fakturaPolozka);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(FakturaPolozka deletedFakturaPolozka) {
    log.trace("deleteRow()");

    if (deletedFakturaPolozka == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedFakturaPolozka.toString());

    fakturaPolozkaService.deleteFakturaPolozka(deletedFakturaPolozka.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedFakturaPolozka.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}