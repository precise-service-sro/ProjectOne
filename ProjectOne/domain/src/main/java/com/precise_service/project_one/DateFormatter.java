package com.precise_service.project_one;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateFormatter {

  private static final String DATE_FORMAT = "dd-MM-yyyy";
  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

  public static Date parseDate(String dateInString) {
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

  public static String formatDate(Date date) {
    if (date == null) {
      log.warn("Cannot format: " + date + " value into valid date");
      return "";
    }
    return simpleDateFormat.format(date);
  }
}
