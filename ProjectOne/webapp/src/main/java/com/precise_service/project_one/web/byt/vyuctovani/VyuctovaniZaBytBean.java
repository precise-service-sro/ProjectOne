package com.precise_service.project_one.web.byt.vyuctovani;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaByt;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytPolozka;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniZaBytService;
import com.precise_service.project_one.web.byt.vyuctovani.tabulka.VyuctovaniZaBytTabulkaBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniZaBytBean implements Serializable {

  @Autowired
  private IVyuctovaniZaBytService vyuctovaniZaBytService;

  @Autowired
  private VyuctovaniZaBytTabulkaBean vyuctovaniZaBytTabulkaBean;

  public List<String> getIdVyuctovaniList(){
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate from = LocalDate.parse("01-01-2017", dateTimeFormatter);
    LocalDate to = LocalDate.parse("31-12-2017", dateTimeFormatter);

    List<String> idVyuctovaniZaBytList = new ArrayList<>();

    List<VyuctovaniZaByt> vyuctovaniInRange = vyuctovaniZaBytService.getVyuctovaniZaBytInRange(from, to);
    for (VyuctovaniZaByt vyuctovaniZaByt : vyuctovaniInRange) {
      idVyuctovaniZaBytList.add(vyuctovaniZaByt.getId());
    }
    return idVyuctovaniZaBytList;
  }

  public List<VyuctovaniZaBytPolozka> getRadkyVyuctovani() {
    String idVyuctovaniZaByt = getIdVyuctovaniList().get(0);
    vyuctovaniZaBytTabulkaBean.prepareData(idVyuctovaniZaByt);
    return vyuctovaniZaBytTabulkaBean.getRadkyVyuctovani();
  }
}
