package com.precise_service.project_one.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;

public interface VyuctovaniRepository extends MongoRepository<Vyuctovani, String> {

  @Query("{ $or: [ { 'zuctovaciObdobi.zacatek' : { $gte: ?0, $lte: ?1 } }, { 'zuctovaciObdobi.konec' : { $gte: ?0, $lte: ?1} } ] }")
  List<Vyuctovani> getVyuctovaniInRange(Date from, Date to);
}