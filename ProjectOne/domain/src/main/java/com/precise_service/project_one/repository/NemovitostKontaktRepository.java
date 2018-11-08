package com.precise_service.project_one.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.nemovitost.NemovitostKontakt;

public interface NemovitostKontaktRepository extends MongoRepository<NemovitostKontakt, String> {

  @Query("{ $and: [ { 'nemovitost.id' : ?0 }, { 'uzivatel.id' : ?1 } ] }")
  List<NemovitostKontakt> getNemovitostKontaktAll(String idNemovitost, String idPrihlasenyUzivatel);
}