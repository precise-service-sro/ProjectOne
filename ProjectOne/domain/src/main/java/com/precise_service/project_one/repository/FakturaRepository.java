package com.precise_service.project_one.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.precise_service.project_one.entity.faktura.Faktura;

public interface FakturaRepository extends MongoRepository<Faktura, String> {

  @Query("{ $or: [ { 'zuctovaciObdobi.zacatek' : { $gte: ?0, $lte: ?1 } }, { 'zuctovaciObdobi.konec' : { $gte: ?0, $lte: ?1} } ] }")
  List<Faktura> getSeznamFakturVeZuctovacimObdobi(Date zacatekZuctovacihoObdobi, Date konecZuctovacihoObdobi);

  @Query("{ $and: [ { 'uzivatel.id' : ?2 }, { $or: [ { 'zuctovaciObdobi.zacatek' : { $gte: ?0, $lte: ?1 } }, { 'zuctovaciObdobi.konec' : { $gte: ?0, $lte: ?1} } ] } ] }")
  List<Faktura> getSeznamFakturVeZuctovacimObdobi(Date zacatekZuctovacihoObdobi, Date konecZuctovacihoObdobi, String idPrihlasenyUzivatel);
}