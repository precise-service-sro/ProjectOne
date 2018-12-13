package com.precise_service.project_one.service.faktura;

import java.util.List;

import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.filter.DataFilter;
import com.precise_service.project_one.service.common.AbstractService;

import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.MongoQueryBuilder.getQuery;

@Slf4j
@Service
public class FakturaService extends AbstractService implements IFakturaService {

  @Override
  public Faktura postFaktura(Faktura faktura) {
    log.trace("postFaktura()");
    return fakturaRepository.save(faktura);
  }

  @Override
  public Faktura zduplikovatFaktura(Faktura faktura) {
    log.trace("zduplikovatFaktura()");
    faktura.setId(null);
    return fakturaRepository.save(faktura);
  }

  @Override
  public Faktura putFaktura(Faktura faktura) {
    log.trace("putFaktura()");
    return fakturaRepository.save(faktura);
  }

  @Override
  public Faktura getFaktura(String idFaktura) {
    log.trace("getFaktura()");
    return fakturaRepository.findById(idFaktura).get();
  }

  @Override
  public List<Faktura> getFakturaList(DataFilter dataFilter) {
    log.trace("getFakturaList()");
    return mongoTemplate.find(getQuery(dataFilter), Faktura.class);
  }

  @Override
  public void deleteFaktura(String idFaktura) {
    log.trace("deleteFaktura()");
    fakturaRepository.deleteById(idFaktura);
  }

  @Override
  public void deleteFakturaAll() {
    log.trace("deleteFakturaAll()");
    fakturaRepository.deleteAll();
  }
}
