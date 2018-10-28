package com.precise_service.project_one.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.Adresa;

public interface AdresaRepository extends MongoRepository<Adresa, String> {

}