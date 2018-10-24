package com.precise_service.project_one.repository.byt.informace;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.byt.informace.Byt;

public interface BytRepository extends MongoRepository<Byt, String> {

}