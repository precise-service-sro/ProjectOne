package com.precise_service.project_one.web.pronajem;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.filter.FakturaFilter;
import com.precise_service.project_one.entity.filter.NemovitostFilter;
import com.precise_service.project_one.entity.filter.OsobaFilter;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.pronajem.PredavaciProtokol;
import com.precise_service.project_one.entity.pronajem.Vyuctovani;
import com.precise_service.project_one.web.AbstractBean;

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
      zuctovaciObdobi.setZacatek(dateFormatterBean.parseDate("01-03-2017"));
      zuctovaciObdobi.setKonec(dateFormatterBean.parseDate("31-12-2017"));
    }

    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    nemovitostList = nemovitostService.getNemovitostList(new NemovitostFilter()
        .setIdOsobaVlastnika(prihlasenyUzivatel.getId())
    );
    seznamNajemniku = osobaService.getOsobaList(new OsobaFilter()
        .setIdPrihlasenyUzivatel(prihlasenyUzivatel.getId())
        .setSeraditVzestupnePodle("prijmeni")
    );
    predavaciProtokolList = predavaciProtokolService.getPredavaciProtokolAll(prihlasenyUzivatel.getId());
  }

  public void generate() throws IOException {
    log.trace("generate()");

    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();

    List<Faktura> fakturaList = fakturaService.getFakturaList(new FakturaFilter()
        .setIdPrihlasenyUzivatel(prihlasenyUzivatel.getId())
        .setIdNemovitost(nemovitost.getId())
        .setCasovyInterval(zuctovaciObdobi)
    );

    String nazev = "Vygenerované vyúčtování ze dne: " + LocalDateTime.now().toString();

    Vyuctovani vyuctovani = vyuctovaniService.generovatVyuctovani(nazev, zuctovaciObdobi, nemovitost, najemnik, fakturaList, predavaciProtokol, null, loginBean.getPrihlasenyUzivatel());

    vyuctovaniDetailBean.setVyuctovani(vyuctovani);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(VYUCTOVANI_DETAIL_URL);
  }

  public void zuctovaciObdobiZacatekDateSelect(SelectEvent event) {
    showInfoMessage("Zvolen nový začátek zúčtovacího období", dateFormatterBean.formatDate((Date) event.getObject()));
    init();
  }

  public void zuctovaciObdobiKonecDateSelect(SelectEvent event) {
    showInfoMessage("Zvolen nový konec zúčtovacího období", dateFormatterBean.formatDate((Date) event.getObject()));
    init();
  }
}
