package com.precise_service.project_one.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.filter.OsobaFilter;
import com.precise_service.project_one.entity.osoba.Osoba;
import com.precise_service.project_one.service.osoba.IOsobaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/osoba", path = "/api/osoba")
public class OsobaEndpoint {

  @Autowired
  private IOsobaService osobaService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Osoba postOsoba(@RequestBody Osoba osoba) {
    log.trace("postOsoba()");
    return osobaService.postOsoba(osoba);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Osoba putOsoba(@RequestBody Osoba osoba) {
    log.trace("putOsoba()");
    return osobaService.putOsoba(osoba);
  }

  @RequestMapping(value = "/{idOsoba}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  Osoba getOsoba(@PathVariable String idOsoba) {
    log.trace("getOsoba()" + idOsoba);
    return osobaService.getOsoba(idOsoba);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<Osoba> getOsobaAll() {
    log.trace("getOsobaAll()");
    return osobaService.getOsobaList(new OsobaFilter());
  }

  @RequestMapping(value = "/{idOsoba}", method = RequestMethod.DELETE)
  void deleteOsoba(@PathVariable String idOsoba) {
    log.trace("deleteOsoba()");
    osobaService.deleteOsoba(idOsoba);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deleteOsobaAll() {
    log.trace("deleteOsobaAll()");
    osobaService.deleteOsobaAll();
  }
}
