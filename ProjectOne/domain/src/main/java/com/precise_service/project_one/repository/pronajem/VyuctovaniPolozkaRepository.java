package com.precise_service.project_one.repository.pronajem;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.pronajem.VyuctovaniPolozka;

public interface VyuctovaniPolozkaRepository extends MongoRepository<VyuctovaniPolozka, String> {

  @Query("{ 'idOsoba' : ?0 }")
  List<VyuctovaniPolozka> getVyuctovaniPolozkaAll(String idVyuctovani);

  @Query("{ $and: [ { 'vyuctovani.id' : ?0 }, { 'zvyraznit' : true } ] }")
  List<VyuctovaniPolozka> getVypocitanaVyuctovaniPolozkaList(String idVyuctovani);

  @Query("{ $and: [ { 'polozkaTyp.id' : ?0 } , { 'vyuctovani.id' : ?1 } ] }")
  List<VyuctovaniPolozka> getVyuctovaniPolozkaList(String idPolozkaTyp, String idVyuctovani);
}