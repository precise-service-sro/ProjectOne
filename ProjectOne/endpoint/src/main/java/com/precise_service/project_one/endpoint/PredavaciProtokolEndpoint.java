package com.precise_service.project_one.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.byt.predavaciProtokol.PredavaciProtokolEntity;
import com.precise_service.project_one.service.IPredavaciProtokolService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PredavaciProtokolEndpoint {

  @Autowired
  private IPredavaciProtokolService predavaciProtokolService;

  @RequestMapping(value = "/predavaciProtokol", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PredavaciProtokolEntity postPredavaciProtokol(@RequestBody PredavaciProtokolEntity predavaciProtokolEntity) {
    log.trace("postPredavaciProtokol()");
    return predavaciProtokolService.postPredavaciProtokol(predavaciProtokolEntity);
  }

  /*
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
  */
}
