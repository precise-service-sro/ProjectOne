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
import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniEntity;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VyuctovaniEndpoint {

  @Autowired
  private IVyuctovaniService vyuctovaniService;

  @RequestMapping(value = "/vyuctovani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniEntity postVyuctovani(@RequestBody VyuctovaniEntity vyuctovaniEntity) {
    log.trace("postVyuctovani()");
    return vyuctovaniService.postVyuctovani(vyuctovaniEntity);
  }

  @RequestMapping(value = "/vyuctovani/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniEntity getVyuctovani(@PathVariable String id) {
    log.trace("getVyuctovani()" + id);
    return vyuctovaniService.getVyuctovani(id);
  }

  @RequestMapping(value = "/vyuctovani/!inRange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  List<VyuctovaniEntity> getVyuctovaniInRange(@RequestBody RangeRequest request ) {
    log.trace("getVyuctovaniInRange()" + request.getFrom().toString() + " - " + request.getTo().toString());
    return vyuctovaniService.getVyuctovaniInRange(request.getFrom(), request.getTo());
  }

  @RequestMapping(value = "/vyuctovani/!all", method = RequestMethod.DELETE)
  void deleteVyuctovaniAll() {
    log.trace("deleteVyuctovaniAll()");
    vyuctovaniService.deleteVyuctovaniAll();
  }
}
