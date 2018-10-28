package com.precise_service.project_one.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozkaTyp;

public interface VyuctovaniPolozkaTypRepository extends MongoRepository<VyuctovaniPolozkaTyp, String> {

}