package com.precise_service.project_one.web.nemovitost;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostKontakt;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NemovitostKontaktBean extends AbstractBean {

  private Nemovitost nemovitost;
  private List<Osoba> osobaList;

  private List<NemovitostKontakt> nemovitostKontaktList;
  private List<NemovitostKontakt> filtrovanyNemovitostKontaktList;

  public void init(String idNemovitost) {
    nemovitost = nemovitostService.getNemovitost(idNemovitost);
    nemovitostKontaktList = nemovitostKontaktService.getNemovitostKontaktAll(idNemovitost, Util.getPrihlasenyUzivatel().getId());
    filtrovanyNemovitostKontaktList = null;
  }

  public List<NemovitostKontakt> getNemovitostKontaktList() {
    return nemovitostKontaktList;
  }

  public List<NemovitostKontakt> getFiltrovanyNemovitostKontaktList() {
    return filtrovanyNemovitostKontaktList;
  }

  public void pridatNemovitostKontakt() {
    log.trace("pridatNemovitostKontakt()");

    NemovitostKontakt nemovitostKontakt = new NemovitostKontakt();
    nemovitostKontakt.setJmeno("- zadejte -");
    nemovitostKontakt.setPrijmeni("- zadejte -");
    nemovitostKontakt.setTelefon("- zadejte -");
    nemovitostKontakt.setEmail("- zadejte -");
    nemovitostKontakt.setUzivatel(Util.getPrihlasenyUzivatel());
    nemovitostKontakt.setNemovitost(nemovitost);

    NemovitostKontakt saved = nemovitostKontaktService.postNemovitostKontakt(nemovitostKontakt);

    init(nemovitost.getId());

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void smazatNemovitostKontakt(NemovitostKontakt deletedNemovitostKontakt) {
    log.trace("smazatNemovitostKontakt()");

      if (deletedNemovitostKontakt == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedNemovitostKontakt.toString());

    nemovitostKontaktService.deleteNemovitostKontakt(deletedNemovitostKontakt.getId());

    FacesMessage msg = new FacesMessage("Smazán kontakt", deletedNemovitostKontakt.getCeleJmeno());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init(nemovitost.getId());
  }

  public void ulozitUpravuNemovitostKontakt(RowEditEvent event) {
    log.trace("ulozitUpravuNemovitostKontakt()");
    NemovitostKontakt nemovitostKontakt = (NemovitostKontakt) event.getObject();

    nemovitostKontaktService.putNemovitostKontakt(nemovitostKontakt);

    FacesMessage msg = new FacesMessage("Uložena úprava kontaktu", nemovitostKontakt.getCeleJmeno());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void zrusitUpravuNemovitostKontakt(RowEditEvent event) {
    log.trace("zrusitUpravuNemovitostKontakt()");
    FacesMessage msg = new FacesMessage("Zrušena úprava kontaktu", ((NemovitostKontakt) event.getObject()).getCeleJmeno());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
