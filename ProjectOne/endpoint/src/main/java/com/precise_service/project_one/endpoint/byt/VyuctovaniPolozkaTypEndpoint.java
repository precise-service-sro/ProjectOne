package com.precise_service.project_one.endpoint.byt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.byt.vyuctovani_za_byt.VyuctovaniPolozkaTypEntity;
import com.precise_service.project_one.service.byt.vyuctovani_za_byt.IVyuctovaniPolozkaTypService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/vyuctovaniPolozkaTyp", path = "/vyuctovaniPolozkaTyp")
public class VyuctovaniPolozkaTypEndpoint {

  @Autowired
  private IVyuctovaniPolozkaTypService vyuctovaniPolozkaTypService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniPolozkaTypEntity postVyuctovaniPolozkaTyp(@RequestBody VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity) {
    log.trace("postVyuctovaniPolozkaTyp()");
    return vyuctovaniPolozkaTypService.postVyuctovaniPolozkaTypEntity(vyuctovaniPolozkaTypEntity);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniPolozkaTypEntity putVyuctovaniPolozkaTyp(@RequestBody VyuctovaniPolozkaTypEntity vyuctovaniPolozkaTypEntity) {
    log.trace("putVyuctovaniPolozkaTyp()");
    return vyuctovaniPolozkaTypService.putVyuctovaniPolozkaTypEntity(vyuctovaniPolozkaTypEntity);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniPolozkaTypEntity getVyuctovaniPolozkaTypEntity(@PathVariable String idVyuctovaniPolozkaTypEntity) {
    log.trace("getVyuctovaniPolozkaTypEntity()" + idVyuctovaniPolozkaTypEntity);
    return vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTypEntity(idVyuctovaniPolozkaTypEntity);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<VyuctovaniPolozkaTypEntity> getVyuctovaniPolozkaTypEntityAll() {
    log.trace("getVyuctovaniPolozkaTypEntityAll()");
    return vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTypEntityAll();
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteVyuctovaniPolozkaTypEntityAll() {
    log.trace("deleteVyuctovaniPolozkaTypEntityAll()");
    vyuctovaniPolozkaTypService.deleteVyuctovaniPolozkaTypEntityAll();
  }
}
