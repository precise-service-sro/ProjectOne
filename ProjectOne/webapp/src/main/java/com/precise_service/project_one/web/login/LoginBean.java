package com.precise_service.project_one.web.login;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

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

  @Autowired
  private IOsobaService osobaService;

  private Osoba prihlasenyUzivatel;

  public boolean login(String user, String password) {
    prihlasenyUzivatel = osobaService.getOsobaByUsernameAndPassword(user, password);
    if (prihlasenyUzivatel == null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
          "LoginDAO!",
          "Wrong password message test!"));
      return false;
    }
    return true;
  }

  private String password;
  private String message;
  private String uname;

  public void loginProject() throws IOException {
    Faces.getFlash().setRedirect(true);

    if (login(uname, password)) {
      HttpSession session = Util.getSession();
      session.setAttribute("username", uname);
      Faces.redirect(NEMOVITOST_PREHLED_URL);
      return;
    }

    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
          "Invalid Login!",
          "Please Try Again!"));

    Faces.redirect(INDEX_URL);
  }

  public void logout() throws IOException {
    HttpSession session = Util.getSession();
    session.invalidate();
    prihlasenyUzivatel = null;
    Faces.getFlash().setRedirect(true);
    Faces.redirect(INDEX_URL);
  }
}