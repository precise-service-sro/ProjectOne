package com.precise_service.project_one.web.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
public class DateFormatterBean {

  private final String DATE_FORMAT = "dd-MM-yyyy";
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

  public Date parseDate(String dateInString) {
    if (StringUtils.isBlank(dateInString)) {
      log.warn("Cannot parse: " + dateInString + " value into valid date");
      return null;
    }

    try {
      return simpleDateFormat.parse(dateInString);

    } catch (ParseException e) {
      log.error("Cannot parse: " + dateInString + " value into valid date");
      return null;
    }
  }

  public String formatDate(Date date) {
    if (date == null) {
      log.warn("Cannot format: " + date + " value into valid date");
      return "";
    }
    return simpleDateFormat.format(date);
  }
}
