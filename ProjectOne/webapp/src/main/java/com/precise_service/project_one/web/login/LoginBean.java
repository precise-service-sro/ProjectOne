package com.precise_service.project_one.web.login;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.osoba.IOsobaService;

@Named
public class LoginBean implements Serializable {

  @Autowired
  private IOsobaService osobaService;

  public boolean login(String user, String password) {


    Osoba osoba = osobaService.getOsobaByUsernameAndPassword(user, password);

    if (osoba == null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
          "LoginDAO!",
          "Wrong password message test!"));
      return false;
    }

    System.out.println(osoba.getCeleJmeno());
    return true;
  }

  private static final long serialVersionUID = 1L;
  private String password;
  private String message, uname;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  public String loginProject() {
    boolean result = login(uname, password);
    if (result) {

      // get Http Session and store username
      HttpSession session = Util.getSession();
      session.setAttribute("username", uname);
      return "index";
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
          "Invalid Login!",
          "Please Try Again!"));

      // invalidate session, and redirect to other pages
      //message = "Invalid Login. Please Try Again!";
      return "login";
    }
  }

  public String logout() {
    HttpSession session = Util.getSession();
    session.invalidate();
    return "login";
  }
}