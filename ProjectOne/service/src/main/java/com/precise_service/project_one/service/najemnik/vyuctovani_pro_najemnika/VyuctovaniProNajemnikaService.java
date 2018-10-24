package com.precise_service.project_one.service.najemnik.vyuctovani_pro_najemnika;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika.VyuctovaniProNajemnika;
import com.precise_service.project_one.repository.najemnik.vyuctovani_pro_najemnika.VyuctovaniProNajemnikaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniProNajemnikaService implements IVyuctovaniProNajemnikaService {

  @Autowired
  private VyuctovaniProNajemnikaRepository vyuctovaniProNajemnikaRepository;


  @Override
  public VyuctovaniProNajemnika postVyuctovaniProNajemnika(VyuctovaniProNajemnika vyuctovaniProNajemnika) {
    log.trace("postVyuctovaniProNajemnika()");
    return vyuctovaniProNajemnikaRepository.save(vyuctovaniProNajemnika);
  }

  @Override
  public VyuctovaniProNajemnika putVyuctovaniProNajemnika(VyuctovaniProNajemnika vyuctovaniProNajemnika) {
    log.trace("postVyuctovaniProNajemnika()");
    return vyuctovaniProNajemnikaRepository.save(vyuctovaniProNajemnika);
  }

  @Override
  public VyuctovaniProNajemnika getVyuctovaniProNajemnika(String idVyuctovaniProNajemnika) {
    log.trace("getVyuctovaniProNajemnika()");
    return vyuctovaniProNajemnikaRepository.findById(idVyuctovaniProNajemnika).get();
  }

  @Override
  public List<VyuctovaniProNajemnika> getVyuctovaniProNajemnikaAll() {
    log.trace("getVyuctovaniProNajemnika()");
    return vyuctovaniProNajemnikaRepository.findAll();
  }

  @Override
  public void deleteVyuctovaniProNajemnikaAll() {
    log.trace("deleteVyuctovaniProNajemnikaAll()");
    vyuctovaniProNajemnikaRepository.deleteAll();
  }
}
