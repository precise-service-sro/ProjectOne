package com.precise_service.project_one.repository.byt.vyuctovani_za_byt;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaByt;

public interface VyuctovaniZaBytRepository extends MongoRepository<VyuctovaniZaByt, String> {

  @Query("{ $or: [ { 'zuctovaciObdobi.zacatek' : { $gte: ?0, $lte: ?1 } }, { 'zuctovaciObdobi.konec' : { $gte: ?0, $lte: ?1} } ] }")
  List<VyuctovaniZaByt> getVyuctovaniZaBytInRange(LocalDate from, LocalDate to);
}