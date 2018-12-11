package com.precise_service.project_one.entity.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FakturaFilter extends DataFilter{

  @JsonProperty("cisloUctu")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String cisloUctu;

  @JsonProperty("datumSplatnosti")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String datumSplatnosti;

  @JsonProperty("variabilniSymbol")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String variabilniSymbol;
}
