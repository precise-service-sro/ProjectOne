package com.precise_service.project_one.web.common;

import java.util.Date;

import javax.inject.Named;

import com.precise_service.project_one.DateFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
public class DateFormatterBean {

  public Date parseDate(String dateInString) {
    return DateFormatter.parseDate(dateInString);
  }

  public String formatDate(Date date) {
    return DateFormatter.formatDate(date);
  }
}
