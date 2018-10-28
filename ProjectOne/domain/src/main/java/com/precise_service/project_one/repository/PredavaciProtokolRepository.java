package com.precise_service.project_one.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.predavaci_protokol.PredavaciProtokol;

public interface PredavaciProtokolRepository extends MongoRepository<PredavaciProtokol, String> {

}