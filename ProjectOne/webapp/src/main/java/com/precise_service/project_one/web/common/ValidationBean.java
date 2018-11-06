package com.precise_service.project_one.web.common;

import java.io.Serializable;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.web.nemovitost.NemovitostDetailBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class ValidationBean implements Serializable {

  @Autowired
  private NemovitostDetailBean nemovitostDetailBean;

  public boolean isBlank(String string) {
    log.trace("isBlank()");
    return StringUtils.isBlank(string);
  }

  public boolean isNotBlank(String string) {
    log.trace("isNotBlank()");
    return StringUtils.isNotBlank(string);
  }
}
