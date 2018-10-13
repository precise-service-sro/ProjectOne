package com.precise_service.project_one.web.vyuctovani.najemnik;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.vyuctovani.PolozkaVyuctovaniEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniEntity;
import com.precise_service.project_one.service.IVyuctovaniService;
import com.precise_service.project_one.web.vyuctovani.najemnik.dto.RadekTabulkyDto2;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniNajemnikBean {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  private String nazev;

  private List<RadekTabulkyDto2> radkyVyuctovani;
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  public String getNazev() {
    return nazev;
  }

  @PostConstruct
  private void initTabulkaRadky() {
    radkyVyuctovani = new ArrayList<>();

    String idVyuctovani = "5bc1c6666c6ab083ca9e5106";
    VyuctovaniEntity vyuctovani = vyuctovaniService.getVyuctovani(idVyuctovani);

    List<PolozkaVyuctovaniEntity> seznamPolozek = vyuctovani.getSeznamPolozek();
    for (PolozkaVyuctovaniEntity polozkaVyuctovaniEntity : seznamPolozek) {
      RadekTabulkyDto2 radekTabulkyDto = new RadekTabulkyDto2();
      radekTabulkyDto.setNazev(polozkaVyuctovaniEntity.getNazev());
      radekTabulkyDto.setSpotrebaMnozstvi(polozkaVyuctovaniEntity.getSpotreba().getMnozstvi());
      radekTabulkyDto.setSpotrebaJednotka(polozkaVyuctovaniEntity.getSpotreba().getJednotka());
      radekTabulkyDto.setZalohy(polozkaVyuctovaniEntity.getZalohy().getMnozstvi());
      radekTabulkyDto.setNaklady(polozkaVyuctovaniEntity.getNaklady().getMnozstvi());
      radekTabulkyDto.setRozdil(polozkaVyuctovaniEntity.getZalohy().getMnozstvi() - polozkaVyuctovaniEntity.getNaklady().getMnozstvi());
      radkyVyuctovani.add(radekTabulkyDto);
    }
    initTabulkaSoucty();
  }

  private void initTabulkaSoucty(){
    celkemZalohy = 0.0;
    celkemNaklady = 0.0;
    celkemRozdil = 0.0;
    for (RadekTabulkyDto2 radekTabulkyDto : radkyVyuctovani){
      celkemZalohy += radekTabulkyDto.getZalohy();
      celkemNaklady += radekTabulkyDto.getNaklady();
      celkemRozdil += radekTabulkyDto.getRozdil();
    }
  }

  public List<RadekTabulkyDto2> getRadkyVyuctovani(){
    log.trace("getRadkyVyuctovani()");
    return radkyVyuctovani;
  }

  public double getCelkemZalohy() {
    return celkemZalohy;
  }

  public double getCelkemNaklady(){
    return celkemNaklady;
  }

  public double getCelkemRozdil(){
    return celkemRozdil;
  }
}
