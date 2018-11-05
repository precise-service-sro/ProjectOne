package com.precise_service.project_one.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.osoba.Osoba;

public interface OsobaRepository extends MongoRepository<Osoba, String> {

  @Query("{ 'prihlasovaciJmeno' : ?0, 'heslo' : ?1 }")
  Osoba getOsobaByPrihlasovaciJmenoAndHeslo(String username, String password);

  @Query("{ $or: [ { 'uzivatel.id' : ?0 }, { '_id' : ?0 } ] }")
  List<Osoba> getOsobaAll(String idPrihlasenyUzivatel);
}