package com.precise_service.project_one.web.nemovitost;

import java.util.List;

import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostKontakt;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;

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
    nemovitostKontaktList = nemovitostKontaktService.getNemovitostKontaktAll(idNemovitost, loginBean.getPrihlasenyUzivatel().getId());
    filtrovanyNemovitostKontaktList = null;
  }

  public void pridatNemovitostKontakt() {
    log.trace("pridatNemovitostKontakt()");

    NemovitostKontakt nemovitostKontakt = new NemovitostKontakt();
    nemovitostKontakt.setJmeno("- zadejte -");
    nemovitostKontakt.setPrijmeni("- zadejte -");
    nemovitostKontakt.setTelefon("- zadejte -");
    nemovitostKontakt.setEmail("- zadejte -");
    nemovitostKontakt.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());
    nemovitostKontakt.setNemovitost(nemovitost);

    NemovitostKontakt saved = nemovitostKontaktService.postNemovitostKontakt(nemovitostKontakt);

    showInfoMessage("Přidán nový kontakt", saved.getId());

    init(nemovitost.getId());
  }

  public void smazatNemovitostKontakt(NemovitostKontakt deletedNemovitostKontakt) {
    log.trace("smazatNemovitostKontakt()");

      if (deletedNemovitostKontakt == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedNemovitostKontakt.toString());

    nemovitostKontaktService.deleteNemovitostKontakt(deletedNemovitostKontakt.getId());

    showInfoMessage("Smazán kontakt", deletedNemovitostKontakt.getCeleJmeno());
    init(nemovitost.getId());
  }

  public void ulozitUpravuNemovitostKontakt(RowEditEvent event) {
    log.trace("ulozitUpravuNemovitostKontakt()");
    NemovitostKontakt nemovitostKontakt = (NemovitostKontakt) event.getObject();

    nemovitostKontaktService.putNemovitostKontakt(nemovitostKontakt);

    showInfoMessage("Uložena úprava kontaktu", nemovitostKontakt.getCeleJmeno());
  }

  public void zrusitUpravuNemovitostKontakt(RowEditEvent event) {
    log.trace("zrusitUpravuNemovitostKontakt()");
    showInfoMessage("Zrušena úprava kontaktu", ((NemovitostKontakt) event.getObject()).getCeleJmeno());
  }
}
