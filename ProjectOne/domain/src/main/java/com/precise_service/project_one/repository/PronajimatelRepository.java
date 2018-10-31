package com.precise_service.project_one.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.osoba.Najemnik;
import com.precise_service.project_one.entity.osoba.Pronajimatel;

public interface PronajimatelRepository extends MongoRepository<Pronajimatel, String> {

}