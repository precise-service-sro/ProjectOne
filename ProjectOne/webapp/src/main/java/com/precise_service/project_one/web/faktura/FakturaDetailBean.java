package com.precise_service.project_one.web.faktura;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.faktura.FakturaPolozka;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class FakturaDetailBean extends AbstractBean {

  private List<Nemovitost> nemovitostList;
  private Faktura faktura;
  private List<PolozkaTyp> polozkaTypList;
  private List<FakturaPolozka> fakturaPolozkaList;
  private List<FakturaPolozka> filtrovanyFakturaPolozkaList;
  private int fakturaPolozkaListSize;
  private int filtrovanyFakturaPolozkaListSize;

  // TODO: tyhle celkovy soucty spocitat a ulozit na entitu celeho faktura, at se to tu nedela pokazde znova
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  public void init() {
    log.trace("init()");

    if (faktura == null) {
      log.error("Není vybraná žádná faktura k zobrazení detailu");
      return;
    }

    polozkaTypList = polozkaTypService.getPolozkaTypListByIdNemovitost(faktura.getNemovitost().getId());
    nemovitostList = nemovitostService.getNemovitostListByVlastnik(loginBean.getPrihlasenyUzivatel().getId());

    fakturaPolozkaList = fakturaPolozkaService.getFakturaPolozkaAll(faktura.getId());

    for (FakturaPolozka fakturaPolozka : fakturaPolozkaList) {
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
    for (FakturaPolozka polozka : fakturaPolozkaList){
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

  public void pridatFakturaPolozka() {
    log.trace("pridatFakturaPolozka()");

    FakturaPolozka fakturaPolozka = new FakturaPolozka();

    fakturaPolozka.setNazev("- zadejte -");
    fakturaPolozka.setFaktura(faktura);
    fakturaPolozka.setPolozkaTyp(null);
    fakturaPolozka.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());

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

    FacesMessage msg = new FacesMessage("Přidána nová položka", saved.getId());
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

    FacesMessage msg = new FacesMessage("Smazán položka", deletedFakturaPolozka.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }

  public void ulozitZmenuFaktury(boolean presmerovatZpetNaPrehledFaktur) throws IOException {
    log.trace("ulozitZmenuFaktury()");
    faktura = fakturaService.putFaktura(faktura);
    showInfoMessage("Uloženo", "Úprava faktury " + faktura.getDodavatel() + " byla uložena");
    if (presmerovatZpetNaPrehledFaktur) {
      routerBean.goToFakturaPrehledBean();
    }
  }

  public void zrusitZmenuFaktury() throws IOException {
    log.trace("zrusitZmenuFaktury()");
    showInfoMessage("Zrušeno", "Úprava faktury " + faktura.getDodavatel() + " byla zrušena");
    routerBean.goToFakturaPrehledBean();
  }


  public int getFakturaPolozkaListSize() {
    if (fakturaPolozkaList == null) {
      return 0;
    }
    return fakturaPolozkaList.size();
  }

  public int getFiltrovanyFakturaPolozkaListSize() {
    if (filtrovanyFakturaPolozkaList == null) {
      return getFakturaPolozkaListSize();
    }
    return filtrovanyFakturaPolozkaList.size();
  }

  public void zduplikovatFakturu() {
    log.trace("zduplikovatFakturu()");

    String idFakturaOriginal = faktura.getId();
    Faktura novaFaktura = fakturaService.zduplikovatFaktura(faktura);
    fakturaPolozkaService.zduplikovatFakturaPolozkaList(idFakturaOriginal, novaFaktura);
    faktura = novaFaktura;

    showInfoMessage("Zduplikováno", "Faktura od dodavatele " + faktura.getDodavatel() + " byla zduplikována");
  }
}
