package com.precise_service.project_one.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
import com.precise_service.project_one.web.common.RouterBean;
import com.precise_service.project_one.web.common.formatter.DateFormatterBean;
import com.precise_service.project_one.web.faktura.FakturaDetailBean;
import com.precise_service.project_one.web.login.AuthorizationFilterBean;
import com.precise_service.project_one.web.login.LoginBean;
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

  @Autowired protected FakturaDetailBean fakturaDetailBean;
  @Autowired protected LoginBean loginBean;
  @Autowired protected NemovitostDetailBean nemovitostDetailBean;
  @Autowired protected OsobaDetailBean osobaDetailBean;
  @Autowired protected PredavaciProtokolDetailBean predavaciProtokolDetailBean;
  @Autowired protected RouterBean routerBean;
  @Autowired protected VyuctovaniDetailBean vyuctovaniDetailBean;

  @Autowired protected DateFormatterBean dateFormatterBean;

  protected void showInfoMessage(String messageSummary, String messageText) {
    showInfo(FacesMessage.SEVERITY_INFO, messageSummary, messageText);
  }

  protected void showWarningMessage(String messageSummary, String messageText) {
    showInfo(FacesMessage.SEVERITY_WARN, messageSummary, messageText);
  }

  protected void showErrorMessage(String messageSummary, String messageText) {
    showInfo(FacesMessage.SEVERITY_ERROR, messageSummary, messageText);
  }

  protected void showInfo(FacesMessage.Severity severity, String messageSummary, String messageText) {
    FacesMessage msg = new FacesMessage(severity, messageSummary, messageText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
