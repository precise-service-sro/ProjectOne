package com.precise_service.project_one.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokolPolozka;

public interface PredavaciProtokolPolozkaRepository extends MongoRepository<PredavaciProtokolPolozka, String> {

  @Query("{ 'predavaciProtokol.id' : ?0 }")
  List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaAll(String idPredavaciProtokol);

}