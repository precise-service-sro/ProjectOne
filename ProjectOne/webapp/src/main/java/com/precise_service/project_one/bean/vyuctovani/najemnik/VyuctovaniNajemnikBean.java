package com.precise_service.project_one.bean.vyuctovani.najemnik;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.vyuctovani.PolozkaVyuctovani;
import com.precise_service.project_one.entity.vyuctovani.VyuctovaniEntity;
import com.precise_service.project_one.service.IVyuctovaniService;

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

    String idVyuctovani = "5bbf5fce6c6ab027665460e6";
    VyuctovaniEntity vyuctovani = vyuctovaniService.getVyuctovani(idVyuctovani);

    List<PolozkaVyuctovani> seznamPolozek = vyuctovani.getSeznamPolozek();
    for (PolozkaVyuctovani polozkaVyuctovani : seznamPolozek) {
      RadekTabulkyDto2 radekTabulkyDto = new RadekTabulkyDto2();
      radekTabulkyDto.setNazev(polozkaVyuctovani.getNazev());
      radekTabulkyDto.setSpotrebaMnozstvi(polozkaVyuctovani.getSpotreba().getMnozstvi());
      radekTabulkyDto.setSpotrebaJednotka(polozkaVyuctovani.getSpotreba().getJednotka());
      radekTabulkyDto.setZalohy(polozkaVyuctovani.getZalohy().getMnozstvi());
      radekTabulkyDto.setNaklady(polozkaVyuctovani.getNaklady().getMnozstvi());
      radekTabulkyDto.setRozdil(polozkaVyuctovani.getZalohy().getMnozstvi() - polozkaVyuctovani.getNaklady().getMnozstvi());
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
