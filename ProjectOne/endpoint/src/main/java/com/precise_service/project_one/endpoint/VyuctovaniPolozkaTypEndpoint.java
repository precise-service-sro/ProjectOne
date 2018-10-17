package com.precise_service.project_one.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniEntity;
import com.precise_service.project_one.entity.byt.vyuctovani.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.service.IVyuctovaniPolozkaTypeService;
import com.precise_service.project_one.service.IVyuctovaniService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VyuctovaniPolozkaTypEndpoint {

  @Autowired
  private IVyuctovaniPolozkaTypeService vyuctovaniPolozkaTypeService;

  @RequestMapping(value = "/postVyuctovaniPolozkaTypEntity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniPolozkaTypEntity postVyuctovaniPolozkaTypEntity(@RequestBody VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity) {
    log.trace("postVyuctovaniPolozkaTypEntity()");
    return vyuctovaniPolozkaTypeService.postVyuctovaniPolozkaTypEntity(vyuctovaniPolozkaTypEntity);
  }
}
