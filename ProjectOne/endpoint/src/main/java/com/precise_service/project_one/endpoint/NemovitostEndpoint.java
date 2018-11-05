package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.nemovitost.Nemovitost;
import com.precise_service.project_one.service.nemovitost.INemovitostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/nemovitost", path = "/api/nemovitost")
public class NemovitostEndpoint {

  @Autowired
  private INemovitostService nemovitostService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Nemovitost postNemovitost(@RequestBody Nemovitost nemovitost) {
    log.trace("postNemovitost()");
    return nemovitostService.postNemovitost(nemovitost);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Nemovitost putNemovitost(@RequestBody Nemovitost nemovitost) {
    log.trace("putNemovitost()");
    return nemovitostService.putNemovitost(nemovitost);
  }

  @RequestMapping(value = "/{idNemovitost}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  Nemovitost getNemovitost(@PathVariable String idNemovitost) {
    log.trace("getNemovitost()" + idNemovitost);
    return nemovitostService.getNemovitost(idNemovitost);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Nemovitost> getNemovitostAll() {
    log.trace("getNemovitostAll()");
    return nemovitostService.getNemovitostAll();
  }

  @RequestMapping(value = "/{idNemovitost}", method = RequestMethod.DELETE)
  void deleteNemovitost(@PathVariable String idNemovitost) {
    log.trace("deleteNemovitost()");
    nemovitostService.deleteNemovitost(idNemovitost);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteNemovitostAll() {
    log.trace("deleteNemovitostAll()");
    nemovitostService.deleteNemovitostAll();
  }
}
