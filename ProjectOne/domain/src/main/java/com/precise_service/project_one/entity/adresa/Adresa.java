package com.precise_service.project_one.entity.adresa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import static org.apache.commons.lang3.StringUtils.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Adresa {

  @JsonProperty("ulice")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String ulice;

  @JsonProperty("psc")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String psc;

  @JsonProperty("mesto")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String mesto;

  @JsonProperty("stat")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Stat stat;

  @JsonProperty("cisloPopisne")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String cisloPopisne;

  @JsonProperty("cisloOrientacni")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String cisloOrientacni;

  // toto by melo byt relevantni jenom pro adresu bytu
  @JsonProperty("patro")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String patro;

  @JsonProperty("cisloBytu")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String cisloBytu;

  public String getCelaAdresa() {
    return "" + ulice + " " + cisloPopisne + (isNotBlank(cisloOrientacni) ? "/" + cisloOrientacni : "")
        + (isNotBlank(mesto) ? ", " + mesto : "") + (isNotBlank(psc) ? ", " + psc : "")
        + (isNotBlank(cisloBytu ) ? ", (byt č." + cisloBytu + ")" : "");
  }
}