package com.precise_service.project_one.service;

public interface IInputDataProcessorService {

  void initDatabaseAfterApplicationStart();

  String loadFromDatabase(String firstName);
}
