package com.precise_service.project_one;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.service.IInputDataProcessorService;

@Named
public class HelloWorld {

  @Autowired
  private IInputDataProcessorService inputDataProcessorService;

  private String firstName = "John";
  private String lastName = "Doe";

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String showGreeting() {
    return "Hello " + firstName + " " + lastName + "!" + "  " + inputDataProcessorService.loadFromDatabase("Zdeněk");
  }
}
