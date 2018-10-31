package com.precise_service.project_one.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.precise_service.project_one.entity.osoba.Uzivatel;

import lombok.Data;

@Data
public abstract class BaseEntity {
  @Id
  public String id;

  @DBRef
  @JsonProperty("uzivatel")
  private Uzivatel uzivatel;
}
