package com.precise_service.project_one.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.precise_service.project_one.service.faktura.IFakturaPolozkaService;
import com.precise_service.project_one.service.faktura.IFakturaService;
import com.precise_service.project_one.service.nemovitost.INemovitostKontaktService;
import com.precise_service.project_one.service.nemovitost.INemovitostService;
import com.precise_service.project_one.service.osoba.IOsobaService;
import com.precise_service.project_one.service.pronajem.INajemniSmlouvaService;
import com.precise_service.project_one.service.pronajem.IPlatbaNajemnehoService;
import com.precise_service.project_one.service.pronajem.IPredavaciProtokolPolozkaService;
import com.precise_service.project_one.service.pronajem.IPredavaciProtokolService;
import com.precise_service.project_one.service.IPolozkaTypService;
import com.precise_service.project_one.service.pronajem.IVyuctovaniPolozkaService;
import com.precise_service.project_one.service.pronajem.IVyuctovaniService;
import com.precise_service.project_one.web.common.RouterBean;
import com.precise_service.project_one.web.common.formatter.DateFormatterBean;
import com.precise_service.project_one.web.faktura.FakturaDetailBean;
import com.precise_service.project_one.web.login.LoginBean;
import com.precise_service.project_one.web.nemovitost.NemovitostDetailBean;
import com.precise_service.project_one.web.osoba.OsobaDetailBean;
import com.precise_service.project_one.web.pronajem.PredavaciProtokolDetailBean;
import com.precise_service.project_one.web.pronajem.VyuctovaniDetailBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public abstract class AbstractBean implements Serializable {

  @Autowired protected IFakturaService fakturaService;
  @Autowired protected IFakturaPolozkaService fakturaPolozkaService;

  @Autowired protected INajemniSmlouvaService najemniSmlouvaService;

  @Autowired protected INemovitostService nemovitostService;
  @Autowired protected INemovitostKontaktService nemovitostKontaktService;

  @Autowired protected IOsobaService osobaService;

  @Autowired protected IPolozkaTypService polozkaTypService;

  @Autowired protected IPredavaciProtokolService predavaciProtokolService;
  @Autowired protected IPredavaciProtokolPolozkaService predavaciProtokolPolozkaService;

  @Autowired protected IPlatbaNajemnehoService platbaNajemnehoService;
  
  @Autowired protected IVyuctovaniService vyuctovaniService;
  @Autowired protected IVyuctovaniPolozkaService vyuctovaniPolozkaService;

  @Autowired protected FakturaDetailBean fakturaDetailBean;
  @Autowired protected LoginBean loginBean;
  @Autowired protected NemovitostDetailBean nemovitostDetailBean;
  @Autowired protected OsobaDetailBean osobaDetailBean;
  @Autowired protected PredavaciProtokolDetailBean predavaciProtokolDetailBean;
  @Autowired protected RouterBean routerBean;
  @Autowired protected VyuctovaniDetailBean vyuctovaniDetailBean;

  @Autowired protected GridFsTemplate gridFsTemplate;

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

  private void showInfo(FacesMessage.Severity severity, String messageSummary, String messageText) {
    FacesMessage msg = new FacesMessage(severity, messageSummary, messageText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
