package com.precise_service.project_one.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.PolozkaTyp;

public interface PolozkaTypRepository extends MongoRepository<PolozkaTyp, String> {

  @Query("{ 'nemovitost.id' : ?0 }")
  List<PolozkaTyp> getPolozkaTypListByIdNemovitost(String idNemovitost);
}