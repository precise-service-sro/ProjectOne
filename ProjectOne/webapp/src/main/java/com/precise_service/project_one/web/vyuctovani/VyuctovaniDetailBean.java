package com.precise_service.project_one.web.vyuctovani;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;
import com.precise_service.project_one.entity.vyuctovani.VyuctovaniCislo;
import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozkaTyp;
import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozka;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniPolozkaTypService;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniPolozkaService;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniDetailBean implements Serializable {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @Autowired
  private IVyuctovaniPolozkaService vyuctovaniPolozkaService;

  @Autowired
  private IVyuctovaniPolozkaTypService vyuctovaniPolozkaTypService;

  public static final String ZUCTOVACI_OBDOBI_DATE_FORMAT = "dd/MM/yyyy";

  private List<VyuctovaniPolozkaTyp> vyuctovaniPolozkaTypList;
  private Vyuctovani vyuctovani;
  private List<VyuctovaniPolozka> radkyVyuctovani;

  // TODO: tyhle celkovy soucty spocitat a ulozit na entitu celeho vyuctovani, at se to tu nedela pokazde znova
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  //@PostConstruct
  public void init() {
    log.trace("init()");

    vyuctovaniPolozkaTypList = vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTypAll();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate from = LocalDate.parse("01-01-2017", dateTimeFormatter);
    LocalDate to = LocalDate.parse("31-12-2017", dateTimeFormatter);

    if (vyuctovani == null) {
      // TODO: tohle budu moct ve finale uplne smazat
      List<Vyuctovani> vyuctovaniInRange = vyuctovaniService.getVyuctovaniInRange(from, to);
      vyuctovani = vyuctovaniInRange.get(0);
    }

    radkyVyuctovani = vyuctovaniPolozkaService.getVyuctovaniPolozkaAll(vyuctovani.getId());

    for (VyuctovaniPolozka vyuctovaniPolozka : radkyVyuctovani) {
      if (vyuctovaniPolozka.getPocatecniStav() == null) {
        VyuctovaniCislo pocatecniStav = new VyuctovaniCislo();
        pocatecniStav.setMnozstvi(0.0);
        pocatecniStav.setJednotka("ks");
        vyuctovaniPolozka.setPocatecniStav(pocatecniStav);

        if (vyuctovaniPolozka.getKoncovyStav() == null) {
          VyuctovaniCislo koncovyStav = new VyuctovaniCislo();
          koncovyStav.setMnozstvi(1.0);
          koncovyStav.setJednotka(pocatecniStav.getJednotka());
          vyuctovaniPolozka.setKoncovyStav(koncovyStav);
        }
      }

      if (vyuctovaniPolozka.getKoncovyStav() == null) {
        VyuctovaniCislo koncovyStav = new VyuctovaniCislo();
        koncovyStav.setMnozstvi(0.0);
        koncovyStav.setJednotka(vyuctovaniPolozka.getPocatecniStav().getJednotka());
        vyuctovaniPolozka.setKoncovyStav(koncovyStav);
      }

      VyuctovaniCislo spotreba = new VyuctovaniCislo();
      spotreba.setMnozstvi(vyuctovaniPolozka.getKoncovyStav().getMnozstvi() - vyuctovaniPolozka.getPocatecniStav().getMnozstvi());
      spotreba.setJednotka(vyuctovaniPolozka.getKoncovyStav().getJednotka());
      vyuctovaniPolozka.setSpotreba(spotreba);

      if (vyuctovaniPolozka.getZalohy() == null) {
        VyuctovaniCislo zalohy = new VyuctovaniCislo();
        zalohy.setMnozstvi(0.0);
        zalohy.setJednotka("Kč");
        vyuctovaniPolozka.setZalohy(zalohy);
      }

      VyuctovaniCislo rozdil = new VyuctovaniCislo();
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

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", vyuctovaniPolozka.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((VyuctovaniPolozka) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void addRow() {
    log.trace("addRow()");

    VyuctovaniPolozka vyuctovaniPolozka = new VyuctovaniPolozka();

    vyuctovaniPolozka.setNazev("!!! Upravit název !!!");
    vyuctovaniPolozka.setVyuctovani(vyuctovani);
    vyuctovaniPolozka.setVyuctovaniPolozkaTyp(null);

    VyuctovaniCislo vychoziStav = new VyuctovaniCislo();
    vychoziStav.setMnozstvi(0.0);
    vychoziStav.setJednotka("Ks");
    vyuctovaniPolozka.setPocatecniStav(vychoziStav);
    vyuctovaniPolozka.setKoncovyStav(vychoziStav);

    VyuctovaniCislo vychoziZalohyNeboNaklady = new VyuctovaniCislo();
    vychoziZalohyNeboNaklady.setMnozstvi(0.0);
    vychoziZalohyNeboNaklady.setJednotka("Kč");
    vyuctovaniPolozka.setZalohy(vychoziZalohyNeboNaklady);
    vyuctovaniPolozka.setNaklady(vychoziZalohyNeboNaklady);

    VyuctovaniPolozka saved = vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(VyuctovaniPolozka deletedVyuctovaniPolozka) {
    log.trace("deleteRow()");

    if (deletedVyuctovaniPolozka == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedVyuctovaniPolozka.toString());

    vyuctovaniPolozkaService.deleteVyuctovaniPolozka(deletedVyuctovaniPolozka.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedVyuctovaniPolozka.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
