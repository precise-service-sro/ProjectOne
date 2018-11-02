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

import lombok.Data;

import static com.precise_service.project_one.web.URL_CONST.INDEX_URL;
import static com.precise_service.project_one.web.URL_CONST.NEMOVITOST_PREHLED_URL;

@Named
@Data
public class LoginBean implements Serializable {

  public static final String SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL = "prihlasenyUzivatel";

  @Autowired
  private IOsobaService osobaService;

  private String prihlasovaciJmeno;
  private String heslo;

  private Osoba prihlasenyUzivatel;

  private Osoba getOsoba(String prihlasovaciJmeno, String heslo) {
    if (StringUtils.isBlank( prihlasovaciJmeno) || StringUtils.isBlank(heslo)) {
      return null;
    }
    return osobaService.getOsobaByUsernameAndPassword(prihlasovaciJmeno, heslo);
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
    session.setAttribute(SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL, prihlasovaciJmeno);
    Faces.redirect(NEMOVITOST_PREHLED_URL);
  }

  public void logout() throws IOException {
    HttpSession session = Util.getSession();
    session.invalidate();
    prihlasenyUzivatel = null;
    Faces.getFlash().setRedirect(true);
    Faces.redirect(INDEX_URL);
  }
}