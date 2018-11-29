package com.precise_service.project_one.web.pronajem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.filter.DatovyFilter;
import com.precise_service.project_one.entity.filter.typ.PlatbaNajemnehoFilter;
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
  private List<VyuctovaniPolozka> vypocitanaVyuctovaniPolozkaList;

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

    vypocitanaVyuctovaniPolozkaList = vyuctovaniPolozkaService.getVypocitanaVyuctovaniPolozkaList(vyuctovani.getId());
    polozkaTypList = polozkaTypService.getPolozkaTypListByIdNemovitost(vyuctovani.getNemovitost().getId());

    spocitatSoucty();
  }

  private void spocitatSoucty(){
    celkemZalohy = 0.0;
    celkemNaklady = 0.0;
    celkemRozdil = 0.0;
    for (VyuctovaniPolozka polozka : vypocitanaVyuctovaniPolozkaList){
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
    // TODO: pridelat filtrovani dle nemovitosti, najemni smlouvy, lidi atd
    DatovyFilter datovyFilter = new DatovyFilter();
    PlatbaNajemnehoFilter platbaNajemnehoFilter = new PlatbaNajemnehoFilter();
    platbaNajemnehoFilter.setIdOdesilatel("5bdb0c7b4f0e8eb71860baab");
    datovyFilter.setPlatbaNajemnehoFilter(platbaNajemnehoFilter);

    // TODO: tady musim jeste nejak poslat pouze zalohy, nikoliv cely najem
    return platbaNajemnehoService.getPlatbaNajemnehoList(datovyFilter);
  }

  public Cislo getPlatbaNajemnehoCelkem() {
    Cislo platbaNajemnehoCelkem = new Cislo();
    platbaNajemnehoCelkem.setMnozstvi(0.0);
    platbaNajemnehoCelkem.setJednotka("Kč");

    for (PlatbaNajemneho platbaNajemneho : getPlatbaNajemnehoList()) {
      // TODO: zkontrolovat měnu / jednotku
      platbaNajemnehoCelkem.setMnozstvi(platbaNajemnehoCelkem.getMnozstvi() + platbaNajemneho.getCastka().getMnozstvi());
    }
    return platbaNajemnehoCelkem;
  }

  public List<VyuctovaniPolozka> getVyuctovaniPolozkaList(PolozkaTyp polozkaTyp) {
    return vyuctovaniPolozkaService.getVyuctovaniPolozkaList(polozkaTyp.getId(), vyuctovani.getId());
  }

  public List<CelkoveVyuctovani> getCelkoveVyuctovani() {
    vyuctovani.setCelkoveZalohy(getPlatbaNajemnehoCelkem());
    Cislo nakladyCelkem = new Cislo();
    nakladyCelkem.setMnozstvi(celkemNaklady);
    nakladyCelkem.setJednotka("Kč");
    vyuctovani.setCelkoveNaklady(nakladyCelkem);
    vyuctovani.setVysledekVyuctovani(calculatorService.odecist(vyuctovani.getCelkoveZalohy(), vyuctovani.getCelkoveNaklady()));
    vyuctovani = vyuctovaniService.putVyuctovani(vyuctovani);

    List<CelkoveVyuctovani> celkoveVyuctovaniList = new ArrayList<>();
    CelkoveVyuctovani celkoveVyuctovani = new CelkoveVyuctovani();
    celkoveVyuctovani.setZalohyCelkem(vyuctovani.getCelkoveZalohy());
    celkoveVyuctovani.setNakladyCelkem(vyuctovani.getCelkoveNaklady());
    celkoveVyuctovani.setRozdil(vyuctovani.getVysledekVyuctovani());
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
