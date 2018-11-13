package com.precise_service.project_one.web.login;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.osoba.IOsobaService;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.web.URL_CONST.FAKTURA_PREHLED_URL;
import static com.precise_service.project_one.web.URL_CONST.INDEX_URL;
import static com.precise_service.project_one.web.URL_CONST.NEMOVITOST_PREHLED_URL;
import static com.precise_service.project_one.web.URL_CONST.PREDAVACI_PROTOKOL_PREHLED_URL;

@Named
@Data
@Slf4j
public class LoginBean extends AbstractBean {

  public static final String SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL = "prihlasenyUzivatel";

  private String prihlasovaciJmeno;
  private String heslo;

  private Osoba prihlasenyUzivatel;

  private Osoba getOsoba(String prihlasovaciJmeno, String heslo) {
    if (StringUtils.isBlank( prihlasovaciJmeno) || StringUtils.isBlank(heslo)) {
      return null;
    }
    return osobaService.getOsobaByPrihlasovaciJmenoAndHeslo(prihlasovaciJmeno, heslo);
  }

  public void login() throws IOException {
    Faces.getFlash().setRedirect(true);

    prihlasenyUzivatel = getOsoba(prihlasovaciJmeno, heslo);
    if (prihlasenyUzivatel == null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Špatné přihlašovací údaje!","Špatné přihlašovací jméno anebo heslo!"));
      Faces.redirect(INDEX_URL);
      return;
    }

    HttpSession session = Util.getSession();
    session.setAttribute(SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL, prihlasenyUzivatel);
    // po prihlaseni me to meruje na stranku se kterou pracuji / upravuji
    Faces.redirect(PREDAVACI_PROTOKOL_PREHLED_URL);
    // TODO: vratit zpet na prehled nemovitosti
    //Faces.redirect(NEMOVITOST_PREHLED_URL);
  }

  public void logout() throws IOException {
    HttpSession session = Util.getSession();
    session.invalidate();
    prihlasenyUzivatel = null;
    Faces.getFlash().setRedirect(true);
    Faces.redirect(INDEX_URL);
  }

  public Osoba getPrihlasenaOsoba() {
    return Util.getPrihlasenyUzivatel();
  }
}