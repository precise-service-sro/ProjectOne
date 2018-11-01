package com.precise_service.project_one.web.osoba;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.osoba.IOsobaService;
import com.precise_service.project_one.web.common.component.EditorTextuBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class OsobaDetailBean implements Serializable {

  @Autowired
  private EditorTextuBean editorTextuBean;

  @Autowired
  private IOsobaService osobaService;

  private Osoba osoba;

  public void init() {
    if (osoba == null) {
      // zatim vzdy vytahuji poznamky pouze k prvnimu osobaovi v DB
      osoba = (Osoba) osobaService.getOsobaAll().get(0);
    }

    // editovatelne poznamky
    editorTextuBean.setText(osoba.getPoznamky());
  }

  public void ulozit(){
    log.warn("ulozit()");
    String newText = editorTextuBean.getText();
    osoba.setPoznamky(newText);
    osobaService.postOsoba(osoba);

    FacesMessage msg = new FacesMessage("Úprava uložena", newText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
