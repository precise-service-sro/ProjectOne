package com.precise_service.project_one.web.common.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.service.vyuctovani.PolozkaTypService;

@Service("polozkaTypConverter")
public class PolozkaTypConverter implements Converter {

  @Autowired
  private PolozkaTypService polozkaTypService;

  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    if (StringUtils.isEmpty(value)) {
      return null;
    }
    try {
      return polozkaTypService.getPolozkaTyp(value);
    } catch(NumberFormatException e) {
      throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
    }
  }

  public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    if(object == null) {
      return null;
    }
    return String.valueOf(((PolozkaTyp) object).getId());
  }
}