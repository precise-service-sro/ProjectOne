package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.faktura.Faktura;
import com.precise_service.project_one.request.RangeRequest;
import com.precise_service.project_one.service.faktura.IFakturaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/faktura", path = "/faktura")
public class FakturaEndpoint {

  @Autowired
  private IFakturaService fakturaService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Faktura postFaktura(@RequestBody Faktura faktura) {
    log.trace("postFaktura()");
    return fakturaService.postFaktura(faktura);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Faktura putFaktura(@RequestBody Faktura faktura) {
    log.trace("putFaktura()");
    return fakturaService.putFaktura(faktura);
  }

  @RequestMapping(value = "/{idFaktura}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  Faktura getFaktura(@PathVariable String idFaktura) {
    log.trace("getFaktura()" + idFaktura);
    return fakturaService.getFaktura(idFaktura);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Faktura> getFakturaAll() {
    log.trace("getFakturaAll()");
    return fakturaService.getFakturaAll();
  }

  @RequestMapping(value = "/!inRange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Faktura> getFakturaListInRange(@RequestBody RangeRequest request) {
    log.trace("getFakturaListInRange()" + request.getFrom().toString() + " - " + request.getTo().toString());
    return fakturaService.getFakturaListInRange(request.getFrom(), request.getTo());
  }

  @RequestMapping(value = "/{idFaktura}", method = RequestMethod.DELETE)
  void deleteFaktura(@PathVariable String idFaktura) {
    log.trace("deleteFaktura()" + idFaktura);
    fakturaService.deleteFaktura(idFaktura);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteFakturaAll() {
    log.trace("deleteFakturaAll()");
    fakturaService.deleteFakturaAll();
  }
}
