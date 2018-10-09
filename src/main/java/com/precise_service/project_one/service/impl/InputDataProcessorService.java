package com.precise_service.project_one.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.domain.Customer;
import com.precise_service.project_one.repository.CustomerRepository;
import com.precise_service.project_one.service.IInputDataProcessorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InputDataProcessorService implements IInputDataProcessorService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public void initDatabaseAfterApplicationStart() {
    customerRepository.deleteAll();

    // save a couple of customers
    customerRepository.save(new Customer("Alice", "Smith"));
    customerRepository.save(new Customer("Zdenek", "Smith"));
    customerRepository.save(new Customer("Bob", "Smith"));

    // fetch all customers
    System.out.println("Customers found with findAll():");
    System.out.println("-------------------------------");
    for (Customer customer : customerRepository.findAll()) {
      System.out.println(customer);
    }
    System.out.println();

    // fetch an individual customer
    System.out.println("hello.Customer found with findByFirstName('Alice'):");
    System.out.println("--------------------------------");
    System.out.println(customerRepository.findByFirstName("Alice"));

    System.out.println("Customers found with findByLastName('Smith'):");
    System.out.println("--------------------------------");
    for (Customer customer : customerRepository.findByLastName("Smith")) {
      System.out.println(customer);
    }
  }

  @Override
  public String loadFromDatabase(String firstName) {
    Customer customer = customerRepository.findByFirstName(firstName);
    log.debug("AAAAAA");
    return customer.firstName + " " + customer.lastName;
  }
}
