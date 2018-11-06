package com.precise_service.project_one.web.common;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.web.nemovitost.NemovitostDetailBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.web.URL_CONST.FAKTURA_DETAIL_URL;
import static com.precise_service.project_one.web.URL_CONST.NEMOVITOST_DETAIL_URL;

@Slf4j
@Data
@Named
public class RouterBean implements Serializable {

  @Autowired
  private NemovitostDetailBean nemovitostDetailBean;

  public void goToNemovitostDetailBean(Nemovitost nemovitost) throws IOException {
    log.trace("goToNemovitostDetailBean()");
    nemovitostDetailBean.setNemovitost(nemovitost);
    Faces.getFlash().setRedirect(true);
    Faces.redirect(NEMOVITOST_DETAIL_URL);
  }
}