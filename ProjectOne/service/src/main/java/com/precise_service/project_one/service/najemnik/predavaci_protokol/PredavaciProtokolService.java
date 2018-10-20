package com.precise_service.project_one.service.najemnik.predavaci_protokol;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolEntity;
import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozkaEntity;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.repository.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntityRepository;
import com.precise_service.project_one.repository.najemnik.predavaci_protokol.PredavaciProtokolEntityRepository;
import com.precise_service.project_one.repository.najemnik.predavaci_protokol.PredavaciProtokolPolozkaEntityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PredavaciProtokolService implements IPredavaciProtokolService {

  @Autowired
  private PredavaciProtokolEntityRepository predavaciProtokolEntityRepository;

  @Autowired
  private PredavaciProtokolPolozkaEntityRepository predavaciProtokolPolozkaEntityRepository;

  @Autowired
  private VyuctovaniPolozkaTypEntityRepository vyuctovaniPolozkaTypEntityRepository;

  @Override
  public PredavaciProtokolEntity postPredavaciProtokol(PredavaciProtokolEntity predavaciProtokolEntity) {
    log.trace("postPredavaciProtokol()");
    return predavaciProtokolEntityRepository.save(predavaciProtokolEntity);
  }

  @Override
  public List<PredavaciProtokolEntity> getPredavaciProtokolEntityList() {
    log.trace("getPredavaciProtokolEntityList()");
    return predavaciProtokolEntityRepository.findAll();
  }

  @Override
  public PredavaciProtokolEntity postPredavaciProtokolNew() {
    log.trace("postPredavaciProtokolNew()");


    List<PredavaciProtokolEntity> predavaciProtokolEntityList = getPredavaciProtokolEntityList();
    PredavaciProtokolEntity predavaciProtokolEntity = predavaciProtokolEntityList.get(0);


    ArrayList<PredavaciProtokolPolozkaEntity> seznamPolozek = new ArrayList<>();

    PredavaciProtokolPolozkaEntity predavaciProtokolPolozkaEntity = new PredavaciProtokolPolozkaEntity();
    predavaciProtokolPolozkaEntity.setNazev("Nazev");
    predavaciProtokolPolozkaEntity.setJednotka("Jednotka");
    predavaciProtokolPolozkaEntity.setCisloMeraku("CisloMeraku");
    predavaciProtokolPolozkaEntity.setStavMeraku("StavMeraku");
    VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity1 = new VyuctovaniPolozkaTypEntity();
    vyuctovaniPolozkaTypEntity1.setId("5bc73e3e6c6ab02e1db90eb6");
    predavaciProtokolPolozkaEntity.setVyuctovaniPolozkaTypEntity(vyuctovaniPolozkaTypEntity1);


    seznamPolozek.add(predavaciProtokolPolozkaEntity);
    predavaciProtokolEntity.setSeznamPolozek(seznamPolozek);

    PredavaciProtokolEntity save1 = predavaciProtokolEntityRepository.save(predavaciProtokolEntity);

    log.warn("MAM to");
    return save1;
  }

  @Override
  public PredavaciProtokolEntity getPredavaciProtokol(String id) {
    log.trace("getPredavaciProtokol()");
    return predavaciProtokolEntityRepository.findById(id).get();
  }

  @Override
  public List<PredavaciProtokolEntity> getPredavaciProtokolAll() {
    log.trace("getPredavaciProtokolAll()");
    return predavaciProtokolEntityRepository.findAll();
  }

  @Override
  public void deletePredavaciProtokolAll() {
    log.trace("deletePredavaciProtokolAll()");
    predavaciProtokolEntityRepository.deleteAll();
  }
}
