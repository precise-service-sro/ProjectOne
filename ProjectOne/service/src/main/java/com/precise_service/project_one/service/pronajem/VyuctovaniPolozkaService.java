package com.precise_service.project_one.service.pronajem;

import java.util.List;

import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.pronajem.VyuctovaniPolozka;
import com.precise_service.project_one.service.common.AbstractService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VyuctovaniPolozkaService extends AbstractService implements IVyuctovaniPolozkaService {

  @Override
  public VyuctovaniPolozka postVyuctovaniPolozka(VyuctovaniPolozka vyuctovani) {
    log.trace("postVyuctovaniPolozka()");
    return vyuctovaniPolozkaRepository.save(vyuctovani);
  }

  @Override
  public VyuctovaniPolozka putVyuctovaniPolozka(VyuctovaniPolozka vyuctovani) {
    log.trace("putVyuctovaniPolozka()");
    return vyuctovaniPolozkaRepository.save(vyuctovani);
  }

  @Override
  public VyuctovaniPolozka getVyuctovaniPolozka(String idVyuctovaniPolozka) {
    log.trace("getVyuctovaniPolozka()");
    return vyuctovaniPolozkaRepository.findById(idVyuctovaniPolozka).get();
  }

  @Override
  public List<VyuctovaniPolozka> getVyuctovaniPolozkaList(String idPolozkaTyp, String idVyuctovani) {
    log.trace("getVyuctovaniPolozkaList()");
    return vyuctovaniPolozkaRepository.getVyuctovaniPolozkaList(idPolozkaTyp, idVyuctovani);
  }

  @Override
  public List<VyuctovaniPolozka> getVyuctovaniPolozkaAll() {
    log.trace("getVyuctovaniPolozkaAll()");
    return vyuctovaniPolozkaRepository.findAll();
  }

  @Override
  public List<VyuctovaniPolozka> getVyuctovaniPolozkaAll(String idVyuctovani) {
    log.trace("getVyuctovaniPolozkaAll()");
    return vyuctovaniPolozkaRepository.getVyuctovaniPolozkaAll(idVyuctovani);
  }

  @Override
  public List<VyuctovaniPolozka> getVypocitanaVyuctovaniPolozkaList(String idVyuctovani) {
    log.trace("getVypocitanaVyuctovaniPolozkaList()");
    return vyuctovaniPolozkaRepository.getVypocitanaVyuctovaniPolozkaList(idVyuctovani);
  }


  @Override
  public void deleteVyuctovaniPolozka(String idVyuctovaniPolozka) {
    log.trace("deleteVyuctovaniPolozka()");
    vyuctovaniPolozkaRepository.deleteById(idVyuctovaniPolozka);
  }

  @Override
  public void deleteVyuctovaniPolozkaAll() {
    log.trace("deleteVyuctovaniPolozkaAll()");
    vyuctovaniPolozkaRepository.deleteAll();
  }

  @Override
  public void deleteVyuctovaniPolozkaAll(String idVyuctovani) {
    log.trace("deleteVyuctovaniPolozkaAll()");
    List<VyuctovaniPolozka> vyuctovaniPolozkaAll = vyuctovaniPolozkaRepository.getVyuctovaniPolozkaAll(idVyuctovani);
    for (VyuctovaniPolozka vyuctovaniPolozka : vyuctovaniPolozkaAll) {
      vyuctovaniPolozkaRepository.deleteById(vyuctovaniPolozka.getId());
    }
  }
}
