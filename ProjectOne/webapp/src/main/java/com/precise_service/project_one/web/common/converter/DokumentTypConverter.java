package com.precise_service.project_one.web.common.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.precise_service.project_one.entity.pronajem.DokumentTyp;
import com.precise_service.project_one.web.AbstractBean;

@Service("dokumentTypConverter")
public class DokumentTypConverter extends AbstractBean implements Converter {

  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    if (StringUtils.isEmpty(value)) {
      return null;
    }
    return DokumentTyp.valueOf(value);
  }

  public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    if (object == null) {
      return null;
    }
    return String.valueOf(((DokumentTyp) object).name());
  }
}