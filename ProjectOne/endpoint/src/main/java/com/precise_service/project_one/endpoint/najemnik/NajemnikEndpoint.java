package com.precise_service.project_one.endpoint.najemnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.najemnik.informace.Najemnik;
import com.precise_service.project_one.service.najemnik.informace.INajemnikService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/najemnik", path = "/najemnik")
public class NajemnikEndpoint {

  @Autowired
  private INajemnikService najemnikService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Najemnik postNajemnik(@RequestBody Najemnik najemnik) {
    log.trace("postNajemnik()");
    return najemnikService.postNajemnik(najemnik);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Najemnik putNajemnik(@RequestBody Najemnik najemnik) {
    log.trace("putNajemnik()");
    return najemnikService.putNajemnik(najemnik);
  }

  @RequestMapping(value = "/{idNajemnik}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  Najemnik getNajemnik(@PathVariable String idNajemnik) {
    log.trace("getNajemnik()" + idNajemnik);
    return najemnikService.getNajemnik(idNajemnik);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Najemnik> getNajemnikAll() {
    log.trace("getNajemnikAll()");
    return najemnikService.getNajemnikAll();
  }

  @RequestMapping(value = "/{idNajemnik}", method = RequestMethod.DELETE)
  void deleteNajemnik(@PathVariable String idNajemnik) {
    log.trace("deleteNajemnik()");
    najemnikService.deleteNajemnik(idNajemnik);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteNajemnikAll() {
    log.trace("deleteNajemnikAll()");
    najemnikService.deleteNajemnikAll();
  }
}
