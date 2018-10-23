package com.precise_service.project_one.endpoint.najemnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozkaEntity;
import com.precise_service.project_one.service.najemnik.predavaci_protokol.IPredavaciProtokolPolozkaService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/najemnik/{idNajemnikEntity}/predavaciProtokol/{idPredavaciProtokol}/polozka", path = "/najemnik/{idNajemnikEntity}/predavaciProtokol/{idPredavaciProtokol}/polozka")
public class PredavaciProtokolPolozkaEndpoint {

  @Autowired
  private IPredavaciProtokolPolozkaService predavaciProtokolPolozkaService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolPolozkaEntity postPredavaciProtokolPolozkaEntity(@PathVariable String idNajemnikEntity, @PathVariable String idPredavaciProtokol, @RequestBody PredavaciProtokolPolozkaEntity predavaciProtokolPolozkaEntity) {
    log.trace("postPredavaciProtokolPolozkaEntity()");
    return predavaciProtokolPolozkaService.postPredavaciProtokolPolozkaEntity(predavaciProtokolPolozkaEntity);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolPolozkaEntity putPredavaciProtokolPolozkaEntity(@PathVariable String idNajemnikEntity, @PathVariable String idPredavaciProtokol, @RequestBody PredavaciProtokolPolozkaEntity predavaciProtokolPolozkaEntity) {
    log.trace("putPredavaciProtokolPolozkaEntity()");
    return predavaciProtokolPolozkaService.putPredavaciProtokolPolozkaEntity(predavaciProtokolPolozkaEntity);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolPolozkaEntity getPredavaciProtokolPolozkaEntity(@PathVariable String idPredavaciProtokolPolozkaEntity) {
    log.trace("getPredavaciProtokolPolozkaEntity()" + idPredavaciProtokolPolozkaEntity);
    return predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaEntity(idPredavaciProtokolPolozkaEntity);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<PredavaciProtokolPolozkaEntity> getPredavaciProtokolPolozkaEntityAll(@PathVariable String idNajemnikEntity, @PathVariable String idPredavaciProtokol) {
    log.trace("getPredavaciProtokolPolozkaEntityAll()");
    return predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaEntityAll();
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deletePredavaciProtokolPolozkaEntityAll(@PathVariable String idNajemnikEntity, @PathVariable String idPredavaciProtokol) {
    log.trace("deletePredavaciProtokolPolozkaEntityAll()");
    predavaciProtokolPolozkaService.deletePredavaciProtokolPolozkaEntityAll();
  }
}
