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
@RestController
@RequestMapping(value = "/najemnik/predavaciProtokol", path = "/najemnik/predavaciProtokol")
public class PredavaciProtokolEndpoint {

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolEntity postPredavaciProtokol(@RequestBody PredavaciProtokolEntity predavaciProtokolEntity) {
    log.trace("postPredavaciProtokolEntity()");
    return predavaciProtokolService.postPredavaciProtokolEntity(predavaciProtokolEntity);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolEntity putPredavaciProtokol(@RequestBody PredavaciProtokolEntity predavaciProtokolEntity) {
    log.trace("putPredavaciProtokolEntity()");
    return predavaciProtokolService.putPredavaciProtokolEntity(predavaciProtokolEntity);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolEntity getPredavaciProtokol(@PathVariable String id) {
    log.trace("getPredavaciProtokolEntity()" + id);
    return predavaciProtokolService.getPredavaciProtokolEntity(id);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<PredavaciProtokolEntity> getPredavaciProtokolAll() {
    log.trace("getPredavaciProtokolEntityAll()");
    return predavaciProtokolService.getPredavaciProtokolEntityAll();
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deletePredavaciProtokolAll() {
    log.trace("deletePredavaciProtokolEntityAll()");
    predavaciProtokolService.deletePredavaciProtokolEntityAll();
  }
}
