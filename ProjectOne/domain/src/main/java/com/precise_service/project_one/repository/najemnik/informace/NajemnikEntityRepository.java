package com.precise_service.project_one.repository.najemnik.informace;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.najemnik.informace.NajemnikEntity;

public interface NajemnikEntityRepository extends MongoRepository<NajemnikEntity, String> {

}