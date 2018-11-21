package com.precise_service.project_one.web.vyuctovani;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.NajemniSmlouva;
import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;
import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozka;
import com.precise_service.project_one.web.AbstractBean;

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

    polozkaTypList = polozkaTypService.getPolozkaTypListByIdNemovitost(vyuctovani.getNemovitost().getId());

    if (vyuctovani == null) {
      log.error("Není vybráno žádné vyúčtování pro zobrazení detailů");
      return;
    }

    radkyVyuctovani = vyuctovaniPolozkaService.getVyuctovaniPolozkaZvyrazneneList(vyuctovani.getId());

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

      if (vyuctovaniPolozka.getNaklady() == null) {
        Cislo zalohy = new Cislo();
        zalohy.setMnozstvi(0.0);
        zalohy.setJednotka("Kč");
        vyuctovaniPolozka.setNaklady(zalohy);
      }

      vyuctovaniPolozkaService.putVyuctovaniPolozka(vyuctovaniPolozka);
    }

    spocitatSoucty();
  }

  private void spocitatSoucty(){
    celkemZalohy = 0.0;
    celkemNaklady = 0.0;
    celkemRozdil = 0.0;
    for (VyuctovaniPolozka polozka : radkyVyuctovani){
      celkemNaklady += polozka.getNaklady().getMnozstvi();
    }
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    VyuctovaniPolozka vyuctovaniPolozka = (VyuctovaniPolozka) event.getObject();

    vyuctovaniPolozkaService.putVyuctovaniPolozka(vyuctovaniPolozka);
    init();

    showInfoMessage("Uložena úprava řádky", vyuctovaniPolozka.getPolozkaTyp().getNazev());
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    showInfoMessage("Zrušena úprava řádky", ((VyuctovaniPolozka) event.getObject()).getPolozkaTyp().getNazev());
  }

  public void addRow() {
    log.trace("addRow()");

    VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();

    vyuctovaniPolozka.setVyuctovani(vyuctovani);
    vyuctovaniPolozka.setPolozkaTyp(null);
    vyuctovaniPolozka.setUzivatel(loginBean.getPrihlasenyUzivatel());

    Cislo vychoziStav = new Cislo();
    vychoziStav.setMnozstvi(0.0);
    vychoziStav.setJednotka("Ks");
    vyuctovaniPolozka.setPocatecniStav(vychoziStav);
    vyuctovaniPolozka.setKoncovyStav(vychoziStav);

    Cislo vychoziZalohyNeboNaklady = new Cislo();
    vychoziZalohyNeboNaklady.setMnozstvi(0.0);
    vychoziZalohyNeboNaklady.setJednotka("Kč");
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

    showInfoMessage("Smazán řádek", deletedVyuctovaniPolozka.getPolozkaTyp().getNazev());
    init();
  }

  public List<PredavaciProtokol> getPredavaciProkokolInList() {
    // jenom obalovaci metoda, abych mohl jediny predavaci protokol zobrazit v DataTable
    return Arrays.asList(vyuctovani.getPredavaciProtokol());
  }

  public List<PlatbaNajemneho> getPlatbaNajemnehoList(){
    List<PlatbaNajemneho> platbaNajemnehoList = new ArrayList<>();

    PlatbaNajemneho platbaNajemneho1 = new PlatbaNajemneho();
    platbaNajemneho1.setDatumPlatby(new Date());
    Cislo platba = new Cislo();
    platba.setMnozstvi(123.0);
    platba.setJednotka("Kč");
    platbaNajemneho1.setPlatba(platba);
    platbaNajemneho1.setNajemnik(osobaService.getOsoba("5bdb0c7b4f0e8eb71860baab"));
    platbaNajemnehoList.add(platbaNajemneho1);

    PlatbaNajemneho platbaNajemneho2 = new PlatbaNajemneho();
    platbaNajemneho2.setDatumPlatby(new Date());
    Cislo platba2 = new Cislo();
    platba2.setMnozstvi(456.0);
    platba2.setJednotka("Kč");
    platbaNajemneho2.setPlatba(platba2);
    platbaNajemneho2.setNajemnik(osobaService.getOsoba("5bdb0c7b4f0e8eb71860baab"));
    platbaNajemnehoList.add(platbaNajemneho2);

    return platbaNajemnehoList;
  }

  @Data
  public class PlatbaNajemneho {
    private Date datumPlatby;
    private Cislo platba;
    private Osoba najemnik;
    private NajemniSmlouva najemniSmlouva;
  }

  public Cislo getPlatbaNajemnehoCelkem() {
    Cislo platbaNajemnehoCelkem = new Cislo();
    platbaNajemnehoCelkem.setMnozstvi(0.0);
    platbaNajemnehoCelkem.setJednotka("Kč");

    List<PlatbaNajemneho> platbaNajemnehoList = getPlatbaNajemnehoList();
    for (PlatbaNajemneho platbaNajemneho : platbaNajemnehoList) {
      // TODO: zkontrolovat měnu / jednotku
      platbaNajemnehoCelkem.setMnozstvi(platbaNajemnehoCelkem.getMnozstvi() + platbaNajemneho.getPlatba().getMnozstvi());
    }
    return platbaNajemnehoCelkem;
  }

  public List<VyuctovaniPolozka> getVyuctovaniPolozkaList(PolozkaTyp polozkaTyp) {

    //List<PolozkaTyp> polozkaTypList = polozkaTypService.getPolozkaTypListByIdNemovitost(vyuctovani.getNemovitost().getId());

    List<VyuctovaniPolozka> vyuctovaniPolozkaList = new ArrayList<>();
    //for (PolozkaTyp polozkaTyp : polozkaTypList) {
    //}
    vyuctovaniPolozkaList.addAll(vyuctovaniPolozkaService.getVyuctovaniPolozkaList(polozkaTyp.getId(), vyuctovani.getId()));

    return vyuctovaniPolozkaList;
  }


  public List<CelkoveVyuctovani> getCelkoveVyuctovani() {
    List<CelkoveVyuctovani> celkoveVyuctovaniList = new ArrayList<>();
    CelkoveVyuctovani celkoveVyuctovani = new CelkoveVyuctovani();
    celkoveVyuctovani.setZalohyCelkem(getPlatbaNajemnehoCelkem());
    Cislo nakladyCelkem = new Cislo();
    nakladyCelkem.setMnozstvi(celkemNaklady);
    nakladyCelkem.setJednotka("Kč");
    celkoveVyuctovani.setNakladyCelkem(nakladyCelkem);

    Cislo rozdil = new Cislo();
    rozdil.setMnozstvi(celkoveVyuctovani.getZalohyCelkem().getMnozstvi() - celkoveVyuctovani.getNakladyCelkem().getMnozstvi());
    rozdil.setJednotka("Kč");
    celkoveVyuctovani.setRozdil(rozdil);
    celkoveVyuctovaniList.add(celkoveVyuctovani);
    return celkoveVyuctovaniList;
  }

  @Data
  public class CelkoveVyuctovani {
    private Cislo zalohyCelkem;
    private Cislo nakladyCelkem;
    private Cislo rozdil;
  }
}
