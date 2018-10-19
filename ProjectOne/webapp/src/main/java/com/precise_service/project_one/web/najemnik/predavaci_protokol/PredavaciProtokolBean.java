package com.precise_service.project_one.web.najemnik.predavaci_protokol;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.predavaciProtokol.PredavaciProtokolEntity;
import com.precise_service.project_one.entity.byt.predavaciProtokol.PredavaciProtokolPolozkaEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.service.IPredavaciProtokolService;
import com.precise_service.project_one.service.IVyuctovaniPolozkaTypeService;
import com.precise_service.project_one.web.Car;
import com.precise_service.project_one.web.najemnik.predavaci_protokol.dto.PredavaciProtokolRadkaDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
public class PredavaciProtokolBean implements Serializable {

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  @Autowired
  private IVyuctovaniPolozkaTypeService vyuctovaniPolozkaTypeService;

  private String nazev;
  private LocalDate datumPodpisu;
  private List<PredavaciProtokolRadkaDto> radky;
  private Double celkemZalohy;
  private Double celkemNaklady;
  private Double celkemRozdil;

  private PredavaciProtokolEntity predavaciProtokolEntity;

  @PostConstruct
  public void init() {
    List<PredavaciProtokolEntity> predavaciProtokolEntityList = predavaciProtokolService.getPredavaciProtokolEntityList();
    predavaciProtokolEntity = predavaciProtokolEntityList.get(0);

    nazev = predavaciProtokolEntity.getNazev();
    datumPodpisu = predavaciProtokolEntity.getDatumPodpisu();
  }

  public List<PredavaciProtokolRadkaDto> getRadky(){
    log.trace("getRadky()");
    radky = new ArrayList<>();
    List<PredavaciProtokolPolozkaEntity> seznamPolozek = predavaciProtokolEntity.getSeznamPolozek();
    for (PredavaciProtokolPolozkaEntity polozka : seznamPolozek) {
      PredavaciProtokolRadkaDto radka = new PredavaciProtokolRadkaDto();
      radka.setNazev(polozka.getNazev());
      VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity = vyuctovaniPolozkaTypeService.getVyuctovaniPolozkaTypEntity(polozka.getIdPolozkaTyp());
      radka.setVyuctovatJako(vyuctovaniPolozkaTypEntity.getNazev());
      radka.setPopis(vyuctovaniPolozkaTypEntity.getPopis());
      radka.setCisloMeraku(polozka.getCisloMeraku());
      radka.setStavMeraku(polozka.getStavMeraku());
      radka.setJednotka(polozka.getJednotka());
      radka.setFormatovanyStavMeraku(polozka.getStavMeraku() + " " + polozka.getJednotka());
      radky.add(radka);
    }
    return radky;
  }

  public List<VyuctovaniPolozkaTypEntity> getVyuctovatJakoList() {
    List<VyuctovaniPolozkaTypEntity> vyuctovaniPolozkaTypEntityList = vyuctovaniPolozkaTypeService.getVyuctovaniPolozkaTypEntityList();
    return vyuctovaniPolozkaTypEntityList;
  }

  public void onRowEdit(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Uložena úprava řádky", ((PredavaciProtokolRadkaDto) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((PredavaciProtokolRadkaDto) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
