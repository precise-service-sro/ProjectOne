package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozka;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniPolozkaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/vyuctovani/{idVyuctovani}/polozka", path = "/vyuctovani/{idVyuctovani}/polozka")
public class VyuctovaniPolozkaEndpoint {

  @Autowired
  private IVyuctovaniPolozkaService vyuctovaniPolozkaService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniPolozka postVyuctovaniPolozka(@RequestBody VyuctovaniPolozka vyuctovaniPolozka) {
    log.trace("postVyuctovaniPolozka()");
    return vyuctovaniPolozkaService.postVyuctovaniPolozka(vyuctovaniPolozka);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniPolozka putVyuctovaniPolozka(@RequestBody VyuctovaniPolozka vyuctovaniPolozka) {
    log.trace("putVyuctovaniPolozka()");
    return vyuctovaniPolozkaService.putVyuctovaniPolozka(vyuctovaniPolozka);
  }

  @RequestMapping(value = "/{idVyuctovaniPolozka}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniPolozka getVyuctovaniPolozka(@PathVariable String idVyuctovaniPolozka) {
    log.trace("getVyuctovaniPolozka()" + idVyuctovaniPolozka);
    return vyuctovaniPolozkaService.getVyuctovaniPolozka(idVyuctovaniPolozka);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<VyuctovaniPolozka> getVyuctovaniPolozkaAll() {
    log.trace("getVyuctovaniPolozkaAll()");
    return vyuctovaniPolozkaService.getVyuctovaniPolozkaAll();
  }
  
  @RequestMapping(value = "/{idVyuctovaniPolozka}", method = RequestMethod.DELETE)
  void deleteVyuctovaniPolozka(@PathVariable String idVyuctovaniPolozka) {
    log.trace("deleteVyuctovaniPolozka()" + idVyuctovaniPolozka);
    vyuctovaniPolozkaService.deleteVyuctovaniPolozka(idVyuctovaniPolozka);
  }
  
  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteVyuctovaniPolozkaAll() {
    log.trace("deleteVyuctovaniPolozkaAll()");
    vyuctovaniPolozkaService.deleteVyuctovaniPolozkaAll();
  }
}
