package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.faktura.FakturaPolozka;
import com.precise_service.project_one.service.faktura.IFakturaPolozkaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/faktura/{idFaktura}/polozka", path = "/faktura/{idFaktura}/polozka")
public class FakturaPolozkaEndpoint {

  @Autowired
  private IFakturaPolozkaService fakturaPolozkaService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  FakturaPolozka postFakturaPolozka(@RequestBody FakturaPolozka fakturaPolozka) {
    log.trace("postFakturaPolozka()");
    return fakturaPolozkaService.postFakturaPolozka(fakturaPolozka);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  FakturaPolozka putFakturaPolozka(@RequestBody FakturaPolozka fakturaPolozka) {
    log.trace("putFakturaPolozka()");
    return fakturaPolozkaService.putFakturaPolozka(fakturaPolozka);
  }

  @RequestMapping(value = "/{idFakturaPolozka}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  FakturaPolozka getFakturaPolozka(@PathVariable String idFakturaPolozka) {
    log.trace("getFakturaPolozka()" + idFakturaPolozka);
    return fakturaPolozkaService.getFakturaPolozka(idFakturaPolozka);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<FakturaPolozka> getFakturaPolozkaAll() {
    log.trace("getFakturaPolozkaAll()");
    return fakturaPolozkaService.getFakturaPolozkaAll();
  }
  
  @RequestMapping(value = "/{idFakturaPolozka}", method = RequestMethod.DELETE)
  void deleteFakturaPolozka(@PathVariable String idFakturaPolozka) {
    log.trace("deleteFakturaPolozka()" + idFakturaPolozka);
    fakturaPolozkaService.deleteFakturaPolozka(idFakturaPolozka);
  }
  
  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteFakturaPolozkaAll() {
    log.trace("deleteFakturaPolozkaAll()");
    fakturaPolozkaService.deleteFakturaPolozkaAll();
  }
}
