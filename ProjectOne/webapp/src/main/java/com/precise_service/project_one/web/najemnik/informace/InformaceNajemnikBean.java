package com.precise_service.project_one.web.najemnik.informace;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.najemnik.informace.NajemnikEntity;
import com.precise_service.project_one.service.najemnik.informace.INajemnikService;
import com.precise_service.project_one.web.common.component.EditorTextuBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class InformaceNajemnikBean implements Serializable {

  @Autowired
  private EditorTextuBean editorTextuBean;

  @Autowired
  private INajemnikService najemnikService;

  private NajemnikEntity najemnikEntity;
  private String jmeno;
  private String prijmeni;

  public void init() {
    // zatim vzdy vytahuji poznamky pouze k prvnimu najemnikovi v DB
    najemnikEntity = najemnikService.getNajemnikEntityAll().get(0);

    jmeno = najemnikEntity.getJmeno();
    prijmeni = najemnikEntity.getPrijmeni();

    // editovatelne poznamky
    editorTextuBean.setText(najemnikEntity.getPoznamky());
  }

  public void ulozit(){
    log.warn("ulozit()");
    String newText = editorTextuBean.getText();
    najemnikEntity.setPoznamky(newText);
    najemnikService.postNajemnikEntity(najemnikEntity);

    FacesMessage msg = new FacesMessage("Úprava uložena", newText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
