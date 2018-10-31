package com.precise_service.project_one.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.NajemniSmlouva;

public interface NajemniSmlouvaRepository extends MongoRepository<NajemniSmlouva, String> {

}