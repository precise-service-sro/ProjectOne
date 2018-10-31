package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.osoba.Uzivatel;
import com.precise_service.project_one.service.osoba.IUzivatelService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/uzivatel", path = "/uzivatel")
public class UzivatelEndpoint {

  @Autowired
  private IUzivatelService uzivatelService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Uzivatel postUzivatel(@RequestBody Uzivatel uzivatel) {
    log.trace("postUzivatel()");
    return uzivatelService.postUzivatel(uzivatel);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Uzivatel putUzivatel(@RequestBody Uzivatel uzivatel) {
    log.trace("putUzivatel()");
    return uzivatelService.putUzivatel(uzivatel);
  }

  @RequestMapping(value = "/{idUzivatel}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  Uzivatel getUzivatel(@PathVariable String idUzivatel) {
    log.trace("getUzivatel()" + idUzivatel);
    return uzivatelService.getUzivatel(idUzivatel);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Uzivatel> getUzivatelAll() {
    log.trace("getUzivatelAll()");
    return uzivatelService.getUzivatelAll();
  }

  @RequestMapping(value = "/{idUzivatel}", method = RequestMethod.DELETE)
  void deleteUzivatel(@PathVariable String idUzivatel) {
    log.trace("deleteUzivatel()");
    uzivatelService.deleteUzivatel(idUzivatel);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteUzivatelAll() {
    log.trace("deleteUzivatelAll()");
    uzivatelService.deleteUzivatelAll();
  }
}
