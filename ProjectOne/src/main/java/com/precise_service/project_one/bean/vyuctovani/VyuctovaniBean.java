package com.precise_service.project_one.bean.vyuctovani;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.service.IVyuctovaniService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniBean {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  private String nazev;
  private List<PolozkaVyuctovaniBean> seznamPolozek;

  public String getNazev() {
    return nazev;
  }

  public List<PolozkaVyuctovaniBean> getSeznamPolozek(){
    log.trace("getSeznamPolozek()");
    //List<Vyuctovani> vyuctovaniList = vyuctovaniService.getSeznamPolozek();

    List<PolozkaVyuctovaniBean> polozkaVyuctovaniBeans = new ArrayList<>();
    polozkaVyuctovaniBeans.add(new PolozkaVyuctovaniBean("Nazev", new CisloNaVyuctovaniBean(123.45, "Kč"), new CisloNaVyuctovaniBean(123.45, "Kč"), new CisloNaVyuctovaniBean(123.45, "Kč")));
    //for (Vyuctovani vyuctovani : vyuctovaniList) {

    //}

    //List<PolozkaVyuctovani> seznamPolozek = vyuctovaniList.get(0).getSeznamPolozek();
    return polozkaVyuctovaniBeans;
  }

  public double getSeznamPolozek2(){
    return 1111.00;
  }

  public double getCelkemZalohy(){
    return 2222.00;
  }

  public double getCelkemNaklady(){
    return 3333.00;
  }

  public double getCelkemRozdil(){
    return 4444.00;
  }
}
