package com.precise_service.project_one.web.byt.vyuctovani;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniCislo;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTyp;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaByt;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytPolozka;
import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozka;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniPolozkaTypService;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniZaBytPolozkaService;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniZaBytService;
import com.precise_service.project_one.web.byt.vyuctovani.tabulka.VyuctovaniZaBytTabulkaBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.commons.DateFormatter.format;

@Slf4j
@Data
@Named
public class VyuctovaniZaBytDetailBean implements Serializable {

  @Autowired
  private IVyuctovaniZaBytService vyuctovaniZaBytService;

  @Autowired
  private IVyuctovaniZaBytPolozkaService vyuctovaniZaBytPolozkaService;

  @Autowired
  private IVyuctovaniPolozkaTypService vyuctovaniPolozkaTypService;

  public static final String ZUCTOVACI_OBDOBI_DATE_FORMAT = "dd/MM/yyyy";

  private List<VyuctovaniPolozkaTyp> vyuctovaniPolozkaTypList;
  private VyuctovaniZaByt vyuctovaniZaByt;
  private List<VyuctovaniZaBytPolozka> radkyVyuctovani;

  // TODO: tyhle celkovy soucty spocitat a ulozit na entitu celeho vyuctovani, at se to tu nedela pokazde znova
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  @PostConstruct
  public void init() {
    log.trace("init()");

    vyuctovaniPolozkaTypList = vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTypAll();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate from = LocalDate.parse("01-01-2017", dateTimeFormatter);
    LocalDate to = LocalDate.parse("31-12-2017", dateTimeFormatter);

    List<VyuctovaniZaByt> vyuctovaniInRange = vyuctovaniZaBytService.getVyuctovaniZaBytInRange(from, to);
    vyuctovaniZaByt = vyuctovaniInRange.get(0);

    radkyVyuctovani = vyuctovaniZaBytPolozkaService.getVyuctovaniZaBytPolozkaAll(vyuctovaniZaByt.getId());

    for (VyuctovaniZaBytPolozka vyuctovaniZaBytPolozka : radkyVyuctovani) {
      if (vyuctovaniZaBytPolozka.getPocatecniStav() == null) {
        VyuctovaniCislo pocatecniStav = new VyuctovaniCislo();
        pocatecniStav.setMnozstvi(0.0);
        pocatecniStav.setJednotka("ks");
        vyuctovaniZaBytPolozka.setPocatecniStav(pocatecniStav);

        if (vyuctovaniZaBytPolozka.getKoncovyStav() == null) {
          VyuctovaniCislo koncovyStav = new VyuctovaniCislo();
          koncovyStav.setMnozstvi(1.0);
          koncovyStav.setJednotka(pocatecniStav.getJednotka());
          vyuctovaniZaBytPolozka.setKoncovyStav(koncovyStav);
        }
      }

      if (vyuctovaniZaBytPolozka.getKoncovyStav() == null) {
        VyuctovaniCislo koncovyStav = new VyuctovaniCislo();
        koncovyStav.setMnozstvi(0.0);
        koncovyStav.setJednotka(vyuctovaniZaBytPolozka.getPocatecniStav().getJednotka());
        vyuctovaniZaBytPolozka.setKoncovyStav(koncovyStav);
      }

      VyuctovaniCislo spotreba = new VyuctovaniCislo();
      spotreba.setMnozstvi(vyuctovaniZaBytPolozka.getKoncovyStav().getMnozstvi() - vyuctovaniZaBytPolozka.getPocatecniStav().getMnozstvi());
      spotreba.setJednotka(vyuctovaniZaBytPolozka.getKoncovyStav().getJednotka());
      vyuctovaniZaBytPolozka.setSpotreba(spotreba);

      if (vyuctovaniZaBytPolozka.getZalohy() == null) {
        VyuctovaniCislo zalohy = new VyuctovaniCislo();
        zalohy.setMnozstvi(0.0);
        zalohy.setJednotka("Kč");
        vyuctovaniZaBytPolozka.setZalohy(zalohy);
      }

      VyuctovaniCislo rozdil = new VyuctovaniCislo();
      rozdil.setMnozstvi(vyuctovaniZaBytPolozka.getZalohy().getMnozstvi() - vyuctovaniZaBytPolozka.getNaklady().getMnozstvi());
      rozdil.setJednotka(vyuctovaniZaBytPolozka.getNaklady().getJednotka());
      vyuctovaniZaBytPolozka.setRozdil(rozdil);

      vyuctovaniZaBytPolozkaService.putVyuctovaniZaBytPolozka(vyuctovaniZaBytPolozka);
    }

    spocitatSoucty();
  }

  private void spocitatSoucty(){
    celkemZalohy = 0.0;
    celkemNaklady = 0.0;
    celkemRozdil = 0.0;
    for (VyuctovaniZaBytPolozka polozka : radkyVyuctovani){
      celkemZalohy += polozka.getZalohy().getMnozstvi();
      celkemNaklady += polozka.getNaklady().getMnozstvi();
      celkemRozdil += polozka.getRozdil().getMnozstvi();
    }
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    VyuctovaniZaBytPolozka vyuctovaniZaBytPolozka = (VyuctovaniZaBytPolozka) event.getObject();

    vyuctovaniZaBytPolozkaService.putVyuctovaniZaBytPolozka(vyuctovaniZaBytPolozka);
    init();

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", vyuctovaniZaBytPolozka.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((VyuctovaniZaBytPolozka) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void addRow() {
    log.trace("addRow()");

    VyuctovaniZaBytPolozka vyuctovaniZaBytPolozka = new VyuctovaniZaBytPolozka();

    vyuctovaniZaBytPolozka.setNazev("!!! Upravit název !!!");
    vyuctovaniZaBytPolozka.setVyuctovaniZaByt(vyuctovaniZaByt);
    vyuctovaniZaBytPolozka.setVyuctovaniPolozkaTyp(null);

    VyuctovaniCislo vychoziStav = new VyuctovaniCislo();
    vychoziStav.setMnozstvi(0.0);
    vychoziStav.setJednotka("Ks");
    vyuctovaniZaBytPolozka.setPocatecniStav(vychoziStav);
    vyuctovaniZaBytPolozka.setKoncovyStav(vychoziStav);

    VyuctovaniCislo vychoziZalohyNeboNaklady = new VyuctovaniCislo();
    vychoziZalohyNeboNaklady.setMnozstvi(0.0);
    vychoziZalohyNeboNaklady.setJednotka("Kč");
    vyuctovaniZaBytPolozka.setZalohy(vychoziZalohyNeboNaklady);
    vyuctovaniZaBytPolozka.setNaklady(vychoziZalohyNeboNaklady);

    VyuctovaniZaBytPolozka saved = vyuctovaniZaBytPolozkaService.postVyuctovaniZaBytPolozka(vyuctovaniZaBytPolozka);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(VyuctovaniZaBytPolozka deletedVyuctovaniZaBytPolozka) {
    log.trace("deleteRow()");

    if (deletedVyuctovaniZaBytPolozka == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedVyuctovaniZaBytPolozka.toString());

    vyuctovaniZaBytPolozkaService.deleteVyuctovaniZaBytPolozka(deletedVyuctovaniZaBytPolozka.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedVyuctovaniZaBytPolozka.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
