package com.precise_service.project_one.repository;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;

public interface FakturaRepository extends MongoRepository<Faktura, String> {

  @Query("{ $and: [ { 'idOsoba' : ?2 }, { $or: [ { 'zuctovaciObdobi.zacatek' : { $gte: ?0, $lte: ?1 } }, { 'zuctovaciObdobi.konec' : { $gte: ?0, $lte: ?1} } ] } ] }")
  List<Faktura> getSeznamFakturVeZuctovacimObdobi(Date zacatekZuctovacihoObdobi, Date konecZuctovacihoObdobi, String idPrihlasenyUzivatel);

  @Query("{ 'idOsoba' : ?0 }")
  List<Faktura> getSeznamFaktur(String idPrihlasenyUzivatel);

  @Query("{ 'nemovitost.id' : ?0 }")
  List<Faktura> getAllFakturaListByNemovitost(String idNemovitost);
}