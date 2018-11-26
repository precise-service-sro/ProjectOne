package com.precise_service.project_one.repository.pronajem;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.pronajem.PlatbaNajemneho;

public interface PlatbaNajemnehoRepository extends MongoRepository<PlatbaNajemneho, String> {
  @Query("{ 'idOsoba' : ?0 }")
  List<PlatbaNajemneho> getPlatbaNajemnehoList(String idPrihlasenyUzivatel);
}