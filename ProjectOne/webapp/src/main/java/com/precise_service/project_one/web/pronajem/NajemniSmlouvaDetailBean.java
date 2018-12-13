package com.precise_service.project_one.web.pronajem;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import org.primefaces.event.TabChangeEvent;

import com.precise_service.project_one.entity.filter.NajemniSmlouvaFilter;
import com.precise_service.project_one.entity.filter.NemovitostFilter;
import com.precise_service.project_one.entity.filter.OsobaFilter;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.pronajem.DokumentTyp;
import com.precise_service.project_one.entity.pronajem.NajemniSmlouva;
import com.precise_service.project_one.entity.pronajem.PredavaciProtokol;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class NajemniSmlouvaDetailBean extends AbstractBean {

  private NajemniSmlouva najemniSmlouva;
  private List<NajemniSmlouva> najemniSmlouvaList;

  private List<Nemovitost> nemovitostList;
  private List<Osoba> osobaList;
  private List<PredavaciProtokol> predavaciProtokolList;
  private List<DokumentTyp> dokumentTypList;

  public void init() {
    log.trace("init()");

    if (najemniSmlouva == null) {
      log.error("Není vybraná žádná nájemní smlouva k zobrazení detailu");
      return;
    }

    String idPrihlasenyUzivatel = loginBean.getPrihlasenyUzivatel().getId();
    najemniSmlouvaList = najemniSmlouvaService.getNajemniSmlouvaList(new NajemniSmlouvaFilter()
        .setIdPrihlasenyUzivatel(idPrihlasenyUzivatel)
    );
    nemovitostList = nemovitostService.getNemovitostList(new NemovitostFilter()
        .setIdOsobaVlastnika(idPrihlasenyUzivatel)
    );
    osobaList = osobaService.getOsobaList(new OsobaFilter()
        .setIdPrihlasenyUzivatel(idPrihlasenyUzivatel)
    );
    predavaciProtokolList = predavaciProtokolService.getPredavaciProtokolAll(idPrihlasenyUzivatel);
    dokumentTypList = Arrays.asList(DokumentTyp.values());
  }

  public void ulozitZmenuNajemniSmlouvy() throws IOException {
    log.trace("ulozitZmenuNajemniSmlouva()");
    najemniSmlouva = najemniSmlouvaService.putNajemniSmlouva(najemniSmlouva);
    showInfoMessage("Uloženo", "Úprava najemní smlouvy byla uložena");
    routerBean.goToNajemniSmlouvaPrehledBean();
  }

  public void zrusitZmenuNajemniSmlouvy() throws IOException {
    log.trace("zrusitZmenuNajemniSmlouva()");
    showInfoMessage("Zrušeno", "Úprava najemní smlouvy byla zrušena");
    routerBean.goToNajemniSmlouvaPrehledBean();
  }

  public void tabChanged(TabChangeEvent event) {
    log.trace("tabChanged()");
  }

  public void zmenaVybraneNajemniSmlouvy(final AjaxBehaviorEvent event) {
    showInfoMessage("Změněno", "Byla vybráná nájemní smlouva" + najemniSmlouva.getIdentifikace());
  }
}
