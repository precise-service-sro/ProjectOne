package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.PolozkaTyp;
import com.precise_service.project_one.service.vyuctovani.IPolozkaTypService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/polozkaTyp", path = "/polozkaTyp")
public class PolozkaTypEndpoint {

  @Autowired
  private IPolozkaTypService polozkaTypService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PolozkaTyp postPolozkaTyp(@RequestBody PolozkaTyp polozkaTyp) {
    log.trace("postPolozkaTyp()");
    return polozkaTypService.postPolozkaTyp(polozkaTyp);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PolozkaTyp putPolozkaTyp(@RequestBody PolozkaTyp polozkaTyp) {
    log.trace("putPolozkaTyp()");
    return polozkaTypService.putPolozkaTyp(polozkaTyp);
  }

  @RequestMapping(value = "/{idPolozkaTyp}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  PolozkaTyp getPolozkaTyp(@PathVariable String idPolozkaTyp) {
    log.trace("getPolozkaTyp()");
    return polozkaTypService.getPolozkaTyp(idPolozkaTyp);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<PolozkaTyp> getPolozkaTypAll() {
    log.trace("getPolozkaTypAll()");
    return polozkaTypService.getPolozkaTypAll();
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deletePolozkaTypAll() {
    log.trace("deletePolozkaTypAll()");
    polozkaTypService.deletePolozkaTypAll();
  }

  @RequestMapping(value = "/{idPolozkaTyp}", method = RequestMethod.DELETE)
  void deletePolozkaTyp(@PathVariable String idPolozkaTyp) {
    log.trace("deletePolozkaTyp()");
    polozkaTypService.deletePolozkaTyp(idPolozkaTyp);
  }
}
