package com.precise_service.project_one.endpoint.byt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.endpoint.rest_api.RangeRequest;
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaByt;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniZaBytService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/byt/{idByt}/vyuctovani", path = "/byt/{idByt}/vyuctovani")
public class VyuctovaniZaBytEndpoint {

  @Autowired
  private IVyuctovaniZaBytService vyuctovaniZaBytService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniZaByt postVyuctovaniZaByt(@RequestBody VyuctovaniZaByt vyuctovani) {
    log.trace("postVyuctovaniZaByt()");
    return vyuctovaniZaBytService.postVyuctovaniZaByt(vyuctovani);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniZaByt putVyuctovaniZaByt(@RequestBody VyuctovaniZaByt vyuctovani) {
    log.trace("putVyuctovaniZaByt()");
    return vyuctovaniZaBytService.putVyuctovaniZaByt(vyuctovani);
  }

  @RequestMapping(value = "/{idVyuctovaniZaByt}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniZaByt getVyuctovaniZaByt(@PathVariable String idVyuctovaniZaByt) {
    log.trace("getVyuctovaniZaByt()" + idVyuctovaniZaByt);
    return vyuctovaniZaBytService.getVyuctovaniZaByt(idVyuctovaniZaByt);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<VyuctovaniZaByt> getVyuctovaniZaBytAll() {
    log.trace("getVyuctovaniZaBytAll()");
    return vyuctovaniZaBytService.getVyuctovaniZaBytAll();
  }

  @RequestMapping(value = "/!inRange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  List<VyuctovaniZaByt> getVyuctovaniZaBytInRange(@RequestBody RangeRequest request) {
    log.trace("getVyuctovaniZaBytInRange()" + request.getFrom().toString() + " - " + request.getTo().toString());
    return vyuctovaniZaBytService.getVyuctovaniZaBytInRange(request.getFrom(), request.getTo());
  }

  @RequestMapping(value = "/{idVyuctovaniZaByt}", method = RequestMethod.DELETE)
  void deleteVyuctovaniZaByt(@PathVariable String idVyuctovaniZaByt) {
    log.trace("deleteVyuctovaniZaByt()" + idVyuctovaniZaByt);
    vyuctovaniZaBytService.deleteVyuctovaniZaByt(idVyuctovaniZaByt);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteVyuctovaniZaBytAll() {
    log.trace("deleteVyuctovaniZaBytAll()");
    vyuctovaniZaBytService.deleteVyuctovaniZaBytAll();
  }
}
