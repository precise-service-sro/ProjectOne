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

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaByt;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniZaBytService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class VyuctovaniZaBytPrehledBean implements Serializable {

  @Autowired
  private IVyuctovaniZaBytService vyuctovaniZaBytService;

  @Autowired VyuctovaniZaBytDetailBean vyuctovaniZaBytDetailBean;

  public static final String ZUCTOVACI_OBDOBI_DATE_FORMAT = "dd/MM/yyyy";

  private String zuctovaciObdobiFilter;
  private List<VyuctovaniZaByt> vyuctovaniZaBytList;

  @PostConstruct
  public void init() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate from = LocalDate.parse("01-01-2017", dateTimeFormatter);
    LocalDate to = LocalDate.parse("31-12-2017", dateTimeFormatter);
    vyuctovaniZaBytList = vyuctovaniZaBytService.getVyuctovaniZaBytInRange(from, to);
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

    //Faces.setFlashAttribute("idVyuctovaniZaByt", idVyuctovaniZaByt);
    Faces.getFlash().setRedirect(true);
    Faces.redirect("/byt/vyuctovani/detail.xhtml");
  }
}
