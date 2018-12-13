package com.precise_service.project_one.service.osoba;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.filter.DataFilter;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.common.AbstractService;

import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.MongoQueryBuilder.getQuery;

@Slf4j
@Service
public class OsobaService extends AbstractService implements IOsobaService {

  @Override
  public Osoba postOsoba(Osoba osoba) {
    log.trace("postOsoba()");
    return osobaRepository.save(osoba);
  }

  @Override
  public Osoba putOsoba(Osoba osoba) {
    log.trace("putOsoba()");
    return osobaRepository.save(osoba);
  }

  @Override
  public Osoba getOsoba(String idOsoba) {
    log.trace("getOsoba() " + idOsoba);
    Optional<Osoba> optionalOsoba = osobaRepository.findById(idOsoba);
    return (optionalOsoba.isPresent()) ? optionalOsoba.get() : null;
  }

  @Override
  public Osoba getOsobaByPrihlasovaciJmenoAndHeslo(String prihlasovaciJmeno, String heslo) {
    log.trace("getOsobaByPrihlasovaciJmenoAndHeslo()");
    return osobaRepository.getOsobaByPrihlasovaciJmenoAndHeslo(prihlasovaciJmeno, heslo);
  }

  @Override
  public List<Osoba> getOsobaList(DataFilter dataFilter) {
    log.trace("getOsobaList()");
    return mongoTemplate.find(getQuery(dataFilter), Osoba.class);
  }

  @Override
  public void deleteOsobaAll() {
    log.trace("deleteOsobaAll()");
    osobaRepository.deleteAll();
  }

  @Override
  public void deleteOsoba(String idOsoba) {
    log.trace("deleteOsoba()");
    osobaRepository.deleteById(idOsoba);
  }
}
