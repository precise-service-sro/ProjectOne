package com.precise_service.project_one.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozka;

public interface VyuctovaniPolozkaRepository extends MongoRepository<VyuctovaniPolozka, String> {

  @Query("{ 'vyuctovani.id' : ?0 }")
  List<VyuctovaniPolozka> getVyuctovaniPolozkaAll(String idVyuctovani);

}