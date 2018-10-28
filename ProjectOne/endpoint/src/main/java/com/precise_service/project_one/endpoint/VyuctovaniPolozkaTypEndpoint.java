package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.vyuctovani.VyuctovaniPolozkaTyp;
import com.precise_service.project_one.service.vyuctovani.IVyuctovaniPolozkaTypService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/vyuctovaniPolozkaTyp", path = "/vyuctovaniPolozkaTyp")
public class VyuctovaniPolozkaTypEndpoint {

  @Autowired
  private IVyuctovaniPolozkaTypService vyuctovaniPolozkaTypService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniPolozkaTyp postVyuctovaniPolozkaTyp(@RequestBody VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp) {
    log.trace("postVyuctovaniPolozkaTyp()");
    return vyuctovaniPolozkaTypService.postVyuctovaniPolozkaTyp(vyuctovaniPolozkaTyp);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniPolozkaTyp putVyuctovaniPolozkaTyp(@RequestBody VyuctovaniPolozkaTyp vyuctovaniPolozkaTyp) {
    log.trace("putVyuctovaniPolozkaTyp()");
    return vyuctovaniPolozkaTypService.putVyuctovaniPolozkaTyp(vyuctovaniPolozkaTyp);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  VyuctovaniPolozkaTyp getVyuctovaniPolozkaTyp(@PathVariable String idVyuctovaniPolozkaTyp) {
    log.trace("getVyuctovaniPolozkaTyp()" + idVyuctovaniPolozkaTyp);
    return vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTyp(idVyuctovaniPolozkaTyp);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<VyuctovaniPolozkaTyp> getVyuctovaniPolozkaTypAll() {
    log.trace("getVyuctovaniPolozkaTypAll()");
    return vyuctovaniPolozkaTypService.getVyuctovaniPolozkaTypAll();
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteVyuctovaniPolozkaTypAll() {
    log.trace("deleteVyuctovaniPolozkaTypAll()");
    vyuctovaniPolozkaTypService.deleteVyuctovaniPolozkaTypAll();
  }
}
