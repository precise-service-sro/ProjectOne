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
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniZaBytService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/byt", path = "/byt")
public class VyuctovaniZaBytEndpoint {

  @Autowired
  private IVyuctovaniZaBytService vyuctovaniService;

  @RequestMapping(value = "/vyuctovani", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniEntity postVyuctovaniZaBytEntity(@RequestBody VyuctovaniEntity vyuctovaniEntity) {
    log.trace("postVyuctovaniZaBytEntity()");
    return vyuctovaniService.postVyuctovaniZaBytEntity(vyuctovaniEntity);
  }

  @RequestMapping(value = "/vyuctovani/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniEntity getVyuctovaniZaBytEntity(@PathVariable String id) {
    log.trace("getVyuctovaniZaBytEntity()" + id);
    return vyuctovaniService.getVyuctovaniZaBytEntity(id);
  }

  @RequestMapping(value = "/vyuctovani/!inRange", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  List<VyuctovaniEntity> getVyuctovaniZaBytEntityInRange(@RequestBody RangeRequest request ) {
    log.trace("getVyuctovaniZaBytEntityInRange()" + request.getFrom().toString() + " - " + request.getTo().toString());
    return vyuctovaniService.getVyuctovaniZaBytEntityInRange(request.getFrom(), request.getTo());
  }

  @RequestMapping(value = "/vyuctovani/!all", method = RequestMethod.DELETE)
  void deleteVyuctovaniZaBytEntityAll() {
    log.trace("deleteVyuctovaniZaBytEntityAll()");
    vyuctovaniService.deleteVyuctovaniZaBytEntityAll();
  }
}
