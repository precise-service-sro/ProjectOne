package com.precise_service.project_one.web.najemnik.vyuctovani;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytPolozka;
import com.precise_service.project_one.entity.najemnik.vyuctovani_pro_najemnika.VyuctovaniProNajemnika;
import com.precise_service.project_one.service.najemnik.vyuctovani_pro_najemnika.IVyuctovaniProNajemnikaService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniProNajemnikaBean implements Serializable {

  @Autowired
  private IVyuctovaniProNajemnikaService vyuctovaniProNajemnikaService;

  public List<String> getIdVyuctovaniList(){
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate from = LocalDate.parse("01-01-2017", dateTimeFormatter);
    LocalDate to = LocalDate.parse("31-12-2017", dateTimeFormatter);

    List<String> idVyuctovaniProNajemnikaList = new ArrayList<>();

    List<VyuctovaniProNajemnika> vyuctovaniInRange = vyuctovaniProNajemnikaService.getVyuctovaniProNajemnikaAll();
    for (VyuctovaniProNajemnika vyuctovani : vyuctovaniInRange) {
      idVyuctovaniProNajemnikaList.add(vyuctovani.getId());
    }
    return idVyuctovaniProNajemnikaList;
  }

  public List<VyuctovaniZaBytPolozka> getRadkyVyuctovani() {
    //vyuctovaniZaBytTabulkaBean.prepareData(idVyuctovani);
    return null; //vyuctovaniZaBytTabulkaBean.getRadkyVyuctovani();
  }
}
