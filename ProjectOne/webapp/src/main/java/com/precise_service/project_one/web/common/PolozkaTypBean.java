package com.precise_service.project_one.web.common;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PolozkaTypBean extends AbstractBean {

  private Nemovitost nemovitost;
  private List<PolozkaTyp> polozkaTypList;
  private List<PolozkaTyp> filtrovanyPolozkaTypList;

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

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", polozkaTyp.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void zrusitUpravuPolozkaTyp(RowEditEvent event) {
    log.trace("zrusitUpravuPolozkaTyp()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((PolozkaTyp) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void pridatPolozkaTyp() {
    log.trace("pridatPolozkaTyp()");

    PolozkaTyp polozkaTyp = new PolozkaTyp();
    polozkaTyp.setNazev("- zadejte -");
    polozkaTyp.setJednotka("- zadejte -");
    polozkaTyp.setPopis("- zadejte -");
    polozkaTyp.setUzivatel(Util.getPrihlasenyUzivatel());
    polozkaTyp.setNemovitost(nemovitost);

    PolozkaTyp saved = polozkaTypService.postPolozkaTyp(polozkaTyp);
    init(nemovitost.getId());

    FacesMessage msg = new FacesMessage("Přidána nový typ vyúčtovávané položky", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void smazatPolozkaTyp(PolozkaTyp deletedPolozkaTyp) {
    log.trace("smazatPolozkaTyp()");

    if (deletedPolozkaTyp == null){
      log.warn("deleted row is null");
      return;
    }

    log.trace("deleting row with: " + deletedPolozkaTyp.toString());
    polozkaTypService.deletePolozkaTyp(deletedPolozkaTyp.getId());

    FacesMessage msg = new FacesMessage("Smazán typ vyúčtovávané položky", deletedPolozkaTyp.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init(nemovitost.getId());
  }
}
