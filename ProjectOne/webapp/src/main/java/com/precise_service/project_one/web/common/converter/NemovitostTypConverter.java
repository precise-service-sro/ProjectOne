package com.precise_service.project_one.web.common.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.precise_service.project_one.entity.nemovitost.NemovitostTyp;
import com.precise_service.project_one.web.AbstractBean;

@Service("nemovitostTypConverter")
public class NemovitostTypConverter extends AbstractBean implements Converter {

  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    if (StringUtils.isEmpty(value)) {
      return null;
    }
    return NemovitostTyp.valueOf(value);
  }

  public String getAsString(FacesContext fc, UIComponent uic, Object object) {
    if (object == null) {
      return null;
    }
    return String.valueOf(((NemovitostTyp) object).name());
  }
}