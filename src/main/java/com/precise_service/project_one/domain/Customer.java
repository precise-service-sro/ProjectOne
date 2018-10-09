package com.precise_service.project_one.domain;

import org.springframework.data.annotation.Id;

public class Customer {

  @Id
  public String id;

  public String firstName;
  public String lastName;

  public Customer() {}

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format(
        "hello.Customer[id=%s, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

}
