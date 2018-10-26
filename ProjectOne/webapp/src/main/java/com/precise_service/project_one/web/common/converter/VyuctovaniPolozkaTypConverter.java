package com.precise_service.project_one.web.common.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTyp;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypService;

@Service("vyuctovaniPolozkaTypConverter")
public class VyuctovaniPolozkaTypConverter implements Converter {

  @Autowired
  private VyuctovaniPolozkaTypService vyuctovaniPolozkaTypService;

  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    if (StringUtils.isEmpty(value)) {
      return null;
    }
    try {
      return vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTyp(value);
    } catch(NumberFormatException e) {
      throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
    }
  }

  public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    if(object == null) {
      return null;
    }
    return String.valueOf(((VyuctovaniPolozkaTyp) object).getId());
  }
}