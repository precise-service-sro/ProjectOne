package com.precise_service.project_one.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.faktura.FakturaPolozka;

public interface FakturaPolozkaRepository extends MongoRepository<FakturaPolozka, String> {

  @Query("{ 'faktura.id' : ?0 }")
  List<FakturaPolozka> getFakturaPolozkaAll(String idFaktura);

}