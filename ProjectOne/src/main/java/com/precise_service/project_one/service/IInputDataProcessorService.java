package com.precise_service.project_one.service;

import java.util.List;

import com.precise_service.project_one.domain.Customer;
import com.precise_service.project_one.domain.vyuctovani.PolozkaVyuctovani;

public interface IInputDataProcessorService {

  /**
   * Complete database cleanup before new initialization
   * */
  void cleanDatabase();

  void initDatabaseAfterApplicationStart();

  String loadFromDatabase(String firstName);

  List<Customer> getAll();

  List<PolozkaVyuctovani> getSeznamPolozek();

}
