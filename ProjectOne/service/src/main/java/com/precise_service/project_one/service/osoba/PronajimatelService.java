package com.precise_service.project_one.service.osoba;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.osoba.Pronajimatel;
import com.precise_service.project_one.repository.PronajimatelRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PronajimatelService implements IPronajimatelService {

  @Autowired
  private PronajimatelRepository pronajimatelRepository;

  @Override
  public Pronajimatel postPronajimatel(Pronajimatel pronajimatel) {
    log.trace("postPronajimatel()");
    return pronajimatelRepository.save(pronajimatel);
  }

  @Override
  public Pronajimatel putPronajimatel(Pronajimatel pronajimatel) {
    log.trace("putPronajimatel()");
    return pronajimatelRepository.save(pronajimatel);
  }

  @Override
  public Pronajimatel getPronajimatel(String idPronajimatel) {
    log.trace("getPronajimatel()");
    return pronajimatelRepository.findById(idPronajimatel).get();
  }

  @Override
  public List<Pronajimatel> getPronajimatelAll() {
    log.trace("getPronajimatelAll()");
    return pronajimatelRepository.findAll();
  }

  @Override
  public void deletePronajimatelAll() {
    log.trace("deletePronajimatelAll()");
    pronajimatelRepository.deleteAll();
  }

  @Override
  public void deletePronajimatel(String idPronajimatel) {
    log.trace("deletePronajimatel()");
    pronajimatelRepository.deleteById(idPronajimatel);
  }
}
