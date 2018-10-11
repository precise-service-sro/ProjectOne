package com.precise_service.project_one.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.domain.vyuctovani.PolozkaVyuctovani;
import com.precise_service.project_one.domain.vyuctovani.Vyuctovani;
import com.precise_service.project_one.repository.VyuctovaniRepository;
import com.precise_service.project_one.service.IVyuctovaniService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniService implements IVyuctovaniService {

  @Autowired
  private VyuctovaniRepository vyuctovaniRepository;

  @Override
  public void deleteVyuctovaniAll() {
    log.trace("deleteVyuctovaniAll()");
    vyuctovaniRepository.deleteAll();
  }

  @Override
  public Vyuctovani getVyuctovani(String id) {
    log.trace("getVyuctovani()");
    return vyuctovaniRepository.findById(id).get();
  }

  @Override
  public void createVyuctovani(Vyuctovani vyuctovani) {
    log.trace("createVyuctovani()");
    vyuctovaniRepository.save(vyuctovani);
  }

  @Override
  public List<PolozkaVyuctovani> getSeznamPolozek(){
    log.trace("getSeznamPolozek()");
    List<Vyuctovani> vyuctovaniList = vyuctovaniRepository.findAll();
    List<PolozkaVyuctovani> seznamPolozek = vyuctovaniList.get(0).getSeznamPolozek();
    return seznamPolozek;
  }
}
