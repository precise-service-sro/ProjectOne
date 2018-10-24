package com.precise_service.project_one.repository.byt.vyuctovani_za_byt;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytPolozka;

public interface VyuctovaniZaBytPolozkaRepository extends MongoRepository<VyuctovaniZaBytPolozka, String> {

  @Query("{ 'vyuctovaniZaByt.id' : ?0 }")
  List<VyuctovaniZaBytPolozka> getVyuctovaniZaBytPolozkaAll(String idVyuctovaniZaByt);

}