package com.precise_service.project_one.web.pronajem;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.springframework.util.CollectionUtils;

import com.precise_service.project_one.entity.filter.PlatbaNajemnehoFilter;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.pronajem.DokumentTyp;
import com.precise_service.project_one.entity.pronajem.NajemniSmlouva;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NajemniSmlouvaPrehledBean extends AbstractBean {

  private List<NajemniSmlouva> najemniSmlouvaList;
  private List<NajemniSmlouva> filtrovanyNajemniSmlouvaList;
  private int najemniSmlouvaListSize;
  private int filtrovanyNajemniSmlouvaListSize;
  private Nemovitost nemovitost;

  public void init() {
    init(null);
  }

  public void init(Nemovitost nemovitost) {
    najemniSmlouvaList = najemniSmlouvaService.getNajemniSmlouvaList(new PlatbaNajemnehoFilter()
        .setIdPrihlasenyUzivatel(loginBean.getPrihlasenyUzivatel().getId())
        .setIdNemovitost((nemovitost != null) ? nemovitost.getId() : null)
    );
    filtrovanyNajemniSmlouvaList = null;
  }


  public void pridatNajemniSmlouva() {
    log.trace("pridatNajemniSmlouva()");

    NajemniSmlouva najemniSmlouva = new NajemniSmlouva();
    najemniSmlouva.setDatumPodpisu(new Date());
    najemniSmlouva.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());

    najemniSmlouvaService.postNajemniSmlouva(najemniSmlouva);
    showInfoMessage("Přidáno", "Přidána nová nájemní smlouva");

    init();
  }

  public void smazatNajemniSmlouva(NajemniSmlouva deletedNajemniSmlouva) {
    log.trace("smazatNajemniSmlouva()");

    if (deletedNajemniSmlouva == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedNajemniSmlouva.toString());

    najemniSmlouvaService.deleteNajemniSmlouva(deletedNajemniSmlouva.getId());

    showInfoMessage("Smazána", "Nájemní smlouvou byla smazána");
    init();
  }

  public int getNajemniSmlouvaListSize() {
    if (najemniSmlouvaList == null) {
      return 0;
    }
    return najemniSmlouvaList.size();
  }

  public int getFiltrovanyNajemniSmlouvaListSize() {
    if (filtrovanyNajemniSmlouvaList == null) {
      return getNajemniSmlouvaListSize();
    }
    return filtrovanyNajemniSmlouvaList.size();
  }

  public String getSeznamJmenVsechNajemniku(NajemniSmlouva najemniSmlouva) {
    String seznamJmenVsechNajemniku = "";

    if (najemniSmlouva == null || CollectionUtils.isEmpty(najemniSmlouva.getSeznamNajemniku())) {
      return seznamJmenVsechNajemniku;
    }

    for (Osoba osoba : najemniSmlouva.getSeznamNajemniku()) {
      seznamJmenVsechNajemniku += osoba.getCeleJmeno() + ", ";
    }
    return seznamJmenVsechNajemniku;
  }

  public String vypocitatBarvuNajemniSmlouva(NajemniSmlouva najemniSmlouva) {
    if (najemniSmlouva == null) {
      return null;
    }

    if (najemniSmlouva.getDokumentTyp() == DokumentTyp.NAJEMNI_SMLOUVA_DODATEK) {
      return "bledeZelenaNajemniSmlouva";
    }

    if (najemniSmlouva.getPlatnost() != null && najemniSmlouva.getPlatnost().getKonec() != null) {
      return "zelenaNajemniSmlouva";
    }

    return "cervenaNajemniSmlouva";
  }
}
