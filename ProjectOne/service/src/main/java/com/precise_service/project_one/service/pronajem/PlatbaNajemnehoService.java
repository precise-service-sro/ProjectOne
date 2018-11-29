package com.precise_service.project_one.service.pronajem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.filter.DatovyFilter;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.pronajem.PlatbaNajemneho;
import com.precise_service.project_one.repository.pronajem.PlatbaNajemnehoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlatbaNajemnehoService implements IPlatbaNajemnehoService {

  @Autowired
  private PlatbaNajemnehoRepository platbaNajemnehoRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public PlatbaNajemneho postPlatbaNajemneho(PlatbaNajemneho platbaNajemneho) {
    log.trace("postPlatbaNajemneho()");
    return platbaNajemnehoRepository.save(platbaNajemneho);
  }

  @Override
  public PlatbaNajemneho putPlatbaNajemneho(PlatbaNajemneho platbaNajemneho) {
    log.trace("putPlatbaNajemneho()");
    return platbaNajemnehoRepository.save(platbaNajemneho);
  }

  @Override
  public PlatbaNajemneho getPlatbaNajemneho(String idPlatbaNajemneho) {
    log.trace("getPlatbaNajemneho()");
    return platbaNajemnehoRepository.findById(idPlatbaNajemneho).get();
  }

  @Override
  public List<PlatbaNajemneho> getPlatbaNajemnehoList(DatovyFilter datovyFilter) {
    log.trace("getPlatbaNajemnehoList()");

    Query query = new Query();

    if (datovyFilter.getNemovitostFilter() != null && datovyFilter.getNemovitostFilter().getIdNemovitost() != null) {
      query.addCriteria(Criteria.where("nemovitost.id").is(datovyFilter.getNemovitostFilter().getIdNemovitost()));
    }

    if (datovyFilter.getPlatbaNajemnehoFilter() != null && datovyFilter.getPlatbaNajemnehoFilter().getIdOdesilatel() != null) {
      query.addCriteria(Criteria.where("odesilatel.id").is(datovyFilter.getPlatbaNajemnehoFilter().getIdOdesilatel()));
    }


    List<PlatbaNajemneho> platbaNajemnehoList = mongoTemplate.find(query, PlatbaNajemneho.class);



    return platbaNajemnehoList;
  }

  @Override
  public List<PlatbaNajemneho> getPlatbaNajemnehoList(Osoba prihlasenyUzivatel) {
    log.trace("getPlatbaNajemnehoAll()");
    return platbaNajemnehoRepository.getPlatbaNajemnehoList(prihlasenyUzivatel.getId());
  }

  @Override
  public void deletePlatbaNajemnehoAll() {
    log.trace("deletePlatbaNajemnehoAll()");
    platbaNajemnehoRepository.deleteAll();
  }

  @Override
  public void deletePlatbaNajemneho(String idPlatbaNajemneho) {
    log.trace("deletePlatbaNajemneho()");
    platbaNajemnehoRepository.deleteById(idPlatbaNajemneho);
  }
}
