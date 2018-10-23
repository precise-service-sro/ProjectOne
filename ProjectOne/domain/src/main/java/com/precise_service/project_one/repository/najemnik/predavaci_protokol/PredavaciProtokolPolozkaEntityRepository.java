package com.precise_service.project_one.repository.najemnik.predavaci_protokol;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytEntity;
import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozkaEntity;

public interface PredavaciProtokolPolozkaEntityRepository extends MongoRepository<PredavaciProtokolPolozkaEntity, String> {

  @Query("{ 'predavaciProtokolEntity.id' : ?0 }")
  List<PredavaciProtokolPolozkaEntity> getAllPredavaciProtokolPolozkaEntityAll(String idPredavaciProtokolEntity);

}