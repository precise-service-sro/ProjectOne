package com.precise_service.project_one.endpoint.byt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniZaBytPolozka;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniZaBytPolozkaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/byt/{idByt}/vyuctovani/{idVyuctovaniZaByt}/polozka", path = "/byt/{idByt}/vyuctovani/{idVyuctovaniZaByt}/polozka")
public class VyuctovaniZaBytPolozkaEndpoint {

  @Autowired
  private IVyuctovaniZaBytPolozkaService vyuctovaniZaBytPolozkaService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniZaBytPolozka postVyuctovaniZaBytPolozka(@RequestBody VyuctovaniZaBytPolozka vyuctovaniZaBytPolozka) {
    log.trace("postVyuctovaniZaBytPolozka()");
    return vyuctovaniZaBytPolozkaService.postVyuctovaniZaBytPolozka(vyuctovaniZaBytPolozka);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniZaBytPolozka putVyuctovaniZaBytPolozka(@RequestBody VyuctovaniZaBytPolozka vyuctovaniZaBytPolozka) {
    log.trace("putVyuctovaniZaBytPolozka()");
    return vyuctovaniZaBytPolozkaService.putVyuctovaniZaBytPolozka(vyuctovaniZaBytPolozka);
  }

  @RequestMapping(value = "/{idVyuctovaniZaBytPolozka}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniZaBytPolozka getVyuctovaniZaBytPolozka(@PathVariable String idVyuctovaniZaBytPolozka) {
    log.trace("getVyuctovaniZaBytPolozka()" + idVyuctovaniZaBytPolozka);
    return vyuctovaniZaBytPolozkaService.getVyuctovaniZaBytPolozka(idVyuctovaniZaBytPolozka);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<VyuctovaniZaBytPolozka> getVyuctovaniZaBytPolozkaAll() {
    log.trace("getVyuctovaniZaBytPolozkaAll()");
    return vyuctovaniZaBytPolozkaService.getVyuctovaniZaBytPolozkaAll();
  }
  
  @RequestMapping(value = "/{idVyuctovaniZaBytPolozka}", method = RequestMethod.DELETE)
  void deleteVyuctovaniZaBytPolozka(@PathVariable String idVyuctovaniZaBytPolozka) {
    log.trace("deleteVyuctovaniZaBytPolozka()" + idVyuctovaniZaBytPolozka);
    vyuctovaniZaBytPolozkaService.deleteVyuctovaniZaBytPolozka(idVyuctovaniZaBytPolozka);
  }
  
  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteVyuctovaniZaBytPolozkaAll() {
    log.trace("deleteVyuctovaniZaBytPolozkaAll()");
    vyuctovaniZaBytPolozkaService.deleteVyuctovaniZaBytPolozkaAll();
  }
}
