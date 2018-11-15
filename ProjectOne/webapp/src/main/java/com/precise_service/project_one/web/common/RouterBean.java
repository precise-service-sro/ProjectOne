package com.precise_service.project_one.web.common;

import java.io.IOException;

import javax.inject.Named;

import org.omnifaces.util.Faces;

import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.web.URL_CONST.FAKTURA_DETAIL_URL;
import static com.precise_service.project_one.web.URL_CONST.FAKTURA_PREHLED_URL;
import static com.precise_service.project_one.web.URL_CONST.NEMOVITOST_DETAIL_URL;
import static com.precise_service.project_one.web.URL_CONST.NEMOVITOST_PREHLED_URL;
import static com.precise_service.project_one.web.URL_CONST.OSOBA_DETAIL_URL;
import static com.precise_service.project_one.web.URL_CONST.OSOBA_PREHLED_URL;
import static com.precise_service.project_one.web.URL_CONST.PREDAVACI_PROTOKOL_DETAIL_URL;
import static com.precise_service.project_one.web.URL_CONST.PREDAVACI_PROTOKOL_PREHLED_URL;

@Slf4j
@Data
@Named
public class RouterBean extends AbstractBean {

  public void goToFakturaPrehledBean() throws IOException {
    log.trace("goToFakturaPrehledBean()");
    Faces.getFlash().setRedirect(true);
    Faces.redirect(FAKTURA_PREHLED_URL);
  }

  public void goToFakturaDetailBean(Faktura faktura) throws IOException {
    log.trace("goToFakturaDetailBean()");
    fakturaDetailBean.setFaktura(faktura);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(FAKTURA_DETAIL_URL);
  }

  public void goToNemovitostPrehledBean() throws IOException {
    log.trace("goToNemovitostPrehledBean()");
    Faces.getFlash().setRedirect(true);
    Faces.redirect(NEMOVITOST_PREHLED_URL);
  }

  public void goToNemovitostDetailBean(Nemovitost nemovitost) throws IOException {
    log.trace("goToNemovitostDetailBean()");
    nemovitostDetailBean.setNemovitost(nemovitost);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(NEMOVITOST_DETAIL_URL);
  }

  public void goToOsobaPrehledBean() throws IOException {
    log.trace("goToOsobaPrehledBean()");
    Faces.getFlash().setRedirect(true);
    Faces.redirect(OSOBA_PREHLED_URL);
  }

  public void goToOsobaDetailBean(Osoba osoba) throws IOException {
    log.trace("goToOsobaDetailBean()");
    osobaDetailBean.setOsoba(osoba);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(OSOBA_DETAIL_URL);
  }

  public void goToPredavaciProtokolPrehledBean() throws IOException {
    log.trace("goToPredavaciProtokolPrehledBean()");
    Faces.getFlash().setRedirect(true);
    Faces.redirect(PREDAVACI_PROTOKOL_PREHLED_URL);
  }

  public void goToPredavaciProtokolDetailBean(PredavaciProtokol predavaciProtokol) throws IOException {
    log.trace("goToPredavaciProtokolDetailBean()");
    predavaciProtokolDetailBean.setPredavaciProtokol(predavaciProtokol);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(PREDAVACI_PROTOKOL_DETAIL_URL);
  }
}
