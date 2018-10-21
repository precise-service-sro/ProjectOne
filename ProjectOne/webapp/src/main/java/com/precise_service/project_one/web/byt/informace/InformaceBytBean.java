package com.precise_service.project_one.web.byt.informace;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.informace.BytEntity;
import com.precise_service.project_one.service.byt.informace.IBytService;
import com.precise_service.project_one.web.common.component.EditorTextuBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class InformaceBytBean implements Serializable {

  @Autowired
  private EditorTextuBean editorTextuBean;

  @Autowired
  private IBytService bytService;

  private BytEntity bytEntity;
  private String nazev;
  private String adresa;

  @PostConstruct
  public void init() {
    // zatim vzdy vytahuji poznamky pouze k prvnimu bytu v DB
    bytEntity = bytService.getBytEntityAll().get(0);

    nazev = bytEntity.getNazev();
    adresa = bytEntity.getAdresa();

    // editovatelne poznamky
    editorTextuBean.setText(bytEntity.getPoznamky());
  }

  public void ulozit(){
    log.warn("ulozit()");
    String newText = editorTextuBean.getText();
    bytEntity.setPoznamky(newText);
    bytService.postBytEntity(bytEntity);

    FacesMessage msg = new FacesMessage("Úprava uložena", newText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
