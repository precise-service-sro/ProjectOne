package com.precise_service.project_one.web.vyuctovani.byt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

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
public class CelkovyPrehledBean {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @Autowired
  private VyuctovaniBean vyuctovaniBean;

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

  public List<RadekTabulkyDto> getRadkyVyuctovani(String abc) {
    vyuctovaniBean.prepareData(abc);
    return vyuctovaniBean.getRadkyVyuctovani();
  }
}
