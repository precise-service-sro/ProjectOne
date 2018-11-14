package com.precise_service.project_one.web.predavaci_protokol;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.web.URL_CONST.PREDAVACI_PROTOKOL_DETAIL_URL;

@Slf4j
@Data
@Named
public class PredavaciProtokolPrehledBean extends AbstractBean {

  private List<PredavaciProtokol> predavaciProtokolList;
  private List<PredavaciProtokol> filtrovanyPredavaciProtokolList;
  private List<Nemovitost> nemovitostList;
  private List<Osoba> seznamNajemniku;

  public void init() {
    log.trace("init()");

    Osoba prihlasenyUzivatel = Util.getPrihlasenyUzivatel();
    predavaciProtokolList = predavaciProtokolService.getPredavaciProtokolAll(prihlasenyUzivatel.getId());
    nemovitostList = nemovitostService.getNemovitostAll(prihlasenyUzivatel.getId());

    // TODO: filtrovat tyto osoby / najemniky dle existence a platnosti najemni smlouvy
    seznamNajemniku = osobaService.getOsobaAll(prihlasenyUzivatel.getId());
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    PredavaciProtokol predavaciProtokol = (PredavaciProtokol) event.getObject();

    predavaciProtokolService.putPredavaciProtokol(predavaciProtokol);

    showInfoMessage("Uložena úprava řádky", predavaciProtokol.getNazev());
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    showInfoMessage("Zrušena úprava řádky", ((PredavaciProtokol) event.getObject()).getNazev());
  }

  public void addRow() {
    log.trace("addRow()");

    PredavaciProtokol predavaciProtokol = new PredavaciProtokol();

    predavaciProtokol.setNazev("- zadejte - ");
    predavaciProtokol.setDatumPodpisu(new Date());
    predavaciProtokol.setUzivatel(Util.getPrihlasenyUzivatel());

    PredavaciProtokol saved = predavaciProtokolService.postPredavaciProtokol(predavaciProtokol);
    init();

    showInfoMessage("Přidána nová řádka", saved.getId());
  }

  public void deleteRow(PredavaciProtokol deletedPredavaciProtokol) {
    log.trace("deleteRow()");

    if (deletedPredavaciProtokol == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedPredavaciProtokol.toString());

    predavaciProtokolService.deletePredavaciProtokol(deletedPredavaciProtokol.getId());

    showInfoMessage("Smazán řádek", deletedPredavaciProtokol.getNazev());
    init();
  }

  public void showPredavaciProtokolDetailBean(PredavaciProtokol predavaciProtokol) throws IOException {
    predavaciProtokolDetailBean.setPredavaciProtokol(predavaciProtokol);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(PREDAVACI_PROTOKOL_DETAIL_URL);
  }
}
