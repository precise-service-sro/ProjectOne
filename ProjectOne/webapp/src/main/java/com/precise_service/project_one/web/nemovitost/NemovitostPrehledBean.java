package com.precise_service.project_one.web.nemovitost;

import java.util.List;

import javax.inject.Named;

import com.precise_service.project_one.entity.adresa.Adresa;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostTyp;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NemovitostPrehledBean extends AbstractBean {

  private List<Nemovitost> nemovitostList;
  private List<Nemovitost> filtrovanyNemovitostList;
  private int nemovitostListSize;
  private int filtrovanyNemovitostListSize;

  public void init() {
    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    nemovitostList = nemovitostService.getNemovitostAll(prihlasenyUzivatel.getId());
    filtrovanyNemovitostList = null;
  }

  public void addRow() {
    log.trace("addRow()");

    Nemovitost nemovitost = new Nemovitost();

    nemovitost.setNazev("- zadejte -");
    nemovitost.setAdresa(new Adresa());
    nemovitost.setNemovitostTyp(NemovitostTyp.BYT);
    nemovitost.setUzivatel(loginBean.getPrihlasenyUzivatel());

    Nemovitost saved = nemovitostService.postNemovitost(nemovitost);

    showInfoMessage("Přidána nová nemovitost", saved.getId());
    init();
  }

  public void deleteRow(Nemovitost deletedNemovitost) {
    log.trace("deleteRow()");

    if (deletedNemovitost == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedNemovitost.toString());

    nemovitostService.deleteNemovitost(deletedNemovitost.getId());

    showInfoMessage("Smazán řádek", deletedNemovitost.getNazev());
    init();
  }

  public int getNemovitostListSize() {
    if (nemovitostList == null) {
      return 0;
    }
    return nemovitostList.size();
  }

  public int getFiltrovanyNemovitostListSize() {
    if (filtrovanyNemovitostList == null) {
      return getNemovitostListSize();
    }
    return filtrovanyNemovitostList.size();
  }
}
