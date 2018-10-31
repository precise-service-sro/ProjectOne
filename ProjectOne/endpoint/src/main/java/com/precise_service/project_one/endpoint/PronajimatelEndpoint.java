package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.osoba.Pronajimatel;
import com.precise_service.project_one.service.osoba.IPronajimatelService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/pronajimatel", path = "/pronajimatel")
public class PronajimatelEndpoint {

  @Autowired
  private IPronajimatelService pronajimatelService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Pronajimatel postPronajimatel(@RequestBody Pronajimatel pronajimatel) {
    log.trace("postPronajimatel()");
    return pronajimatelService.postPronajimatel(pronajimatel);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Pronajimatel putPronajimatel(@RequestBody Pronajimatel pronajimatel) {
    log.trace("putPronajimatel()");
    return pronajimatelService.putPronajimatel(pronajimatel);
  }

  @RequestMapping(value = "/{idPronajimatel}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  Pronajimatel getPronajimatel(@PathVariable String idPronajimatel) {
    log.trace("getPronajimatel()" + idPronajimatel);
    return pronajimatelService.getPronajimatel(idPronajimatel);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Pronajimatel> getPronajimatelAll() {
    log.trace("getPronajimatelAll()");
    return pronajimatelService.getPronajimatelAll();
  }

  @RequestMapping(value = "/{idPronajimatel}", method = RequestMethod.DELETE)
  void deletePronajimatel(@PathVariable String idPronajimatel) {
    log.trace("deletePronajimatel()");
    pronajimatelService.deletePronajimatel(idPronajimatel);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deletePronajimatelAll() {
    log.trace("deletePronajimatelAll()");
    pronajimatelService.deletePronajimatelAll();
  }
}
