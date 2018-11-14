package com.precise_service.project_one.web.login;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.precise_service.project_one.entity.osoba.Osoba;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {

  public static HttpSession getSession() {
    return (HttpSession)
        FacesContext.
            getCurrentInstance().
            getExternalContext().
            getSession(false);
  }

  public static HttpServletRequest getRequest() {
    return (HttpServletRequest) FacesContext.
        getCurrentInstance().
        getExternalContext().getRequest();
  }

  public static Osoba getPrihlasenyUzivatel() {
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    return (Osoba) session.getAttribute(LoginBean.SESSION_ATTRIBUTE_PRIHLASENY_UZIVATEL);
  }
}