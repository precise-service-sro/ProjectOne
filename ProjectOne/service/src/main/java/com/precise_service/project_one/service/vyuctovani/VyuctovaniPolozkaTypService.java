package com.precise_service.project_one.service.vyuctovani;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozkaTyp;
import com.precise_service.project_one.repository.VyuctovaniPolozkaTypRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniPolozkaTypService implements IVyuctovaniPolozkaTypService {

  @Autowired
  private VyuctovaniPolozkaTypRepository vyuctovaniPolozkaTypRepository;

  @Override
  public VyuctovaniPolozkaTyp postVyuctovaniPolozkaTyp(VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp) {
    log.trace("postVyuctovaniPolozkaTyp()");
    return vyuctovaniPolozkaTypRepository.save(vyuctovaniPolozkaTyp);
  }

  @Override
  public VyuctovaniPolozkaTyp putVyuctovaniPolozkaTyp(VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp) {
    log.trace("putVyuctovaniPolozkaTyp()");
    return vyuctovaniPolozkaTypRepository.save(vyuctovaniPolozkaTyp);
  }

  @Override
  public VyuctovaniPolozkaTyp getVyuctovaniPolozkaTyp(String idVyuctovaniPolozkaTyp) {
    log.trace("getVyuctovaniPolozkaTyp()");
    return vyuctovaniPolozkaTypRepository.findById(idVyuctovaniPolozkaTyp).get();
  }

  @Override
  public List<VyuctovaniPolozkaTyp> getVyuctovaniPolozkaTypAll() {
    log.trace("getVyuctovaniPolozkaTypAll()");
    return vyuctovaniPolozkaTypRepository.findAll();
  }

  @Override
  public void deleteVyuctovaniPolozkaTyp(String idVyuctovaniPolozkaTyp) {
    log.trace("deleteVyuctovaniPolozkaTyp()");
    vyuctovaniPolozkaTypRepository.deleteById(idVyuctovaniPolozkaTyp);
  }

  @Override
  public void deleteVyuctovaniPolozkaTypAll() {
    log.trace("deleteVyuctovaniPolozkaTypAll()");
    vyuctovaniPolozkaTypRepository.deleteAll();
  }
}
