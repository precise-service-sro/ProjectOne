package com.precise_service.project_one.web.faktura;

import java.util.List;

import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.util.CollectionUtils;

import com.precise_service.project_one.entity.CasovyInterval;
import com.precise_service.project_one.entity.Cislo;
import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.entity.faktura.FakturaPolozka;
import com.precise_service.project_one.entity.filter.FakturaFilter;
import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.web.AbstractBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class FakturaPrehledBean extends AbstractBean {

  private CasovyInterval zuctovaciObdobi;
  private List<Faktura> fakturaList;
  private List<Faktura> filtrovanyFakturaList;
  private int fakturaListSize;
  private int filtrovanyFakturaListSize;

  public void init() {
    zuctovaciObdobi = new CasovyInterval();
    Osoba prihlasenyUzivatel = loginBean.getPrihlasenyUzivatel();
    fakturaList = fakturaService.getFakturaList(new FakturaFilter()
        .setIdPrihlasenyUzivatel(prihlasenyUzivatel.getId())
    );
    filtrovanyFakturaList = null;
  }

  public void initPodleNemovitosti(Nemovitost nemovitost) {
    zuctovaciObdobi = new CasovyInterval();
    fakturaList = fakturaService.getFakturaList(new FakturaFilter()
        .setIdNemovitost(nemovitost.getId())
    );
    filtrovanyFakturaList = null;
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    Faktura faktura = (Faktura) event.getObject();

    fakturaService.putFaktura(faktura);

    showInfoMessage("Uložena úprava řádky", faktura.getDodavatel());
    init();
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    showInfoMessage("Zrušena úprava řádky", ((Faktura) event.getObject()).getDodavatel());
  }

  public void pridatFaktura() {
    log.trace("pridatFaktura()");

    Faktura faktura = new Faktura();

    faktura.setDodavatel(null);
    faktura.setNemovitost(null);
    faktura.setZuctovaciObdobi(new CasovyInterval());
    faktura.setIdOsoba(loginBean.getPrihlasenyUzivatel().getId());

    fakturaService.postFaktura(faktura);
    showInfoMessage("Přidáno", "Přidána nová faktura");

    init();
  }

  public void smazatFaktura(Faktura deletedFaktura) {
    log.trace("smazatFaktura()");

    if (deletedFaktura == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedFaktura.toString());

    fakturaService.deleteFaktura(deletedFaktura.getId());

    showInfoMessage("Smazáno", "Faktura smazána");
    init();
  }

  public void zuctovaciObdobiZacatekDateSelect() {
    log.trace("zuctovaciObdobiZacatekDateSelect()");
    showInfoMessage("Zvolen nový začátek zúčtovacího období", dateFormatterBean.formatDate(zuctovaciObdobi.getZacatek()));
    init();
  }

  public void zuctovaciObdobiKonecDateSelect() {
    log.trace("zuctovaciObdobiKonecDateSelect()");
    showInfoMessage("Zvolen nový konec zúčtovacího období", dateFormatterBean.formatDate(zuctovaciObdobi.getKonec()));
    init();
  }

  public int getFakturaListSize() {
    if (fakturaList == null) {
      return 0;
    }
    return fakturaList.size();
  }

  public int getFiltrovanyFakturaListSize() {
    if (filtrovanyFakturaList == null) {
      return getFakturaListSize();
    }
    return filtrovanyFakturaList.size();
  }

  public Cislo spocitatCelkoveNakladyFaktury(String idFaktura) {
    Cislo celkoveNaklady = new Cislo();
    celkoveNaklady.setJednotka("Kč");
    List<FakturaPolozka> fakturaPolozkaAll = fakturaPolozkaService.getFakturaPolozkaAll(idFaktura);
    if (!CollectionUtils.isEmpty(fakturaPolozkaAll)) {
      for (FakturaPolozka fakturaPolozka : fakturaPolozkaAll) {
        celkoveNaklady.setMnozstvi(celkoveNaklady.getMnozstvi() + fakturaPolozka.getNaklady().getMnozstvi());
        celkoveNaklady.setJednotka(fakturaPolozka.getNaklady().getJednotka());
      }
    }
    return celkoveNaklady;
  }
}
