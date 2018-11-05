package com.precise_service.project_one.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;

public interface PredavaciProtokolRepository extends MongoRepository<PredavaciProtokol, String> {

  @Query("{ 'uzivatel.id' : ?0 }")
  List<PredavaciProtokol> getPredavaciProtokolAll(String idPrihlasenyUzivatel);
}