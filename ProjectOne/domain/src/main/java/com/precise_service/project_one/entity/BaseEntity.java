package com.precise_service.project_one.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class BaseEntity {
  @Id
  public String id;
}
