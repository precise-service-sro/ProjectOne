package com.precise_service.project_one.web.nemovitost;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.web.common.component.EditorTextuBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NemovitostDetailBean implements Serializable {

  @Autowired
  private EditorTextuBean editorTextuBean;

  @Autowired
  private INemovitostService nemovitostService;

  private Nemovitost nemovitost;

  public void init() {
    // pokud nemam vybranou zadnou nemovitost, tak vytahuji prvni nemovitost z DB
    if (nemovitost == null) {
      nemovitost = nemovitostService.getNemovitostAll().get(0);
    }

    // editovatelne poznamky
    editorTextuBean.setText(nemovitost.getPoznamky());
  }

  public void ulozit(){
    log.warn("ulozit()");
    String newText = editorTextuBean.getText();
    nemovitost.setPoznamky(newText);
    nemovitostService.postNemovitost(nemovitost);

    FacesMessage msg = new FacesMessage("Úprava uložena", newText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
