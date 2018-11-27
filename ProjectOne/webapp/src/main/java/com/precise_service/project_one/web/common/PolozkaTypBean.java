package com.precise_service.project_one.web.common;

import java.util.List;

import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PolozkaTypBean extends AbstractBean {

  private Nemovitost nemovitost;
  private List<PolozkaTyp> polozkaTypList;
  private List<PolozkaTyp> filtrovanyPolozkaTypList;
  private int polozkaTypListSize;
  private int filtrovanyPolozkaTypListSize;

  public void init(String idNemovitost) {
    log.trace("init()");
    nemovitost = nemovitostService.getNemovitost(idNemovitost);
    polozkaTypList = polozkaTypService.getPolozkaTypListByIdNemovitost(nemovitost.getId());
    filtrovanyPolozkaTypList = null;
  }

  public void ulozitUpravuPolozkaTyp(RowEditEvent event) {
    log.trace("ulozitUpravuPolozkaTyp()");
    PolozkaTyp polozkaTyp = (PolozkaTyp) event.getObject();

    polozkaTypService.putPolozkaTyp(polozkaTyp);

    showInfoMessage("Uložena úprava řádky", polozkaTyp.getNazev());
  }

  public void zrusitUpravuPolozkaTyp(RowEditEvent event) {
    log.trace("zrusitUpravuPolozkaTyp()");
    showInfoMessage("Zrušena úprava řádky", ((PolozkaTyp) event.getObject()).getNazev());
  }

  public void pridatPolozkaTyp() {
    log.trace("pridatPolozkaTyp()");

    PolozkaTyp polozkaTyp = new PolozkaTyp();
    polozkaTyp.setNazev("- zadejte -");
    polozkaTyp.setJednotka("- zadejte -");
    polozkaTyp.setPopis("- zadejte -");
    polozkaTyp.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());
    polozkaTyp.setNemovitost(nemovitost);

    PolozkaTyp saved = polozkaTypService.postPolozkaTyp(polozkaTyp);
    showInfoMessage("Přidána nový typ vyúčtovávané položky", saved.getId());
    init(nemovitost.getId());
  }

  public void smazatPolozkaTyp(PolozkaTyp deletedPolozkaTyp) {
    log.trace("smazatPolozkaTyp()");

    if (deletedPolozkaTyp == null){
      log.warn("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedPolozkaTyp.toString());

    polozkaTypService.deletePolozkaTyp(deletedPolozkaTyp.getId());
    showInfoMessage("Smazán typ vyúčtovávané položky", deletedPolozkaTyp.getNazev());
    init(nemovitost.getId());
  }

  public int getPolozkaTypListSize() {
    if (polozkaTypList == null) {
      return 0;
    }
    return polozkaTypList.size();
  }

  public int getFiltrovanyPolozkaTypListSize() {
    if (filtrovanyPolozkaTypList == null) {
      return getPolozkaTypListSize();
    }
    return filtrovanyPolozkaTypList.size();
  }
}
