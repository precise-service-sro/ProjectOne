package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.nemovitost.NemovitostKontakt;
import com.precise_service.project_one.service.nemovitost.INemovitostKontaktService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/nemovitost/{idNemovitost}/kontakt", path = "/api/nemovitost/{idNemovitost}/kontakt")
public class NemovitostKontaktEndpoint {

  @Autowired
  private INemovitostKontaktService nemovitostKontaktService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  NemovitostKontakt postNemovitostKontakt(@PathVariable String idNemovitost, @RequestBody NemovitostKontakt nemovitostKontakt) {
    log.trace("postNemovitostKontakt()");
    return nemovitostKontaktService.postNemovitostKontakt(idNemovitost, nemovitostKontakt);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  NemovitostKontakt putNemovitostKontakt(@PathVariable String idNemovitost, @RequestBody NemovitostKontakt nemovitostKontakt) {
    log.trace("putNemovitostKontakt()");
    return nemovitostKontaktService.putNemovitostKontakt(idNemovitost, nemovitostKontakt);
  }

  @RequestMapping(value = "/{idNemovitostKontakt}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  NemovitostKontakt getNemovitostKontakt(@PathVariable String idNemovitost, @PathVariable String idNemovitostKontakt) {
    log.trace("getNemovitostKontakt()");
    return nemovitostKontaktService.getNemovitostKontakt(idNemovitost, idNemovitostKontakt);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<NemovitostKontakt> getNemovitostKontaktAll(@PathVariable String idNemovitost) {
    log.trace("getNemovitostKontaktAll()");
    return nemovitostKontaktService.getNemovitostKontaktAll(idNemovitost, "idPrihlasenyUzivatel");
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteNemovitostKontaktAll(@PathVariable String idNemovitost) {
    log.trace("deleteNemovitostKontaktAll()");
    nemovitostKontaktService.deleteNemovitostKontaktAll(idNemovitost);
  }

  @RequestMapping(value = "/{idNemovitostKontakt}", method = RequestMethod.DELETE)
  void deleteNemovitostKontakt(@PathVariable String idNemovitost, @PathVariable String idNemovitostKontakt) {
    log.trace("deleteNemovitostKontakt()");
    nemovitostKontaktService.deleteNemovitostKontakt(idNemovitost, idNemovitostKontakt);
  }
}
