package com.precise_service.project_one.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.osoba.Osoba;

public interface OsobaRepository extends MongoRepository<Osoba, String> {

}