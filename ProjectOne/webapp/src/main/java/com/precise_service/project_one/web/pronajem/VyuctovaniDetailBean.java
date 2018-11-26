package com.precise_service.project_one.web.pronajem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.pronajem.PlatbaNajemneho;
import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.pronajem.PredavaciProtokol;
import com.precise_service.project_one.entity.pronajem.Vyuctovani;
import com.precise_service.project_one.entity.pronajem.VyuctovaniPolozka;
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

    if (vyuctovani == null) {
      log.error("Není vybráno žádné vyúčtování pro zobrazení detailů");
      return;
    }

    radkyVyuctovani = vyuctovaniPolozkaService.getVyuctovaniPolozkaZvyrazneneList(vyuctovani.getId());
    polozkaTypList = polozkaTypService.getPolozkaTypListByIdNemovitost(vyuctovani.getNemovitost().getId());

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
    vyuctovaniPolozka.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());

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

    platbaNajemnehoList.add(vytvoritPlatbaNajemneho(dateFormatterBean.parseDate("27-02-2017")));
    platbaNajemnehoList.add(vytvoritPlatbaNajemneho(dateFormatterBean.parseDate("16-03-2017")));
    platbaNajemnehoList.add(vytvoritPlatbaNajemneho(dateFormatterBean.parseDate("18-04-2017")));
    platbaNajemnehoList.add(vytvoritPlatbaNajemneho(dateFormatterBean.parseDate("16-05-2017")));
    platbaNajemnehoList.add(vytvoritPlatbaNajemneho(dateFormatterBean.parseDate("26-06-2017")));
    platbaNajemnehoList.add(vytvoritPlatbaNajemneho(dateFormatterBean.parseDate("20-07-2017")));
    platbaNajemnehoList.add(vytvoritPlatbaNajemneho(dateFormatterBean.parseDate("17-08-2017")));
    platbaNajemnehoList.add(vytvoritPlatbaNajemneho(dateFormatterBean.parseDate("14-09-2017")));
    platbaNajemnehoList.add(vytvoritPlatbaNajemneho(dateFormatterBean.parseDate("20-10-2017")));
    platbaNajemnehoList.add(vytvoritPlatbaNajemneho(dateFormatterBean.parseDate("20-11-2017")));

    return platbaNajemnehoList;
  }

  private PlatbaNajemneho vytvoritPlatbaNajemneho(Date datumPlatby) {
    PlatbaNajemneho platbaNajemneho = new PlatbaNajemneho();
    platbaNajemneho.setDatumPlatby(datumPlatby);
    Cislo castka = new Cislo();
    castka.setMnozstvi(2800.0);
    castka.setJednotka("Kč");
    platbaNajemneho.setCastka(castka);
    platbaNajemneho.setOdesilatel(osobaService.getOsoba("5bdb0c7b4f0e8eb71860baab"));
    platbaNajemneho.setPrijemce(loginBean.getPrihlasenyUzivatel());
    return platbaNajemneho;
  }

  public Cislo getPlatbaNajemnehoCelkem() {
    Cislo platbaNajemnehoCelkem = new Cislo();
    platbaNajemnehoCelkem.setMnozstvi(0.0);
    platbaNajemnehoCelkem.setJednotka("Kč");

    List<PlatbaNajemneho> platbaNajemnehoList = getPlatbaNajemnehoList();
    for (PlatbaNajemneho platbaNajemneho : platbaNajemnehoList) {
      // TODO: zkontrolovat měnu / jednotku
      platbaNajemnehoCelkem.setMnozstvi(platbaNajemnehoCelkem.getMnozstvi() + platbaNajemneho.getCastka().getMnozstvi());
    }
    return platbaNajemnehoCelkem;
  }

  public List<VyuctovaniPolozka> getVyuctovaniPolozkaList(PolozkaTyp polozkaTyp) {
    return vyuctovaniPolozkaService.getVyuctovaniPolozkaList(polozkaTyp.getId(), vyuctovani.getId());
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
