package com.precise_service.project_one;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.precise_service.IPokusService;
import com.precise_service.PokusService;

@Configuration
public class ServiceConfig {

  @Bean
  public IPokusService pokusService() {
    return new PokusService();
  }
}
