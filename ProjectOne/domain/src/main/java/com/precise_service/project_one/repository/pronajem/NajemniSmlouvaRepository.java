package com.precise_service.project_one.repository.pronajem;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.pronajem.NajemniSmlouva;

public interface NajemniSmlouvaRepository extends MongoRepository<NajemniSmlouva, String> {
  @Query("{ 'idOsoba' : ?0 }")
  List<NajemniSmlouva> getNajemniSmlouvaList(String idPrihlasenyUzivatel);
}