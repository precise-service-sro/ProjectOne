package com.precise_service.project_one.web.common.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.web.AbstractBean;

@Service("nemovitostConverter")
public class NemovitostConverter extends AbstractBean implements Converter {

  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    if (StringUtils.isEmpty(value)) {
      return null;
    }
    try {
      return nemovitostService.getNemovitost(value);
    } catch(NumberFormatException e) {
      throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
    }
  }

  public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    if(object  == null) {
      return null;
    }
    return String.valueOf(((Nemovitost) object).getId());
  }
}