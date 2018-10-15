package com.precise_service.project_one.web.predavaci_protokol;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.predavaciProtokol.PredavaciProtokolEntity;
import com.precise_service.project_one.entity.byt.predavaciProtokol.PredavaciProtokolPolozkaEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniPolozkaEntity;
import com.precise_service.project_one.service.IPredavaciProtokolService;
import com.precise_service.project_one.web.predavaci_protokol.dto.PredavaciProtokolRadkaDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PredavaciProtokolBean {

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  private String nazev;

  private List<PredavaciProtokolRadkaDto> radkyVyuctovani;
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  public String getNazev() {
    return nazev;
  }

  private void initTabulkaRadky() {
    radkyVyuctovani = new ArrayList<>();

    String idVyuctovani = "5bc1c6666c6ab083ca9e506";
    VyuctovaniEntity vyuctovani = new VyuctovaniEntity();  // vyuctovaniService.getVyuctovani(idVyuctovani);

    List<VyuctovaniPolozkaEntity> seznamPolozek = vyuctovani.getSeznamPolozek();
    for (VyuctovaniPolozkaEntity vyuctovaniPolozkaEntity : seznamPolozek) {
      PredavaciProtokolRadkaDto radekTabulkyDto = new PredavaciProtokolRadkaDto();
      radekTabulkyDto.setNazev(vyuctovaniPolozkaEntity.getNazev());
      radekTabulkyDto.setSpotrebaMnozstvi(vyuctovaniPolozkaEntity.getSpotreba().getMnozstvi());
      radekTabulkyDto.setSpotrebaJednotka(vyuctovaniPolozkaEntity.getSpotreba().getJednotka());
      radekTabulkyDto.setZalohy(vyuctovaniPolozkaEntity.getZalohy().getMnozstvi());
      radekTabulkyDto.setNaklady(vyuctovaniPolozkaEntity.getNaklady().getMnozstvi());
      radekTabulkyDto.setRozdil(vyuctovaniPolozkaEntity.getZalohy().getMnozstvi() - vyuctovaniPolozkaEntity.getNaklady().getMnozstvi());
      radkyVyuctovani.add(radekTabulkyDto);
    }
    initTabulkaSoucty();
  }

  private void initTabulkaSoucty(){
    celkemZalohy = 0.0;
    celkemNaklady = 0.0;
    celkemRozdil = 0.0;
    for (PredavaciProtokolRadkaDto radekTabulkyDto : radkyVyuctovani){
      celkemZalohy += radekTabulkyDto.getZalohy();
      celkemNaklady += radekTabulkyDto.getNaklady();
      celkemRozdil += radekTabulkyDto.getRozdil();
    }
  }

  public List<PredavaciProtokolRadkaDto> getRadky(){
    log.trace("getRadky()");
    List<PredavaciProtokolEntity> predavaciProtokolEntityList = predavaciProtokolService.getPredavaciProtokolEntityList();
    PredavaciProtokolEntity predavaciProtokolEntity = predavaciProtokolEntityList.get(0);

    List<PredavaciProtokolRadkaDto> predavaciProtokolRadkaDtoList = new ArrayList<>();
    List<PredavaciProtokolPolozkaEntity> seznamPolozek = predavaciProtokolEntity.getSeznamPolozek();
    for (PredavaciProtokolPolozkaEntity redavaciProtokolPolozkaEntity : seznamPolozek) {
      PredavaciProtokolRadkaDto predavaciProtokolRadkaDto = new PredavaciProtokolRadkaDto();
      predavaciProtokolRadkaDto.setNazev(redavaciProtokolPolozkaEntity.getNazev());
      predavaciProtokolRadkaDtoList.add(predavaciProtokolRadkaDto);
    }

    return predavaciProtokolRadkaDtoList;
  }
}
