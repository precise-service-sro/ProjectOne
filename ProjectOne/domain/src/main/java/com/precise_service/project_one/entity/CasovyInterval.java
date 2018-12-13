package com.precise_service.project_one.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import static com.precise_service.project_one.DateFormatter.formatDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CasovyInterval {
  @JsonProperty("zacatek")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date zacatek;

  @JsonProperty("konec")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Date konec;

  public String getIdentifikace() {
    return "(" + (zacatek != null ? formatDate(zacatek) : "ZACATEK" ) + " - " + (konec != null ? formatDate(konec) : "KONEC" ) + ")";
  }
}
