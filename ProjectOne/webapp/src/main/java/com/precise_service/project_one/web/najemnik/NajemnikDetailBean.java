package com.precise_service.project_one.web.najemnik;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.Najemnik;
import com.precise_service.project_one.service.najemnik.INajemnikService;
import com.precise_service.project_one.web.common.component.EditorTextuBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NajemnikDetailBean implements Serializable {

  @Autowired
  private EditorTextuBean editorTextuBean;

  @Autowired
  private INajemnikService najemnikService;

  private Najemnik najemnik;
  private String jmeno;
  private String prijmeni;

  public void init() {
    // zatim vzdy vytahuji poznamky pouze k prvnimu najemnikovi v DB
    najemnik = najemnikService.getNajemnikAll().get(0);

    jmeno = najemnik.getJmeno();
    prijmeni = najemnik.getPrijmeni();

    // editovatelne poznamky
    editorTextuBean.setText(najemnik.getPoznamky());
  }

  public void ulozit(){
    log.warn("ulozit()");
    String newText = editorTextuBean.getText();
    najemnik.setPoznamky(newText);
    najemnikService.postNajemnik(najemnik);

    FacesMessage msg = new FacesMessage("Úprava uložena", newText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
