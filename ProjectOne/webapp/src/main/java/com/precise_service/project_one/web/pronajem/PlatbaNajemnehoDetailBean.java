package com.precise_service.project_one.web.pronajem;

import java.io.IOException;
import java.util.List;

import javax.inject.Named;

import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.pronajem.PlatbaNajemneho;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PlatbaNajemnehoDetailBean extends AbstractBean {

  private PlatbaNajemneho platbaNajemneho;
  private List<Osoba> osobaList;

  public void init() {
    log.trace("init()");

    if (platbaNajemneho == null) {
      log.error("Není vybraná žádná platba k zobrazení detailu");
      return;
    }
    osobaList = osobaService.getOsobaAll(loginBean.getPrihlasenyUzivatel().getId());
  }

  public void ulozitZmenuPlatbaNajemneho() throws IOException {
    log.trace("ulozitZmenuPlatbaNajemneho()");
    platbaNajemneho = platbaNajemnehoService.putPlatbaNajemneho(platbaNajemneho);
    showInfoMessage("Uloženo", "Úprava platby nájemného " + platbaNajemneho.getId() + " byla uložena");
    routerBean.goToPlatbaNajemnehoPrehledBean();
  }

  public void zrusitZmenuPlatbaNajemneho() throws IOException {
    log.trace("zrusitZmenuPlatbaNajemneho()");
    showInfoMessage("Zrušeno", "Úprava platby nájemného " + platbaNajemneho.getId() + " byla zrušena");
    routerBean.goToPlatbaNajemnehoPrehledBean();
  }

  public void zduplikovatPlatbaNajemneho() throws IOException {
    log.trace("zduplikovatPlatbaNajemneho()");

    platbaNajemneho.setId(null);
    platbaNajemneho = platbaNajemnehoService.postPlatbaNajemneho(platbaNajemneho);

    showInfoMessage("Zduplikováno", "Platba nájemného byla zduplikována");
    routerBean.goToPlatbaNajemnehoPrehledBean();
  }
}
