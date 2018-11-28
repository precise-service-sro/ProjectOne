package com.precise_service.project_one.web.pronajem;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.springframework.util.CollectionUtils;

import com.precise_service.project_one.entity.osoba.Osoba;
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

  public void init() {
    najemniSmlouvaList = najemniSmlouvaService.getNajemniSmlouvaAll();
    filtrovanyNajemniSmlouvaList = null;
  }

  public void pridatNajemniSmlouva() {
    log.trace("pridatNajemniSmlouva()");

    NajemniSmlouva najemniSmlouva = new NajemniSmlouva();
    najemniSmlouva.setDatumPodpisu(new Date());
    najemniSmlouva.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());

    NajemniSmlouva saved = najemniSmlouvaService.postNajemniSmlouva(najemniSmlouva);
    showInfoMessage("Přidána nová nájemní smlouva", saved.getId());

    init();
  }

  public void deleteRow(NajemniSmlouva deletedNajemniSmlouva) {
    log.trace("deleteRow()");

    if (deletedNajemniSmlouva == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedNajemniSmlouva.toString());

    najemniSmlouvaService.deleteNajemniSmlouva(deletedNajemniSmlouva.getId());

    showInfoMessage("Nájemní smlouvou smazána", deletedNajemniSmlouva.getId());
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
}
