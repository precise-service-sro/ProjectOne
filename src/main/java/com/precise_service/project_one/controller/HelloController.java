package com.precise_service.project_one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.service.IInputDataProcessorService;

@RestController
public class HelloController {

  @Autowired
  private IInputDataProcessorService inputDataProcessorService;

  @RequestMapping("/")
  String hello() {
    return "Project One - Welcome page! + " + inputDataProcessorService.loadFromDatabase("Zdeněk");
  }


}
