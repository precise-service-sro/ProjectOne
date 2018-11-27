package com.precise_service.project_one.web.pronajem;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.pronajem.PredavaciProtokol;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PredavaciProtokolPrehledBean extends AbstractBean {

  private List<PredavaciProtokol> predavaciProtokolList;
  private List<PredavaciProtokol> filtrovanyPredavaciProtokolList;
  private List<Nemovitost> nemovitostList;
  private List<Osoba> seznamNajemniku;
  private int predavaciProtokolListSize;
  private int filtrovanyPredavaciProtokolListSize;

  public void init() {
    log.trace("init()");

    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    predavaciProtokolList = predavaciProtokolService.getPredavaciProtokolAll(prihlasenyUzivatel.getId());
    nemovitostList = nemovitostService.getNemovitostListByVlastnik(prihlasenyUzivatel.getId());

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

  public void pridatPredavaciProtokol() {
    log.trace("pridatPredavaciProtokol()");

    PredavaciProtokol predavaciProtokol = new PredavaciProtokol();

    predavaciProtokol.setNazev("- zadejte - ");
    predavaciProtokol.setDatumPodpisu(new Date());
    predavaciProtokol.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());

    predavaciProtokolService.postPredavaciProtokol(predavaciProtokol);
    init();

    showInfoMessage("Přidán", "Přidán nový předávací protokol");
  }

  public void smazatPredavaciProtokol(PredavaciProtokol deletedPredavaciProtokol) {
    log.trace("smazatPredavaciProtokol()");

    if (deletedPredavaciProtokol == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedPredavaciProtokol.toString());

    predavaciProtokolService.deletePredavaciProtokol(deletedPredavaciProtokol.getId());

    showInfoMessage("Smazán", "Smazán předávací protokol");
    init();
  }

  public int getPredavaciProtokolListSize() {
    if (predavaciProtokolList == null) {
      return 0;
    }
    return predavaciProtokolList.size();
  }

  public int getFiltrovanyPredavaciProtokolListSize() {
    if (filtrovanyPredavaciProtokolList == null) {
      return getPredavaciProtokolListSize();
    }
    return filtrovanyPredavaciProtokolList.size();
  }
}
