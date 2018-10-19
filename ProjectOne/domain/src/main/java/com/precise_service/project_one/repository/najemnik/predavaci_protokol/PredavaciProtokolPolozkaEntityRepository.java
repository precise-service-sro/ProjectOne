package com.precise_service.project_one.repository.najemnik.predavaci_protokol;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.byt.predavaci_protokol.PredavaciProtokolPolozkaEntity;

public interface PredavaciProtokolPolozkaEntityRepository extends MongoRepository<PredavaciProtokolPolozkaEntity, String> {

}