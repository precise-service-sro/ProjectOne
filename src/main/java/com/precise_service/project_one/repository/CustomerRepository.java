package com.precise_service.project_one.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

  Customer findByFirstName(String firstName);
  List<Customer> findByLastName(String lastName);
}