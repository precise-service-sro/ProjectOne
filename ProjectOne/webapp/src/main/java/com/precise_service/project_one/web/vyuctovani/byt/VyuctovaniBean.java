package com.precise_service.project_one.web.vyuctovani.byt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniCisloEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniPolozkaEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.service.IPredavaciProtokolService;
import com.precise_service.project_one.service.IVyuctovaniPolozkaTypeService;
import com.precise_service.project_one.service.IVyuctovaniService;
import com.precise_service.project_one.web.vyuctovani.byt.dto.RadekTabulkyDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.precise_service.project_one.commons.DateFormatter.format;


@Slf4j
@Data
@Named
public class VyuctovaniBean {

  public static final String ZUCTOVACI_OBDOBI_DATE_FORMAT = "dd/MM/yyyy";
  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @Autowired
  private IVyuctovaniPolozkaTypeService vyuctovaniPolozkaTypeService;

  private String nazev;
  private String popis;
  private String zuctovaciObdobi;
  private List<RadekTabulkyDto> radkyVyuctovani;
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  public void prepareData(String idVyuctovani) {
    log.trace("prepareData()");
    log.debug("idVyuctovani: " + idVyuctovani);
    radkyVyuctovani = new ArrayList<>();

    VyuctovaniEntity vyuctovani = vyuctovaniService.getVyuctovani(idVyuctovani);

    nazev = vyuctovani.getNazev();
    if (vyuctovani.getZuctovaciObdobi() != null) {
      LocalDate zacatekZuctovacihoObdobi = vyuctovani.getZuctovaciObdobi().getZacatek();
      LocalDate konecZuctovacihoObdobi = vyuctovani.getZuctovaciObdobi().getKonec();
      zuctovaciObdobi = "(" + format(zacatekZuctovacihoObdobi, ZUCTOVACI_OBDOBI_DATE_FORMAT) + " - " + format(konecZuctovacihoObdobi, ZUCTOVACI_OBDOBI_DATE_FORMAT) + ")";
    }

    List<VyuctovaniPolozkaEntity> seznamPolozek = vyuctovani.getSeznamPolozek();
    for (VyuctovaniPolozkaEntity vyuctovaniPolozkaEntity : seznamPolozek) {
      RadekTabulkyDto radekTabulkyDto = new RadekTabulkyDto();

      VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity = vyuctovaniPolozkaTypeService.getVyuctovaniPolozkaTypEntity(vyuctovaniPolozkaEntity.getIdPolozkaTyp());
      radekTabulkyDto.setNazev(vyuctovaniPolozkaEntity.getNazev());
      radekTabulkyDto.setVyuctovatJako(vyuctovaniPolozkaTypEntity.getNazev());
      radekTabulkyDto.setPopis("(" + vyuctovaniPolozkaTypEntity.getPopis() + ")");
      VyuctovaniCisloEntity spotreba = vyuctovaniPolozkaEntity.getSpotreba();
      if (spotreba == null) {
        spotreba = new VyuctovaniCisloEntity();
        spotreba.setMnozstvi(1.00);
        spotreba.setJednotka("Ks");
      }
      radekTabulkyDto.setPocatecniStav((vyuctovaniPolozkaEntity.getPocatecniStav() != null) ? vyuctovaniPolozkaEntity.getPocatecniStav().getMnozstvi() : 0.0);
      radekTabulkyDto.setKoncovyStav((vyuctovaniPolozkaEntity.getKoncovyStav() != null) ? vyuctovaniPolozkaEntity.getKoncovyStav().getMnozstvi() : 0.0);
      radekTabulkyDto.setSpotrebaMnozstvi(spotreba.getMnozstvi());
      radekTabulkyDto.setSpotrebaJednotka(spotreba.getJednotka());
      VyuctovaniCisloEntity zalohy = vyuctovaniPolozkaEntity.getZalohy();
      radekTabulkyDto.setZalohy((zalohy != null) ? zalohy.getMnozstvi() : 0.0);
      VyuctovaniCisloEntity naklady = vyuctovaniPolozkaEntity.getNaklady();
      radekTabulkyDto.setNaklady((naklady != null) ? naklady.getMnozstvi() : 0.0);
      radekTabulkyDto.setRozdil(radekTabulkyDto.getZalohy() - radekTabulkyDto.getNaklady());
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
