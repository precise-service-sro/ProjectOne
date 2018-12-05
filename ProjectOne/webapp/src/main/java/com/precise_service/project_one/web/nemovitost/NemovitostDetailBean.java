package com.precise_service.project_one.web.nemovitost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;

import com.precise_service.project_one.entity.adresa.Stat;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.nemovitost.NemovitostDispozice;
import com.precise_service.project_one.entity.nemovitost.NemovitostDruhVlastnictvi;
import com.precise_service.project_one.entity.nemovitost.NemovitostTyp;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NemovitostDetailBean extends AbstractBean {

  private Nemovitost nemovitost;
  private List<Osoba> osobaList;

  private List<Nemovitost> nemovitostList;
  private List<NemovitostTyp> nemovitostTypList;
  private List<NemovitostDruhVlastnictvi> nemovitostDruhVlastnictviList;
  private List<NemovitostDispozice> nemovitostDispoziceList;
  private List<Stat> statList;

  // seznam zobrazovanych obrazku
  private List<String> images;
  private StreamedContent zobrazitObrazek;

  public StreamedContent zobrazitObrazek(Object id) {
    return osobaDetailBean.getAvatarFotoStreamedContent();
  }

  public List<String> getImages() {
    if (images == null) {
      images = new ArrayList<>();
      for (int i = 1; i < 5; i++) {
        images.add("nature" + i + ".jpg");
      }
    }
    return images;
  }

  public void init() {


    // pokud nemam vybranou zadnou nemovitost, tak vytahuji prvni nemovitost z DB
    if (nemovitost == null) {
      log.error("Není vybrána žádná nemovitosti pro zobrazení detailu");
      return;
    }

    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    osobaList = osobaService.getOsobaAll(prihlasenyUzivatel.getId());
    nemovitostList = nemovitostService.getNemovitostListByVlastnik(prihlasenyUzivatel.getId());

    nemovitostTypList = Arrays.asList(NemovitostTyp.values());
    statList = Arrays.asList(Stat.values());
    nemovitostDruhVlastnictviList = Arrays.asList(NemovitostDruhVlastnictvi.values());
    nemovitostDispoziceList = Arrays.asList(NemovitostDispozice.values());
    nemovitostDispoziceList = Arrays.asList(NemovitostDispozice.values());
  }

  public void pridatNemovitost() throws IOException {
    log.trace("pridatNemovitost()");
    showInfoMessage("Přidáno", "Nová nemovitost " + nemovitost.getNazev() + " byla přidáná");
    // TODO: ulozit novou nemovitost do DB
    routerBean.goToNemovitostPrehledBean();
  }

  public void ulozitZmenuNemovitosti() throws IOException {
    log.trace("ulozitZmenuNemovitosti()");
    nemovitost = nemovitostService.putNemovitost(nemovitost);
    showInfoMessage("Uloženo", "Úprava nemovitosti " + nemovitost.getNazev() + " byla uložena");
    routerBean.goToNemovitostPrehledBean();
  }

  public void zrusitZmenuNemovitosti() throws IOException {
    log.trace("zrusitZmenuNemovitosti()");
    showInfoMessage("Zrušeno", "Přidání/úprava nemovitosti byla zrušena");
    routerBean.goToNemovitostPrehledBean();
  }

  public void zmenaVybraneNemovitosti(final AjaxBehaviorEvent event) {
    showInfoMessage("Změněno", "Byla vybráná nemovitost " + nemovitost.getIdentifikaceNemovitosti());
  }
}
