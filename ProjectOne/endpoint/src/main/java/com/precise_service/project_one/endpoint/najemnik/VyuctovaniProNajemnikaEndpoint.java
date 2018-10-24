package com.precise_service.project_one.endpoint.najemnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokol;
import com.precise_service.project_one.service.najemnik.predavaci_protokol.IPredavaciProtokolService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/najemnik/vyuctovani", path = "/najemnik/vyuctovani")
public class VyuctovaniProNajemnikaEndpoint {

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokol postPredavaciProtokol(@RequestBody PredavaciProtokol predavaciProtokol) {
    log.trace("postPredavaciProtokol()");
    return predavaciProtokolService.postPredavaciProtokol(predavaciProtokol);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokol getPredavaciProtokol(@PathVariable String id) {
    log.trace("getPredavaciProtokol()" + id);
    return predavaciProtokolService.getPredavaciProtokol(id);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<PredavaciProtokol> getPredavaciProtokolAll() {
    log.trace("getPredavaciProtokolAll()");
    return predavaciProtokolService.getPredavaciProtokolAll();
  }

  /*
  @RequestMapping(value = "/vyuctovani/!inRange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Vyuctovani> getVyuctovaniInRange(@RequestBody RangeRequest request ) {
    log.trace("getVyuctovaniInRange()" + request.getFrom().toString() + " - " + request.getTo().toString());
    return vyuctovaniZaBytService.getVyuctovaniInRange(request.getFrom(), request.getTo());
  }
  */

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deletePredavaciProtokolAll() {
    log.trace("deletePredavaciProtokolAll()");
    predavaciProtokolService.deletePredavaciProtokolAll();
  }
}
