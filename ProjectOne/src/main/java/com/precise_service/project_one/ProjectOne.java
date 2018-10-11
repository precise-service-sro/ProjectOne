package com.precise_service.project_one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.precise_service.project_one.service.IInputDataProcessorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ProjectOne implements CommandLineRunner {

  @Autowired
  private IInputDataProcessorService inputDataProcessorService;

  public static void main(String[] args) {
    log.info("!!! PROJECT ONE ... STARTUJEME !!!");
    SpringApplication.run(ProjectOne.class, args);
  }

  @Override
  public void run(String... args) {
    log.trace("ProjectOne...starting and loading input data");
    inputDataProcessorService.initDatabaseAfterApplicationStart();
    log.trace("ProjectOne...starting and loading input data...finished");
  }
}