package com.precise_service.project_one.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.byt.predavaciProtokol.PredavaciProtokolEntity;

public interface PredavaciProtokolEntityRepository extends MongoRepository<PredavaciProtokolEntity, String> {

}