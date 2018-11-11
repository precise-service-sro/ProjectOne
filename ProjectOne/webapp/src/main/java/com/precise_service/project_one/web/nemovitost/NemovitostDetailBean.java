package com.precise_service.project_one.web.nemovitost;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostDispozice;
import com.precise_service.project_one.entity.nemovitost.NemovitostDruhVlastnictvi;
import com.precise_service.project_one.entity.nemovitost.NemovitostTyp;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;
import com.precise_service.project_one.web.login.Util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NemovitostDetailBean extends AbstractBean {

  private Nemovitost nemovitost;
  private List<Osoba> osobaList;

  private List<NemovitostTyp> nemovitostTypList;
  private List<NemovitostDruhVlastnictvi> nemovitostDruhVlastnictviList;
  private List<NemovitostDispozice> nemovitostDispoziceList;

  public void init() {
    // pokud nemam vybranou zadnou nemovitost, tak vytahuji prvni nemovitost z DB
    if (nemovitost == null) {
      log.error("Není vybrána žádná nemovitosti pro zobrazení detailu");
      return;
    }

    Osoba prihlasenyUzivatel = Util.getPrihlasenyUzivatel();
    osobaList = osobaService.getOsobaAll(prihlasenyUzivatel.getId());

    nemovitostTypList = Arrays.asList(NemovitostTyp.values());
    nemovitostDruhVlastnictviList = Arrays.asList(NemovitostDruhVlastnictvi.values());
    nemovitostDispoziceList = Arrays.asList(NemovitostDispozice.values());
  }

  public void ulozitPoznamky(){
    log.warn("ulozitPoznamky()");
    String newText = editorTextuBean.getText();
    nemovitost.setPoznamky(newText);
    nemovitostService.postNemovitost(nemovitost);

    FacesMessage msg = new FacesMessage("Úprava uložena", newText);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void pridatNemovitost() throws IOException {
    log.trace("pridatNemovitost()");
    showMessage(FacesMessage.SEVERITY_INFO,"Přidáno", "Nová nemovitost " + nemovitost.getNazev() + " byla přidáná");
    // TODO: ulozit novou nemovitost do DB
    routerBean.goToNemovitostPrehledBean();
  }

  public void ulozitZmenuNemovitosti() throws IOException {
    log.trace("ulozitZmenuNemovitosti()");
    nemovitost = nemovitostService.putNemovitost(nemovitost);
    showMessage(FacesMessage.SEVERITY_INFO,"Uloženo", "Úprava nemovitosti " + nemovitost.getNazev() + " byla uložena");
    routerBean.goToNemovitostPrehledBean();
  }

  public void zrusitZmenuNemovitosti() throws IOException {
    log.trace("zrusitZmenuNemovitosti()");
    showMessage(FacesMessage.SEVERITY_INFO,"Zrušeno", "Přidání/úprava nemovitosti byla zrušena");
    routerBean.goToNemovitostPrehledBean();
  }
}
