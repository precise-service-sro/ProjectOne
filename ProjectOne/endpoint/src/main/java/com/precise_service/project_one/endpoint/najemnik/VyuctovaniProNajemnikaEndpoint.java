package com.precise_service.project_one.endpoint.najemnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolEntity;
import com.precise_service.project_one.service.najemnik.predavaci_protokol.IPredavaciProtokolService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@RestController
public class VyuctovaniProNajemnikaEndpoint {

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  @RequestMapping(value = "/predavaciProtokol", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolEntity postPredavaciProtokol(@RequestBody PredavaciProtokolEntity predavaciProtokolEntity) {
    log.trace("postPredavaciProtokol()");
    return predavaciProtokolService.postPredavaciProtokol(predavaciProtokolEntity);
  }

  @RequestMapping(value = "/predavaciProtokolNew", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolEntity postPredavaciProtokolNew() {
    log.trace("postPredavaciProtokolNew()");
    return predavaciProtokolService.postPredavaciProtokolNew();
  }

  @RequestMapping(value = "/predavaciProtokol/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolEntity getPredavaciProtokol(@PathVariable String id) {
    log.trace("getPredavaciProtokol()" + id);
    return predavaciProtokolService.getPredavaciProtokol(id);
  }

  @RequestMapping(value = "/predavaciProtokol/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<PredavaciProtokolEntity> getPredavaciProtokolAll() {
    log.trace("getPredavaciProtokolAll()");
    return predavaciProtokolService.getPredavaciProtokolAll();
  }

  /*
  @RequestMapping(value = "/vyuctovani/!inRange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  List<VyuctovaniEntity> getVyuctovaniInRange(@RequestBody RangeRequest request ) {
    log.trace("getVyuctovaniInRange()" + request.getFrom().toString() + " - " + request.getTo().toString());
    return vyuctovaniService.getVyuctovaniInRange(request.getFrom(), request.getTo());
  }
  */

  @RequestMapping(value = "/predavaciProtokol/!all", method = RequestMethod.DELETE)
  void deletePredavaciProtokolAll() {
    log.trace("deletePredavaciProtokolAll()");
    predavaciProtokolService.deletePredavaciProtokolAll();
  }
}
