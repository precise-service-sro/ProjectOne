package com.precise_service.project_one.web.login;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
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
import static com.precise_service.project_one.web.URL_CONST.VYUCTOVANI_PREHLED_URL;

@Named
@Data
@Slf4j
public class LoginBean extends AbstractBean {

  public static final String SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL = "prihlasenyUzivatel";

  private String prihlasovaciJmeno;
  private String heslo;

  private Osoba getOsoba(String prihlasovaciJmeno, String heslo) {
    if (StringUtils.isBlank( prihlasovaciJmeno) || StringUtils.isBlank(heslo)) {
      return null;
    }
    return osobaService.getOsobaByPrihlasovaciJmenoAndHeslo(prihlasovaciJmeno, heslo);
  }

  public void login() throws IOException {
    Faces.getFlash().setRedirect(true);

    Osoba prihlasenyUzivatel = getOsoba(prihlasovaciJmeno, heslo);
    if (prihlasenyUzivatel == null) {
      showErrorMessage("Špatné přihlašovací údaje!","Špatné přihlašovací jméno anebo heslo!");
      return;
    }

    if (!prihlasenyUzivatel.getMuzeSePrihlasit()) {
      showErrorMessage("Špatné přihlašovací údaje!","Uživatelský účet nemá povolené přihlašování!");
      return;
    }

    HttpSession httpSession = getHttpSession();
    httpSession.setAttribute(SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL, prihlasenyUzivatel);
    // po prihlaseni me to meruje na stranku se kterou pracuji / upravuji
    Faces.redirect(VYUCTOVANI_PREHLED_URL);
    // TODO: vratit zpet na prehled nemovitosti
    //Faces.redirect(NEMOVITOST_PREHLED_URL);
  }

  public void logout() throws IOException {
    HttpSession httpSession = getHttpSession();
    httpSession.invalidate();
    Faces.getFlash().setRedirect(true);
    Faces.redirect(INDEX_URL);
  }

  public Osoba getPrihlasenyUzivatel() {
    HttpSession session = getHttpSession();
    return (Osoba) session.getAttribute(LoginBean.SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL);
  }

  public void setPrihlasenyUzivatel(Osoba prihlasenyUzivatel) {
    HttpSession session = getHttpSession();
    if (session != null) {
      session.setAttribute(LoginBean.SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL, prihlasenyUzivatel);
    }
  }

  private HttpSession getHttpSession() {
    FacesContext currentInstance = FacesContext.getCurrentInstance();
    if (currentInstance == null) {
      return null;
    }
    ExternalContext externalContext = currentInstance.getExternalContext();
    if (externalContext == null) {
      return null;
    }
    return (HttpSession) externalContext.getSession(false);
  }
}