package com.precise_service.project_one.repository.najemnik.predavaci_protokol;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokol;

public interface PredavaciProtokolRepository extends MongoRepository<PredavaciProtokol, String> {

}