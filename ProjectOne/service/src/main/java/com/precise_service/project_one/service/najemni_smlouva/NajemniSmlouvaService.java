package com.precise_service.project_one.service.najemni_smlouva;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.NajemniSmlouva;
import com.precise_service.project_one.repository.NajemniSmlouvaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NajemniSmlouvaService implements INajemniSmlouvaService {

  @Autowired
  private NajemniSmlouvaRepository najemniSmlouvaRepository;

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
  public List<NajemniSmlouva> getNajemniSmlouvaAll() {
    log.trace("getNajemniSmlouvaAll()");
    return najemniSmlouvaRepository.findAll();
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
