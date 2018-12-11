package com.precise_service.project_one.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.faktura.Faktura;

public interface FakturaRepository extends MongoRepository<Faktura, String> {

}