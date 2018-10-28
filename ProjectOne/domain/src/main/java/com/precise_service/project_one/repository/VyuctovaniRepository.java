package com.precise_service.project_one.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;

public interface VyuctovaniRepository extends MongoRepository<Vyuctovani, String> {

  @Query("{ $or: [ { 'vyuctovaniZuctovaciObdobi.zacatek' : { $gte: ?0, $lte: ?1 } }, { 'vyuctovaniZuctovaciObdobi.konec' : { $gte: ?0, $lte: ?1} } ] }")
  List<Vyuctovani> getVyuctovaniInRange(LocalDate from, LocalDate to);
}