package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.vyuctovani.Vyuctovani;
import com.precise_service.project_one.request.RangeRequest;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/vyuctovani", path = "/api/vyuctovani")
public class VyuctovaniEndpoint {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Vyuctovani postVyuctovani(@RequestBody Vyuctovani vyuctovani) {
    log.trace("postVyuctovani()");
    return vyuctovaniService.postVyuctovani(vyuctovani);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Vyuctovani putVyuctovani(@RequestBody Vyuctovani vyuctovani) {
    log.trace("putVyuctovani()");
    return vyuctovaniService.putVyuctovani(vyuctovani);
  }

  @RequestMapping(value = "/{idVyuctovani}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  Vyuctovani getVyuctovani(@PathVariable String idVyuctovani) {
    log.trace("getVyuctovani()" + idVyuctovani);
    return vyuctovaniService.getVyuctovani(idVyuctovani);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Vyuctovani> getVyuctovaniAll() {
    log.trace("getVyuctovaniAll()");
    return vyuctovaniService.getVyuctovaniAll();
  }

  @RequestMapping(value = "/!inRange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Vyuctovani> getVyuctovaniInRange(@RequestBody RangeRequest request) {
    log.trace("getVyuctovaniInRange()" + request.getFrom().toString() + " - " + request.getTo().toString());
    return vyuctovaniService.getVyuctovaniInRange(request.getFrom(), request.getTo());
  }

  @RequestMapping(value = "/{idVyuctovani}", method = RequestMethod.DELETE)
  void deleteVyuctovani(@PathVariable String idVyuctovani) {
    log.trace("deleteVyuctovani()" + idVyuctovani);
    vyuctovaniService.deleteVyuctovani(idVyuctovani);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteVyuctovaniAll() {
    log.trace("deleteVyuctovaniAll()");
    vyuctovaniService.deleteVyuctovaniAll();
  }
}
