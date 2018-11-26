package com.precise_service.project_one.endpoint.pronajem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.pronajem.NajemniSmlouva;
import com.precise_service.project_one.service.pronajem.INajemniSmlouvaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/najemniSmlouva", path = "/api/najemniSmlouva")
public class NajemniSmlouvaEndpoint {

  @Autowired
  private INajemniSmlouvaService najemniSmlouvaService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  NajemniSmlouva postNajemniSmlouva(@RequestBody NajemniSmlouva najemniSmlouva) {
    log.trace("postNajemniSmlouva()");
    return najemniSmlouvaService.postNajemniSmlouva(najemniSmlouva);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  NajemniSmlouva putNajemniSmlouva(@RequestBody NajemniSmlouva najemniSmlouva) {
    log.trace("putNajemniSmlouva()");
    return najemniSmlouvaService.putNajemniSmlouva(najemniSmlouva);
  }

  @RequestMapping(value = "/{idNajemniSmlouva}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  NajemniSmlouva getNajemniSmlouva(@PathVariable String idNajemniSmlouva) {
    log.trace("getNajemniSmlouva()" + idNajemniSmlouva);
    return najemniSmlouvaService.getNajemniSmlouva(idNajemniSmlouva);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<NajemniSmlouva> getNajemniSmlouvaAll() {
    log.trace("getNajemniSmlouvaAll()");
    return najemniSmlouvaService.getNajemniSmlouvaAll();
  }

  @RequestMapping(value = "/{idNajemniSmlouva}", method = RequestMethod.DELETE)
  void deleteNajemniSmlouva(@PathVariable String idNajemniSmlouva) {
    log.trace("deleteNajemniSmlouva()");
    najemniSmlouvaService.deleteNajemniSmlouva(idNajemniSmlouva);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteNajemniSmlouvaAll() {
    log.trace("deleteNajemniSmlouvaAll()");
    najemniSmlouvaService.deleteNajemniSmlouvaAll();
  }
}
