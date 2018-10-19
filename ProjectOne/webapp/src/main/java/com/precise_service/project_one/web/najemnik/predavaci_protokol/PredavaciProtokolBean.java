package com.precise_service.project_one.web.najemnik.predavaci_protokol;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.precise_service.project_one.entity.byt.predavaci_protokol.PredavaciProtokolEntity;
import com.precise_service.project_one.entity.byt.predavaci_protokol.PredavaciProtokolPolozkaEntity;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.repository.najemnik.predavaci_protokol.PredavaciProtokolEntityRepository;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniPolozkaTypService;
import com.precise_service.project_one.service.najemnik.predavaci_protokol.IPredavaciProtokolService;
import com.precise_service.project_one.web.najemnik.predavaci_protokol.dto.PredavaciProtokolRadkaDto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Named
//@ManagedBean(name="predavaciProtokolBean")
//@ViewScoped
public class PredavaciProtokolBean implements Serializable {

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  @Autowired
  private IVyuctovaniPolozkaTypService vyuctovaniPolozkaTypeService;

  @Autowired
  private PredavaciProtokolEntityRepository predavaciProtokolEntityRepository;

  private String nazev;
  private LocalDate datumPodpisu;

  private PredavaciProtokolEntity predavaciProtokolEntity;

  private List<PredavaciProtokolRadkaDto> radky;
  private List<VyuctovaniPolozkaTypEntity> vyuctovaniPolozkaTypEntityList;

  @PostConstruct
  public void init() {
    List<PredavaciProtokolEntity> predavaciProtokolEntityList = predavaciProtokolService.getPredavaciProtokolEntityList();
    predavaciProtokolEntity = predavaciProtokolEntityList.get(0);

    nazev = predavaciProtokolEntity.getNazev();
    datumPodpisu = predavaciProtokolEntity.getDatumPodpisu();

    radky = new ArrayList<>();
    List<PredavaciProtokolPolozkaEntity> seznamPolozek = predavaciProtokolEntity.getSeznamPolozek();
    for (PredavaciProtokolPolozkaEntity polozka : seznamPolozek) {
      PredavaciProtokolRadkaDto predavaciProtokolRadkaDto = new PredavaciProtokolRadkaDto();
      predavaciProtokolRadkaDto.setIdPredavaciProtokol(predavaciProtokolEntity.getId());
      predavaciProtokolRadkaDto.setNazev(polozka.getNazev());
      predavaciProtokolRadkaDto.setVyuctovaniPolozkaTypEntity(polozka.getVyuctovaniPolozkaTypEntity());
      predavaciProtokolRadkaDto.setCisloMeraku(polozka.getCisloMeraku());
      predavaciProtokolRadkaDto.setJednotka(polozka.getJednotka());
      predavaciProtokolRadkaDto.setStavMeraku(polozka.getStavMeraku());
      radky.add(predavaciProtokolRadkaDto);
    }

    vyuctovaniPolozkaTypEntityList = vyuctovaniPolozkaTypeService.getVyuctovaniPolozkaTypEntityList();
  }

  public void onRowEdit(RowEditEvent event) {
    PredavaciProtokolRadkaDto predavaciProtokolRadkaDto = (PredavaciProtokolRadkaDto) event.getObject();


    PredavaciProtokolEntity predavaciProtokol = predavaciProtokolService.getPredavaciProtokol(predavaciProtokolRadkaDto.getIdPredavaciProtokol());

    // najit polozku
    List<PredavaciProtokolPolozkaEntity> seznamPolozek = predavaciProtokol.getSeznamPolozek();
    for (PredavaciProtokolPolozkaEntity polozkaEntity : seznamPolozek) {
      if (polozkaEntity.getNazev().equals(predavaciProtokolRadkaDto.getNazev())) {
        polozkaEntity.setCisloMeraku(predavaciProtokolRadkaDto.getCisloMeraku());
        polozkaEntity.setStavMeraku(predavaciProtokolRadkaDto.getStavMeraku());
        polozkaEntity.setVyuctovaniPolozkaTypEntity(predavaciProtokolRadkaDto.getVyuctovaniPolozkaTypEntity());
      }
    }

    predavaciProtokolEntityRepository.save(predavaciProtokol);

    FacesMessage msg = new FacesMessage("Uložena úprava řádky", predavaciProtokolRadkaDto.getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowCancel(RowEditEvent event) {
    FacesMessage msg = new FacesMessage("Zrušena úprava řádky", ((PredavaciProtokolRadkaDto) event.getObject()).getNazev());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
