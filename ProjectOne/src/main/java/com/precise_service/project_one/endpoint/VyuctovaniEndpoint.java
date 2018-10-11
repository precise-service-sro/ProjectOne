package com.precise_service.project_one.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.domain.vyuctovani.Vyuctovani;
import com.precise_service.project_one.service.IVyuctovaniService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VyuctovaniEndpoint {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @RequestMapping(value = "/vyuctovani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  void postVyuctovani(@RequestBody Vyuctovani vyuctovani) {
    log.trace("postVyuctovani()");
    vyuctovaniService.createVyuctovani(vyuctovani);
  }

  @RequestMapping(value = "/vyuctovani/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  Vyuctovani getVyuctovani(@PathVariable String id) {
    log.trace("getVyuctovani()" + id);
    return vyuctovaniService.getVyuctovani(id);
  }

  @RequestMapping(value = "/vyuctovani/!all", method = RequestMethod.DELETE)
  void deleteVyuctovaniAll() {
    log.trace("deleteVyuctovaniAll()");
    vyuctovaniService.deleteVyuctovaniAll();
  }
}
