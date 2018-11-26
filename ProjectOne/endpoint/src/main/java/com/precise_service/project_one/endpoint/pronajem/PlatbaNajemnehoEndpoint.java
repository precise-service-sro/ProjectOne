package com.precise_service.project_one.endpoint.pronajem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.precise_service.project_one.entity.pronajem.PlatbaNajemneho;
import com.precise_service.project_one.service.pronajem.IPlatbaNajemnehoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/platbaNajemneho", path = "/api/platbaNajemneho")
public class PlatbaNajemnehoEndpoint {

  @Autowired
  private IPlatbaNajemnehoService platbaNajemnehoService;

  @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PlatbaNajemneho postPlatbaNajemneho(@RequestBody PlatbaNajemneho platbaNajemneho) {
    log.trace("postPlatbaNajemneho()");
    return platbaNajemnehoService.postPlatbaNajemneho(platbaNajemneho);
  }

  @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  PlatbaNajemneho putPlatbaNajemneho(@RequestBody PlatbaNajemneho platbaNajemneho) {
    log.trace("putPlatbaNajemneho()");
    return platbaNajemnehoService.putPlatbaNajemneho(platbaNajemneho);
  }

  @RequestMapping(value = "/{idPlatbaNajemneho}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  PlatbaNajemneho getPlatbaNajemneho(@PathVariable String idPlatbaNajemneho) {
    log.trace("getPlatbaNajemneho()" + idPlatbaNajemneho);
    return platbaNajemnehoService.getPlatbaNajemneho(idPlatbaNajemneho);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  List<PlatbaNajemneho> getPlatbaNajemnehoAll() {
    log.trace("getPlatbaNajemnehoAll()");
    return platbaNajemnehoService.getPlatbaNajemnehoAll();
  }

  @RequestMapping(value = "/{idPlatbaNajemneho}", method = RequestMethod.DELETE)
  void deletePlatbaNajemneho(@PathVariable String idPlatbaNajemneho) {
    log.trace("deletePlatbaNajemneho()");
    platbaNajemnehoService.deletePlatbaNajemneho(idPlatbaNajemneho);
  }

  @RequestMapping(value = "/!all", method = RequestMethod.DELETE)
  void deletePlatbaNajemnehoAll() {
    log.trace("deletePlatbaNajemnehoAll()");
    platbaNajemnehoService.deletePlatbaNajemnehoAll();
  }
}
