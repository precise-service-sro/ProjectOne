package com.precise_service.project_one.web.common.component;

import javax.inject.Named;

import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;

@Data
@Named
public class EditorTextuBean extends AbstractBean {
  private String text;
}
