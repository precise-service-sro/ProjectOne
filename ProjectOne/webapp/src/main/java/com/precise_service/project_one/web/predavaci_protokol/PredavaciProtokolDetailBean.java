package com.precise_service.project_one.web.predavaci_protokol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokolPolozka;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PredavaciProtokolDetailBean extends AbstractBean {

  private PredavaciProtokol predavaciProtokol;
  private List<PredavaciProtokolPolozka> predavaciProtokolPolozkaList;
  private List<PredavaciProtokolPolozka> filtrovanyPredavaciProtokolPolozkaList;
  private List<PolozkaTyp> polozkaTypList;
  private List<Nemovitost> nemovitostList;
  private List<Osoba> osobaList;

  public void init() {
    log.trace("init()");

    if (predavaciProtokol == null) {
      log.error("Není vybrán žádný předávácí protokol k zobrazení detailů");
      return;
    }

    predavaciProtokolPolozkaList = predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaAll(predavaciProtokol.getId());

    Nemovitost nemovitost = predavaciProtokol.getNemovitost();
    polozkaTypList = (nemovitost != null) ? polozkaTypService.getPolozkaTypListByIdNemovitost(nemovitost.getId()) : new ArrayList<>(0);

    Osoba prihlasenyUzivatel = Util.getPrihlasenyUzivatel();
    nemovitostList = nemovitostService.getNemovitostAll(prihlasenyUzivatel.getId());
    osobaList = osobaService.getOsobaAll(prihlasenyUzivatel.getId());
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    PredavaciProtokolPolozka predavaciProtokolPolozka = (PredavaciProtokolPolozka) event.getObject();

    predavaciProtokolPolozkaService.putPredavaciProtokolPolozka(predavaciProtokolPolozka);

    showInfoMessage("Uložena úprava řádky", predavaciProtokolPolozka.getNazev());
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    showInfoMessage("Zrušena úprava řádky", ((PredavaciProtokolPolozka) event.getObject()).getNazev());
  }

  public void addRow() {
    log.trace("addRow()");

    PredavaciProtokolPolozka predavaciProtokolPolozka = new PredavaciProtokolPolozka();

    predavaciProtokolPolozka.setPredavaciProtokol(predavaciProtokol);
    predavaciProtokolPolozka.setNazev("- zadejte -");
    predavaciProtokolPolozka.setPolozkaTyp(null);
    predavaciProtokolPolozka.setCisloMeraku("- zadejte -");
    predavaciProtokolPolozka.setStavMeraku("- zadejte -");
    predavaciProtokolPolozka.setUzivatel(Util.getPrihlasenyUzivatel());

    PredavaciProtokolPolozka saved = predavaciProtokolPolozkaService.postPredavaciProtokolPolozka(predavaciProtokolPolozka);
    showInfoMessage("Přidáno", "Nová položka předávacího protokolu byla přidána (" + saved.id + ")");
    init();
  }

  public void deleteRow(PredavaciProtokolPolozka deletedPredavaciProtokolPolozka) {
    log.trace("deleteRow()");

    if (deletedPredavaciProtokolPolozka == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedPredavaciProtokolPolozka.toString());

    predavaciProtokolPolozkaService.deletePredavaciProtokolPolozka(deletedPredavaciProtokolPolozka.getId());
    showInfoMessage("Smazáno", "Položka předávacího protokolu : " + deletedPredavaciProtokolPolozka.getNazev() + " byla smazána");
    init();
  }

  public void ulozitZmenuPredavacihoProtokolu() throws IOException {
    log.trace("ulozitZmenuPredavacihoProtokolu()");
    predavaciProtokol = predavaciProtokolService.putPredavaciProtokol(predavaciProtokol);
    showInfoMessage("Uloženo", "Úprava předávacího protokolu " + predavaciProtokol.getNazev() + " byla uložena");
    routerBean.goToPredavaciProtokolPrehledBean();
  }

  public void zrusitZmenuPredavacihoProtokolu() throws IOException {
    log.trace("zrusitZmenuPredavacihoProtokolu()");
    showInfoMessage("Zrušeno", "Úprava předávacího protokolu " + predavaciProtokol.getNazev() + " byla zrušena");
    routerBean.goToPredavaciProtokolPrehledBean();
  }
}
