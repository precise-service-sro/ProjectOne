package com.precise_service.project_one.commons;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
  public static String format(LocalDate date, String pattern) {
    if (date == null) {
      return null;
    }
    return date.format(DateTimeFormatter.ofPattern(pattern));
  }
}
