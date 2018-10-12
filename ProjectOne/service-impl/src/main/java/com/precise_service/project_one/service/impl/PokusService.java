package com.precise_service.project_one.service.impl;

import org.springframework.stereotype.Service;

import com.precise_service.project_one.service.IPokusService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PokusService implements IPokusService {

  @Override
  public String hello() {
    return "heelo from PokusService.service-impl2repositoryDomainCLEAN";
  }
}
