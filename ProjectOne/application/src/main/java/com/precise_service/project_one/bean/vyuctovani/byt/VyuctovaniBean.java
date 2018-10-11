package com.precise_service.project_one.bean.vyuctovani.byt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.IPokusService;
import com.precise_service.project_one.domain.vyuctovani.PolozkaVyuctovani;
import com.precise_service.project_one.domain.vyuctovani.VyuctovaniEntity;
import com.precise_service.project_one.service.IVyuctovaniService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@Named
public class VyuctovaniBean {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @Autowired
  private IPokusService pokusService;

  private String nazev;
  private List<RadekTabulkyDto> radkyVyuctovani;
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  @PostConstruct
  private void initTabulkaRadky() {
    radkyVyuctovani = new ArrayList<>();

    String idVyuctovani = "5bbf5fce6c6ab027665460e6";
    VyuctovaniEntity vyuctovani = vyuctovaniService.getVyuctovani(idVyuctovani);

    nazev = vyuctovani.getNazev();
    nazev = pokusService.hello();
    List<PolozkaVyuctovani> seznamPolozek = vyuctovani.getSeznamPolozek();
    for (PolozkaVyuctovani polozkaVyuctovani : seznamPolozek) {
      RadekTabulkyDto radekTabulkyDto = new RadekTabulkyDto();
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
    for (RadekTabulkyDto radekTabulkyDto : radkyVyuctovani){
      celkemZalohy += radekTabulkyDto.getZalohy();
      celkemNaklady += radekTabulkyDto.getNaklady();
      celkemRozdil += radekTabulkyDto.getRozdil();
    }
  }
}
