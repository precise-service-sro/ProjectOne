package com.precise_service.project_one.web.byt.vyuctovani.tabulka;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.commons.DateFormatter.format;


@Slf4j
@Data
@Named
public class VyuctovaniZaBytTabulkaBean implements Serializable {

  public static final String ZUCTOVACI_OBDOBI_DATE_FORMAT = "dd/MM/yyyy";

  @Autowired
  private IVyuctovaniZaBytService vyuctovaniZaBytService;

  @Autowired
  private IVyuctovaniZaBytPolozkaService vyuctovaniZaBytPolozkaService;

  @Autowired
  private IVyuctovaniPolozkaTypService vyuctovaniPolozkaTypService;

  private String nazev;
  private String popis;
  private String zuctovaciObdobi;
  private List<VyuctovaniZaBytPolozka> radkyVyuctovani;
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  private List<VyuctovaniPolozkaTyp> vyuctovaniPolozkaTypList;

  public void prepareData(String idVyuctovaniZaByt) {
    log.trace("prepareData()");
    log.debug("idVyuctovaniZaByt: " + idVyuctovaniZaByt);
    radkyVyuctovani = new ArrayList<>();

    VyuctovaniZaByt vyuctovaniZaByt = vyuctovaniZaBytService.getVyuctovaniZaByt(idVyuctovaniZaByt);

    nazev = vyuctovaniZaByt.getNazev();
    if (vyuctovaniZaByt.getZuctovaciObdobi() != null) {
      LocalDate zacatekZuctovacihoObdobi = vyuctovaniZaByt.getZuctovaciObdobi().getZacatek();
      LocalDate konecZuctovacihoObdobi = vyuctovaniZaByt.getZuctovaciObdobi().getKonec();
      zuctovaciObdobi = "(" + format(zacatekZuctovacihoObdobi, ZUCTOVACI_OBDOBI_DATE_FORMAT) + " - " + format(konecZuctovacihoObdobi, ZUCTOVACI_OBDOBI_DATE_FORMAT) + ")";
    }

    vyuctovaniPolozkaTypList = vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTypAll();

    radkyVyuctovani = vyuctovaniZaBytPolozkaService.getVyuctovaniZaBytPolozkaAll(idVyuctovaniZaByt);


    for (VyuctovaniZaBytPolozka vyuctovaniZaBytPolozka : radkyVyuctovani) {

      if (vyuctovaniZaBytPolozka.getPocatecniStav() == null) {
        VyuctovaniCislo pocatecniStav = new VyuctovaniCislo();
        pocatecniStav.setMnozstvi(0.0);
        pocatecniStav.setJednotka("ks");
        vyuctovaniZaBytPolozka.setPocatecniStav(pocatecniStav);

        if (vyuctovaniZaBytPolozka.getKoncovyStav() == null) {
          VyuctovaniCislo koncovyStav = new VyuctovaniCislo();
          koncovyStav.setMnozstvi(1.0);
          koncovyStav.setJednotka("ks");
          vyuctovaniZaBytPolozka.setKoncovyStav(koncovyStav);
        }
      }

      if (vyuctovaniZaBytPolozka.getKoncovyStav() == null) {
        VyuctovaniCislo koncovyStav = new VyuctovaniCislo();
        koncovyStav.setMnozstvi(0.0);
        koncovyStav.setJednotka("ks");
        vyuctovaniZaBytPolozka.setKoncovyStav(koncovyStav);
      }

      if (vyuctovaniZaBytPolozka.getSpotreba() == null) {
        VyuctovaniCislo spotreba = new VyuctovaniCislo();
        spotreba.setMnozstvi(1.00);
        spotreba.setJednotka("ks");
        vyuctovaniZaBytPolozka.setSpotreba(spotreba);
      }

      if (vyuctovaniZaBytPolozka.getZalohy() == null) {
        VyuctovaniCislo zalohy = new VyuctovaniCislo();
        zalohy.setMnozstvi(0.0);
        zalohy.setJednotka("ks");
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

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", vyuctovaniZaBytPolozka.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((PredavaciProtokolPolozka) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
