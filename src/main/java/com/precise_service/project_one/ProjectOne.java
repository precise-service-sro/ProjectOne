package com.precise_service.project_one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.precise_service.project_one.service.IInputDataProcessorService;
import com.precise_service.project_one.service.impl.InputDataProcessorService;

@SpringBootApplication
public class ProjectOne implements CommandLineRunner {

  @Autowired
  private IInputDataProcessorService inputDataProcessorService;

  public static void main(String[] args) {
    SpringApplication.run(ProjectOne.class, args);
  }

  @Override
  public void run(String... args) {
    inputDataProcessorService.initDatabaseAfterApplicationStart();
  }
}