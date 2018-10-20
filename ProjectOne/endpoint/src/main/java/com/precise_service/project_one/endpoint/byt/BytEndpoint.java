package com.precise_service.project_one.endpoint.byt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.byt.informace.BytEntity;
import com.precise_service.project_one.service.byt.informace.IBytService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/byt", path = "/byt")
public class BytEndpoint {

  @Autowired
  private IBytService bytService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  BytEntity postBytEntity(@RequestBody BytEntity bytEntity) {
    log.trace("postBytEntity()");
    return bytService.postBytEntity(bytEntity);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  BytEntity getBytEntity(@PathVariable String idBytEntity) {
    log.trace("getBytEntity()" + idBytEntity);
    return bytService.getBytEntity(idBytEntity);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<BytEntity> getBytEntityAll() {
    log.trace("getBytEntityAll()");
    return bytService.getBytEntityAll();
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteBytEntityAll() {
    log.trace("deleteBytEntityAll()");
    bytService.deleteBytEntityAll();
  }
}
