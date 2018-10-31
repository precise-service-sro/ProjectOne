package com.precise_service.project_one.web.common.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.precise_service.project_one.entity.osoba.Najemnik;
import com.precise_service.project_one.service.osoba.INajemnikService;

@Service("najemnikConverter")
public class NajemnikConverter implements Converter {

  @Autowired
  private INajemnikService najemnikService;

  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    if (StringUtils.isEmpty(value)) {
      return null;
    }
    try {
      return najemnikService.getNajemnik(value);
    } catch(NumberFormatException e) {
      throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
    }
  }

  public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    if(object == null) {
      return null;
    }
    return String.valueOf(((Najemnik) object).getId());
  }
}