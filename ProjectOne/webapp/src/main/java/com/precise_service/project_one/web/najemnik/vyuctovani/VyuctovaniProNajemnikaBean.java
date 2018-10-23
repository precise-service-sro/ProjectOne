package com.precise_service.project_one.web.najemnik.vyuctovani;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika.VyuctovaniProNajemnikaEntity;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniZaBytService;
import com.precise_service.project_one.service.najemnik.vyuctovani_pro_najemnika.IVyuctovaniProNajemnikaService;
import com.precise_service.project_one.web.byt.vyuctovani.tabulka.VyuctovaniTabulkaRadkaDto;
import com.precise_service.project_one.web.byt.vyuctovani.tabulka.VyuctovaniZaBytTabulkaBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniProNajemnikaBean implements Serializable {

  @Autowired
  private IVyuctovaniProNajemnikaService vyuctovaniProNajemnikaService;

  @Autowired
  private VyuctovaniZaBytTabulkaBean vyuctovaniZaBytTabulkaBean;

  public List<String> getIdVyuctovaniList(){
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate from = LocalDate.parse("01-01-2017", dateTimeFormatter);
    LocalDate to = LocalDate.parse("31-12-2017", dateTimeFormatter);

    List<String> idVyuctovaniProNajemnikaList = new ArrayList<>();

    List<VyuctovaniProNajemnikaEntity> vyuctovaniInRange = vyuctovaniProNajemnikaService.getVyuctovaniProNajemnikaEntityAll();
    for (VyuctovaniProNajemnikaEntity vyuctovaniEntity : vyuctovaniInRange) {
      idVyuctovaniProNajemnikaList.add(vyuctovaniEntity.getId());
    }
    return idVyuctovaniProNajemnikaList;
  }

  public List<VyuctovaniTabulkaRadkaDto> getRadkyVyuctovani() {
    //vyuctovaniZaBytTabulkaBean.prepareData(idVyuctovani);
    return vyuctovaniZaBytTabulkaBean.getRadkyVyuctovani();
  }
}
