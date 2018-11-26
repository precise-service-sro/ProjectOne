package com.precise_service.project_one.web.pronajem;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.entity.pronajem.Vyuctovani;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniPrehledBean extends AbstractBean {

  private CasovyInterval zuctovaciObdobi;
  private List<Vyuctovani> vyuctovaniList;
  private List<Vyuctovani> filtrovanyVyuctovaniList;
  private List<Nemovitost> nemovitostList;
  private int vyuctovaniListSize;
  private int filtrovanyVyuctovaniListSize;

  public void init() {

    if (zuctovaciObdobi == null) {
      zuctovaciObdobi = new CasovyInterval();
      zuctovaciObdobi.setZacatek(dateFormatterBean.parseDate("01-01-2017"));
      zuctovaciObdobi.setKonec(dateFormatterBean.parseDate("31-12-2017"));
    }

    // getVyuctovaniInRange
    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    vyuctovaniList = vyuctovaniService.getVyuctovaniListInRange(prihlasenyUzivatel);

    nemovitostList = nemovitostService.getNemovitostAll(prihlasenyUzivatel.getId());
  }

  public void pridatVyuctovani() {
    log.trace("pridatVyuctovani()");

    Vyuctovani vyuctovani = new Vyuctovani();

    vyuctovani.setNazev("Manuáně vytvořené vyúčtování ze dne: " + LocalDateTime.now().toString());
    vyuctovani.setZuctovaciObdobi(new CasovyInterval());
    vyuctovani.setDatumVystaveni(new Date());
    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    vyuctovani.setIdOsoba(prihlasenyUzivatel.getId());

    List<Nemovitost> nemovitostiPrihlasenehoUzivatele = nemovitostService.getNemovitostAll(prihlasenyUzivatel.getId());
    if (nemovitostiPrihlasenehoUzivatele.isEmpty()) {
      showInfoMessage("Chyba", "Není povoleno vytvářet vyúčtování bez existující nemovitosti !!!");
      return;
    }
    vyuctovani.setNemovitost(nemovitostiPrihlasenehoUzivatele.get(0));

    Vyuctovani saved = vyuctovaniService.postVyuctovani(vyuctovani);
    showInfoMessage("Přidáno", "Přidáno nové vyúčtování (" + saved.getId() + ")");
    init();
  }

  public void smazatVyuctovani(Vyuctovani deletedVyuctovani) {
    log.trace("smazatVyuctovani()");

    if (deletedVyuctovani == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedVyuctovani.toString());

    vyuctovaniService.deleteVyuctovani(deletedVyuctovani.getId());
    vyuctovaniPolozkaService.deleteVyuctovaniPolozkaAll(deletedVyuctovani.getId());
    showInfoMessage("Smazáno", "Vyúčtování " + deletedVyuctovani.getId() + " bylo smazáno včetně všech jeho položek");
    init();
  }

  public void zuctovaciObdobiZacatekDateSelect(SelectEvent event) {
    showInfoMessage("Zvolen nový začátek zúčtovacího období", dateFormatterBean.formatDate((Date) event.getObject()));
    init();
  }

  public void zuctovaciObdobiKonecDateSelect(SelectEvent event) {
    showInfoMessage("Zvolen nový konec zúčtovacího období", dateFormatterBean.formatDate((Date) event.getObject()));
    init();
  }

  public int getVyuctovaniListSize() {
    if (vyuctovaniList == null) {
      return 0;
    }
    return vyuctovaniList.size();
  }

  public int getFiltrovanyVyuctovaniListSize() {
    if (filtrovanyVyuctovaniList == null) {
      return getVyuctovaniListSize();
    }
    return filtrovanyVyuctovaniList.size();
  }
}
