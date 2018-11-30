package com.precise_service.project_one.service.pronajem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.filter.DataFilter;
import com.precise_service.project_one.entity.pronajem.PlatbaNajemneho;
import com.precise_service.project_one.repository.pronajem.PlatbaNajemnehoRepository;

import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.commons.MongoQueryBuilder.getQuery;

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
  public List<PlatbaNajemneho> getPlatbaNajemnehoList(DataFilter dataFilter) {
    log.trace("getPlatbaNajemnehoList()");
    return mongoTemplate.find(getQuery(dataFilter), PlatbaNajemneho.class);
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
