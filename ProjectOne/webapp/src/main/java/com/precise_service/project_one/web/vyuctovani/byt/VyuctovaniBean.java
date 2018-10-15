package com.precise_service.project_one.web.vyuctovani.byt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.vyuctovani.CisloNaVyuctovaniEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.PolozkaVyuctovaniEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniEntity;
import com.precise_service.project_one.service.IPokusService;
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
  private IPokusService pokusService;

  private String nazev;
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

    List<PolozkaVyuctovaniEntity> seznamPolozek = vyuctovani.getSeznamPolozek();
    for (PolozkaVyuctovaniEntity polozkaVyuctovaniEntity : seznamPolozek) {
      RadekTabulkyDto radekTabulkyDto = new RadekTabulkyDto();
      radekTabulkyDto.setNazev(polozkaVyuctovaniEntity.getNazev());
      CisloNaVyuctovaniEntity spotreba = polozkaVyuctovaniEntity.getSpotreba();
      if (spotreba == null) {
        spotreba = new CisloNaVyuctovaniEntity();
        spotreba.setMnozstvi(1.00);
        spotreba.setJednotka("Ks");
      }
      radekTabulkyDto.setSpotrebaJednotka(spotreba.getJednotka());
      radekTabulkyDto.setPocatecniStav(spotreba.getPocatecniStav());
      radekTabulkyDto.setKoncovyStav(spotreba.getKoncovyStav());
      radekTabulkyDto.setSpotrebaMnozstvi(spotreba.getMnozstvi());
      CisloNaVyuctovaniEntity zalohy = polozkaVyuctovaniEntity.getZalohy();
      radekTabulkyDto.setZalohy((zalohy != null) ? zalohy.getMnozstvi() : 0.0);
      CisloNaVyuctovaniEntity naklady = polozkaVyuctovaniEntity.getNaklady();
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
