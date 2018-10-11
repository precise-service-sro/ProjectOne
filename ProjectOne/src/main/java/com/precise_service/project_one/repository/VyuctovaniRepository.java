package com.precise_service.project_one.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.domain.Vyuctovani;

public interface VyuctovaniRepository extends MongoRepository<Vyuctovani, String> {

}