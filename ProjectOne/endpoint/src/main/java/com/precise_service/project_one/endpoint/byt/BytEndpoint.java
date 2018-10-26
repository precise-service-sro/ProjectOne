package com.precise_service.project_one.endpoint.byt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.byt.informace.Byt;
import com.precise_service.project_one.service.byt.informace.IBytService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/byt", path = "/byt")
public class BytEndpoint {

  @Autowired
  private IBytService bytService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Byt postByt(@RequestBody Byt byt) {
    log.trace("postByt()");
    return bytService.postByt(byt);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Byt putByt(@RequestBody Byt byt) {
    log.trace("putByt()");
    return bytService.putByt(byt);
  }

  @RequestMapping(value = "/{idByt}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  Byt getByt(@PathVariable String idByt) {
    log.trace("getByt()" + idByt);
    return bytService.getByt(idByt);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Byt> getBytAll() {
    log.trace("getBytAll()");
    return bytService.getBytAll();
  }

  @RequestMapping(value = "/{idByt}", method = RequestMethod.DELETE)
  void deleteByt(@PathVariable String idByt) {
    log.trace("deleteByt()");
    bytService.deleteByt(idByt);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteBytAll() {
    log.trace("deleteBytAll()");
    bytService.deleteBytAll();
  }
}
