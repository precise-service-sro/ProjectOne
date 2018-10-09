package com.precise_service.project_one.service;

public interface IInputDataProcessorService {

  void cleanDatabase();

  void initDatabaseAfterApplicationStart();

  String loadFromDatabase(String firstName);

}
