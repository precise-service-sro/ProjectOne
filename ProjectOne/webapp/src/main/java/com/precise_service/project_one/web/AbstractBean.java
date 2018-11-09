package com.precise_service.project_one.web;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.service.faktura.IFakturaPolozkaService;
import com.precise_service.project_one.service.faktura.IFakturaService;
import com.precise_service.project_one.service.nemovitost.INemovitostKontaktService;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.service.osoba.IOsobaService;
import com.precise_service.project_one.service.predavaci_protokol.IPredavaciProtokolPolozkaService;
import com.precise_service.project_one.service.predavaci_protokol.IPredavaciProtokolService;
import com.precise_service.project_one.service.vyuctovani.IPolozkaTypService;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniPolozkaService;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniService;
import com.precise_service.project_one.web.common.component.EditorTextuBean;
import com.precise_service.project_one.web.faktura.FakturaDetailBean;
import com.precise_service.project_one.web.nemovitost.NemovitostDetailBean;
import com.precise_service.project_one.web.osoba.OsobaDetailBean;
import com.precise_service.project_one.web.predavaci_protokol.PredavaciProtokolDetailBean;
import com.precise_service.project_one.web.vyuctovani.VyuctovaniDetailBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public abstract class AbstractBean implements Serializable {

  @Autowired protected IFakturaService fakturaService;
  @Autowired protected IFakturaPolozkaService fakturaPolozkaService;

  @Autowired protected INemovitostService nemovitostService;
  @Autowired protected INemovitostKontaktService nemovitostKontaktService;

  @Autowired protected IOsobaService osobaService;

  @Autowired protected IPolozkaTypService polozkaTypService;

  @Autowired protected IPredavaciProtokolService predavaciProtokolService;
  @Autowired protected IPredavaciProtokolPolozkaService predavaciProtokolPolozkaService;
  
  @Autowired protected IVyuctovaniService vyuctovaniService;
  @Autowired protected IVyuctovaniPolozkaService vyuctovaniPolozkaService;

  @Autowired protected EditorTextuBean editorTextuBean;
  @Autowired protected FakturaDetailBean fakturaDetailBean;
  @Autowired protected NemovitostDetailBean nemovitostDetailBean;
  @Autowired protected PredavaciProtokolDetailBean predavaciProtokolDetailBean;
  @Autowired protected OsobaDetailBean osobaDetailBean;
  @Autowired protected VyuctovaniDetailBean vyuctovaniDetailBean;
}
