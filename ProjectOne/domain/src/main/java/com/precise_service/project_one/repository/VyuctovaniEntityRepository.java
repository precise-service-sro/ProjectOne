package com.precise_service.project_one.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniEntity;

public interface VyuctovaniEntityRepository extends MongoRepository<VyuctovaniEntity, String> {

  @Query("{ $or: [ { 'zuctovaciObdobi.zacatek' : { $gte: ?0, $lte: ?1 } }, { 'zuctovaciObdobi.konec' : { $gte: ?0, $lte: ?1} } ] }")
  List<VyuctovaniEntity> getVyuctovaniInRange(LocalDate from, LocalDate to);
}