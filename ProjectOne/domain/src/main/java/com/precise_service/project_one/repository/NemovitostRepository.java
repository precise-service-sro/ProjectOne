package com.precise_service.project_one.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;

public interface NemovitostRepository extends MongoRepository<Nemovitost, String> {

  @Query("{ 'uzivatel.id' : ?0 }")
  List<Nemovitost> getNemovitostAll(String idPrihlasenyUzivatel);
}