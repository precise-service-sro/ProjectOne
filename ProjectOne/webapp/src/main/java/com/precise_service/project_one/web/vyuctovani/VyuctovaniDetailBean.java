package com.precise_service.project_one.web.vyuctovani;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;
import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozka;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniDetailBean extends AbstractBean {

  private List<PolozkaTyp> polozkaTypList;
  private Vyuctovani vyuctovani;
  private List<VyuctovaniPolozka> radkyVyuctovani;

  // TODO: tyhle celkovy soucty spocitat a ulozit na entitu celeho vyuctovani, at se to tu nedela pokazde znova
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  public void init() {
    log.trace("init()");

    polozkaTypList = polozkaTypService.getPolozkaTypAll();

    if (vyuctovani == null) {
      log.error("Není vybráno žádné vyúčtování pro zobrazení detailů");
      return;
    }

    radkyVyuctovani = vyuctovaniPolozkaService.getVyuctovaniPolozkaAll(vyuctovani.getId());

    for (VyuctovaniPolozka vyuctovaniPolozka : radkyVyuctovani) {
      if (vyuctovaniPolozka.getPocatecniStav() == null) {
        Cislo pocatecniStav = new Cislo();
        pocatecniStav.setMnozstvi(0.0);
        pocatecniStav.setJednotka("ks");
        vyuctovaniPolozka.setPocatecniStav(pocatecniStav);

        if (vyuctovaniPolozka.getKoncovyStav() == null) {
          Cislo koncovyStav = new Cislo();
          koncovyStav.setMnozstvi(1.0);
          koncovyStav.setJednotka(pocatecniStav.getJednotka());
          vyuctovaniPolozka.setKoncovyStav(koncovyStav);
        }
      }

      if (vyuctovaniPolozka.getKoncovyStav() == null) {
        Cislo koncovyStav = new Cislo();
        koncovyStav.setMnozstvi(0.0);
        koncovyStav.setJednotka(vyuctovaniPolozka.getPocatecniStav().getJednotka());
        vyuctovaniPolozka.setKoncovyStav(koncovyStav);
      }

      Cislo spotreba = new Cislo();
      spotreba.setMnozstvi(vyuctovaniPolozka.getKoncovyStav().getMnozstvi() - vyuctovaniPolozka.getPocatecniStav().getMnozstvi());
      spotreba.setJednotka(vyuctovaniPolozka.getKoncovyStav().getJednotka());
      vyuctovaniPolozka.setSpotreba(spotreba);

      if (vyuctovaniPolozka.getZalohy() == null) {
        Cislo zalohy = new Cislo();
        zalohy.setMnozstvi(0.0);
        zalohy.setJednotka("Kč");
        vyuctovaniPolozka.setZalohy(zalohy);
      }

      if (vyuctovaniPolozka.getNaklady() == null) {
        Cislo zalohy = new Cislo();
        zalohy.setMnozstvi(0.0);
        zalohy.setJednotka("Kč");
        vyuctovaniPolozka.setNaklady(zalohy);
      }

      Cislo rozdil = new Cislo();
      rozdil.setMnozstvi(vyuctovaniPolozka.getZalohy().getMnozstvi() - vyuctovaniPolozka.getNaklady().getMnozstvi());
      rozdil.setJednotka(vyuctovaniPolozka.getNaklady().getJednotka());
      vyuctovaniPolozka.setRozdil(rozdil);

      vyuctovaniPolozkaService.putVyuctovaniPolozka(vyuctovaniPolozka);
    }

    spocitatSoucty();
  }

  private void spocitatSoucty(){
    celkemZalohy = 0.0;
    celkemNaklady = 0.0;
    celkemRozdil = 0.0;
    for (VyuctovaniPolozka polozka : radkyVyuctovani){
      celkemZalohy += polozka.getZalohy().getMnozstvi();
      celkemNaklady += polozka.getNaklady().getMnozstvi();
      celkemRozdil += polozka.getRozdil().getMnozstvi();
    }
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    VyuctovaniPolozka vyuctovaniPolozka = (VyuctovaniPolozka) event.getObject();

    vyuctovaniPolozkaService.putVyuctovaniPolozka(vyuctovaniPolozka);
    init();

    showInfoMessage("Uložena úprava řádky", vyuctovaniPolozka.getNazev());
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    showInfoMessage("Zrušena úprava řádky", ((VyuctovaniPolozka) event.getObject()).getNazev());
  }

  public void addRow() {
    log.trace("addRow()");

    VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();

    vyuctovaniPolozka.setNazev("- zadejte -");
    vyuctovaniPolozka.setVyuctovani(vyuctovani);
    vyuctovaniPolozka.setPolozkaTyp(null);
    vyuctovaniPolozka.setUzivatel(Util.getPrihlasenyUzivatel());

    Cislo vychoziStav = new Cislo();
    vychoziStav.setMnozstvi(0.0);
    vychoziStav.setJednotka("Ks");
    vyuctovaniPolozka.setPocatecniStav(vychoziStav);
    vyuctovaniPolozka.setKoncovyStav(vychoziStav);

    Cislo vychoziZalohyNeboNaklady = new Cislo();
    vychoziZalohyNeboNaklady.setMnozstvi(0.0);
    vychoziZalohyNeboNaklady.setJednotka("Kč");
    vyuctovaniPolozka.setZalohy(vychoziZalohyNeboNaklady);
    vyuctovaniPolozka.setNaklady(vychoziZalohyNeboNaklady);

    VyuctovaniPolozka saved = vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
    init();

    showInfoMessage("Přidána nová řádka", saved.getId());
  }

  public void deleteRow(VyuctovaniPolozka deletedVyuctovaniPolozka) {
    log.trace("deleteRow()");

    if (deletedVyuctovaniPolozka == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedVyuctovaniPolozka.toString());

    vyuctovaniPolozkaService.deleteVyuctovaniPolozka(deletedVyuctovaniPolozka.getId());

    showInfoMessage("Smazán řádek", deletedVyuctovaniPolozka.getNazev());
    init();
  }
}
