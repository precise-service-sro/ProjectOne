package com.precise_service.project_one.web.najemnik.vyuctovani;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniEntity;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniService;
import com.precise_service.project_one.web.byt.vyuctovani.tabulka.VyuctovaniZaBytTabulkaBean;
import com.precise_service.project_one.web.byt.vyuctovani.tabulka.VyuctovaniTabulkaRadkaDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniProNajemnikaBean implements Serializable {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @Autowired
  private VyuctovaniZaBytTabulkaBean vyuctovaniZaBytTabulkaBean;

  public List<String> getIdVyuctovaniList(){
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate from = LocalDate.parse("01-01-2017", dateTimeFormatter);
    LocalDate to = LocalDate.parse("31-12-2017", dateTimeFormatter);

    List<String> idVyuctovaniList = new ArrayList<>();

    List<VyuctovaniEntity> vyuctovaniInRange = vyuctovaniService.getVyuctovaniInRange(from, to);
    for (VyuctovaniEntity vyuctovaniEntity : vyuctovaniInRange) {
      idVyuctovaniList.add(vyuctovaniEntity.getId());
    }
    return idVyuctovaniList;
  }

  public List<VyuctovaniTabulkaRadkaDto> getRadkyVyuctovani() {
    //vyuctovaniZaBytTabulkaBean.prepareData(idVyuctovani);
    return vyuctovaniZaBytTabulkaBean.getRadkyVyuctovani();
  }
}