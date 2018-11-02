package com.precise_service.project_one.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.osoba.Osoba;

public interface OsobaRepository extends MongoRepository<Osoba, String> {

  @Query("{ 'username' : ?0, 'password' : ?1 }")
  Osoba getOsobaByUsernameAndPassword(String username, String password);
}