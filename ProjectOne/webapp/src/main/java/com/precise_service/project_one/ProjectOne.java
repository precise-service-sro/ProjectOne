package com.precise_service.project_one;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.precise_service.project_one.service.IPokusService;
import com.precise_service.project_one.service.impl.PokusService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ProjectOne implements CommandLineRunner {

  public static void main(String[] args) {
    log.info("!!! PROJECT ONE ... STARTUJEME !!!");
    SpringApplication.run(ProjectOne.class, args);
  }

  @Override
  public void run(String... args) {
    log.trace("ProjectOne...starting and loading input data");
    log.trace("ProjectOne...starting and loading input data...finished");
  }
}