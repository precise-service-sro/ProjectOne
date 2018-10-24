package com.precise_service.project_one.repository.najemnik.informace;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.precise_service.project_one.entity.najemnik.informace.Najemnik;

public interface NajemnikRepository extends MongoRepository<Najemnik, String> {

}