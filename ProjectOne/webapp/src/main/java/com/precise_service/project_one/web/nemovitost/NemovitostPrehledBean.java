package com.precise_service.project_one.web.nemovitost;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import com.precise_service.project_one.entity.adresa.Adresa;
import com.precise_service.project_one.entity.filter.NemovitostFilter;
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
    nemovitostList = nemovitostService.getNemovitostList(new NemovitostFilter()
        .setIdOsobaVlastnika(prihlasenyUzivatel.getId())
    );
    filtrovanyNemovitostList = null;
  }

  public void init(Nemovitost nemovitost) {
    nemovitostList = Arrays.asList(nemovitost);
    filtrovanyNemovitostList = null;
  }

  public void pridatNemovitost() {
    log.trace("pridatNemovitost()");

    Nemovitost nemovitost = new Nemovitost();

    nemovitost.setNazev("- zadejte -");
    nemovitost.setAdresa(new Adresa());
    nemovitost.setNemovitostTyp(NemovitostTyp.BYT);
    nemovitost.setVlastnik(loginBean.getPrihlasenyUzivatel());
    nemovitost.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());

    nemovitostService.postNemovitost(nemovitost);

    showInfoMessage("Přidáno", "Nová nemovitost byla přidána");
    init();
  }

  public void smazatNemovitost(Nemovitost deletedNemovitost) {
    log.trace("smazatNemovitost()");

    if (deletedNemovitost == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedNemovitost.toString());

    nemovitostService.deleteNemovitost(deletedNemovitost.getId());

    showInfoMessage("Smazáno", "Nemovist byla smazána");
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
