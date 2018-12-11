package com.precise_service.project_one.service.pronajem;

import java.util.List;

import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.filter.DataFilter;
import com.precise_service.project_one.entity.pronajem.NajemniSmlouva;
import com.precise_service.project_one.entity.pronajem.PlatbaNajemneho;
import com.precise_service.project_one.service.common.AbstractService;

import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.commons.MongoQueryBuilder.getQuery;

@Slf4j
@Service
public class NajemniSmlouvaService extends AbstractService implements INajemniSmlouvaService {

  @Override
  public NajemniSmlouva postNajemniSmlouva(NajemniSmlouva najemniSmlouva) {
    log.trace("postNajemniSmlouva()");
    return najemniSmlouvaRepository.save(najemniSmlouva);
  }

  @Override
  public NajemniSmlouva putNajemniSmlouva(NajemniSmlouva najemniSmlouva) {
    log.trace("putNajemniSmlouva()");
    return najemniSmlouvaRepository.save(najemniSmlouva);
  }

  @Override
  public NajemniSmlouva getNajemniSmlouva(String idNajemniSmlouva) {
    log.trace("getNajemniSmlouva()");
    return najemniSmlouvaRepository.findById(idNajemniSmlouva).get();
  }

  @Override
  public List<NajemniSmlouva> getNajemniSmlouvaList(DataFilter dataFilter) {
    log.trace("getNajemniSmlouvaList()");
    return mongoTemplate.find(getQuery(dataFilter), NajemniSmlouva.class);
  }

  @Override
  public void deleteNajemniSmlouvaAll() {
    log.trace("deleteNajemniSmlouvaAll()");
    najemniSmlouvaRepository.deleteAll();
  }

  @Override
  public void deleteNajemniSmlouva(String idNajemniSmlouva) {
    log.trace("deleteNajemniSmlouva()");
    najemniSmlouvaRepository.deleteById(idNajemniSmlouva);
  }
}
