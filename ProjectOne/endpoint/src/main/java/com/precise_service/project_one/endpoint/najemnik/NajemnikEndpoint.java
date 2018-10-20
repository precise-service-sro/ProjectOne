package com.precise_service.project_one.endpoint.najemnik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.najemnik.informace.NajemnikEntity;
import com.precise_service.project_one.service.najemnik.informace.INajemnikService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/najemnik", path = "/najemnik")
public class NajemnikEndpoint {

  @Autowired
  private INajemnikService najemnikService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  NajemnikEntity postNajemnikEntity(@RequestBody NajemnikEntity najemnikEntity) {
    log.trace("postNajemnikEntity()");
    return najemnikService.postNajemnikEntity(najemnikEntity);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  NajemnikEntity getNajemnikEntity(@PathVariable String idNajemnikEntity) {
    log.trace("getNajemnikEntity()" + idNajemnikEntity);
    return najemnikService.getNajemnikEntity(idNajemnikEntity);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<NajemnikEntity> getNajemnikEntityAll() {
    log.trace("getNajemnikEntityAll()");
    return najemnikService.getNajemnikEntityAll();
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteNajemnikEntityAll() {
    log.trace("deleteNajemnikEntityAll()");
    najemnikService.deleteNajemnikEntityAll();
  }
}
