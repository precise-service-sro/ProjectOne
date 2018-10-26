package com.precise_service.project_one.web.byt.vyuctovani;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.informace.Byt;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniCislo;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTyp;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaByt;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytPolozka;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZuctovaciObdobi;
import com.precise_service.project_one.service.byt.informace.IBytService;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniZaBytService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniZaBytPrehledBean implements Serializable {

  @Autowired
  private IVyuctovaniZaBytService vyuctovaniZaBytService;

  @Autowired
  private IBytService bytService;

  @Autowired
  private VyuctovaniZaBytDetailBean vyuctovaniZaBytDetailBean;

  public static final String ZUCTOVACI_OBDOBI_DATE_FORMAT = "dd/MM/yyyy";

  private String zuctovaciObdobiFilter;
  private List<VyuctovaniZaByt> vyuctovaniZaBytList;
  private List<Byt> bytList;

  @PostConstruct
  public void init() {
    // getVyuctovaniZaBytInRange
    /*
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate from = LocalDate.parse("01-01-2017", dateTimeFormatter);
    LocalDate to = LocalDate.parse("31-12-2017", dateTimeFormatter);
    vyuctovaniZaBytList = vyuctovaniZaBytService.getVyuctovaniZaBytInRange(from, to);
    */

    // getVyuctovaniZaBytAll
    vyuctovaniZaBytList = vyuctovaniZaBytService.getVyuctovaniZaBytAll();

    bytList = bytService.getBytAll();
  }

  public void onRowEdit(RowEditEvent event) {
    log.trace("onRowEdit()");
    VyuctovaniZaByt vyuctovaniZaByt = (VyuctovaniZaByt) event.getObject();

    vyuctovaniZaBytService.putVyuctovaniZaByt(vyuctovaniZaByt);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", vyuctovaniZaByt.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    log.trace("onRowCancel()");
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((VyuctovaniZaByt) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void showVyuctovaniZaBytDetailBean(VyuctovaniZaByt vyuctovaniZaByt) throws IOException {
    vyuctovaniZaBytDetailBean.setVyuctovaniZaByt(vyuctovaniZaByt);
    Faces.getFlash().setRedirect(true);
    Faces.redirect("/byt/vyuctovani/detail.xhtml");
  }

  public void addRow() {
    log.trace("addRow()");

    VyuctovaniZaByt vyuctovaniZaByt = new VyuctovaniZaByt();

    vyuctovaniZaByt.setNazev("!!! Upravit název !!!");
    vyuctovaniZaByt.setByt(null);
    vyuctovaniZaByt.setZuctovaciObdobi(new VyuctovaniZuctovaciObdobi());

    VyuctovaniZaByt saved = vyuctovaniZaBytService.postVyuctovaniZaByt(vyuctovaniZaByt);
    init();

    FacesMessage msg = new FacesMessage("Přidána nová řádka", saved.getId());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void deleteRow(VyuctovaniZaByt deletedVyuctovaniZaByt) {
    log.trace("deleteRow()");

    if (deletedVyuctovaniZaByt == null) {
      log.trace("deleted row is null");
      return;
    }
    log.trace("deleting row with: " + deletedVyuctovaniZaByt.toString());

    vyuctovaniZaBytService.deleteVyuctovaniZaByt(deletedVyuctovaniZaByt.getId());

    FacesMessage msg = new FacesMessage("Smazán řádek", deletedVyuctovaniZaByt.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
    init();
  }
}
