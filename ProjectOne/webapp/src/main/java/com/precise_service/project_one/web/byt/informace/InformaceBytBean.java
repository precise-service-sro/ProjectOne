package com.precise_service.project_one.web.byt.informace;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.informace.BytEntity;
import com.precise_service.project_one.service.byt.informace.IBytService;
import com.precise_service.project_one.web.byt.informace.component.EditorTextuBean;

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

  private String nazev;
  private String adresa;

  @PostConstruct
  public void init() {
    BytEntity bytEntity = bytService.getBytEntityAll().get(0);

    nazev = bytEntity.getNazev();
    adresa = bytEntity.getAdresa();

    // editovatelne poznamky
    editorTextuBean.setText(bytEntity.getPoznamky());
  }
}
