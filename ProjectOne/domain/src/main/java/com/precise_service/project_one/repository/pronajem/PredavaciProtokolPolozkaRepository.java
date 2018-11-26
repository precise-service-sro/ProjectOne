package com.precise_service.project_one.repository.pronajem;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.pronajem.PredavaciProtokolPolozka;

public interface PredavaciProtokolPolozkaRepository extends MongoRepository<PredavaciProtokolPolozka, String> {

  @Query("{ 'predavaciProtokol.id' : ?0 }")
  List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaAll(String idPredavaciProtokol);

  @Query("{ $and: [ { 'predavaciProtokol.id' : ?0 }, { 'polozkaTyp.id' : ?1 } ] }")
  List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaList(String idPredavaciProtokol, String idPolozkaTyp);
}