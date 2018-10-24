package com.precise_service.project_one.endpoint.najemnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.najemnik.predavaci_protokol.PredavaciProtokolPolozka;
import com.precise_service.project_one.service.najemnik.predavaci_protokol.IPredavaciProtokolPolozkaService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/najemnik/{idNajemnik}/predavaciProtokol/{idPredavaciProtokol}/polozka", path = "/najemnik/{idNajemnik}/predavaciProtokol/{idPredavaciProtokol}/polozka")
public class PredavaciProtokolPolozkaEndpoint {

  @Autowired
  private IPredavaciProtokolPolozkaService predavaciProtokolPolozkaService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolPolozka postPredavaciProtokolPolozka(@PathVariable String idNajemnik, @PathVariable String idPredavaciProtokol, @RequestBody PredavaciProtokolPolozka predavaciProtokolPolozka) {
    log.trace("postPredavaciProtokolPolozka()");
    return predavaciProtokolPolozkaService.postPredavaciProtokolPolozka(predavaciProtokolPolozka);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolPolozka putPredavaciProtokolPolozka(@PathVariable String idNajemnik, @PathVariable String idPredavaciProtokol, @RequestBody PredavaciProtokolPolozka predavaciProtokolPolozka) {
    log.trace("putPredavaciProtokolPolozka()");
    return predavaciProtokolPolozkaService.putPredavaciProtokolPolozka(predavaciProtokolPolozka);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolPolozka getPredavaciProtokolPolozka(@PathVariable String idPredavaciProtokolPolozka) {
    log.trace("getPredavaciProtokolPolozka()" + idPredavaciProtokolPolozka);
    return predavaciProtokolPolozkaService.getPredavaciProtokolPolozka(idPredavaciProtokolPolozka);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<PredavaciProtokolPolozka> getPredavaciProtokolPolozkaAll(@PathVariable String idNajemnik, @PathVariable String idPredavaciProtokol) {
    log.trace("getPredavaciProtokolPolozkaAll()");
    return predavaciProtokolPolozkaService.getPredavaciProtokolPolozkaAll();
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deletePredavaciProtokolPolozkaAll(@PathVariable String idNajemnik, @PathVariable String idPredavaciProtokol) {
    log.trace("deletePredavaciProtokolPolozkaAll()");
    predavaciProtokolPolozkaService.deletePredavaciProtokolPolozkaAll();
  }
}
