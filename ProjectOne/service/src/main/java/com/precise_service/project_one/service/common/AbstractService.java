package com.precise_service.project_one.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.precise_service.project_one.repository.FakturaPolozkaRepository;
import com.precise_service.project_one.repository.FakturaRepository;
import com.precise_service.project_one.repository.NemovitostKontaktRepository;
import com.precise_service.project_one.repository.NemovitostRepository;
import com.precise_service.project_one.repository.OsobaRepository;
import com.precise_service.project_one.repository.PolozkaTypRepository;
import com.precise_service.project_one.repository.pronajem.NajemniSmlouvaRepository;
import com.precise_service.project_one.repository.pronajem.PlatbaNajemnehoRepository;
import com.precise_service.project_one.repository.pronajem.PredavaciProtokolPolozkaRepository;
import com.precise_service.project_one.repository.pronajem.PredavaciProtokolRepository;
import com.precise_service.project_one.repository.pronajem.VyuctovaniPolozkaRepository;
import com.precise_service.project_one.repository.pronajem.VyuctovaniRepository;
import com.precise_service.project_one.service.IPolozkaTypService;
import com.precise_service.project_one.service.faktura.IFakturaPolozkaService;
import com.precise_service.project_one.service.faktura.IFakturaService;
import com.precise_service.project_one.service.nemovitost.NemovitostService;
import com.precise_service.project_one.service.pronajem.IPredavaciProtokolPolozkaService;
import com.precise_service.project_one.service.pronajem.IVyuctovaniPolozkaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public abstract class AbstractService {

  @Autowired protected MongoTemplate mongoTemplate;
  
  @Autowired protected FakturaPolozkaRepository fakturaPolozkaRepository;
  @Autowired protected FakturaRepository fakturaRepository;
  @Autowired protected NajemniSmlouvaRepository najemniSmlouvaRepository;
  @Autowired protected NemovitostRepository nemovitostRepository;
  @Autowired protected NemovitostKontaktRepository nemovitostKontaktRepository;
  @Autowired protected OsobaRepository osobaRepository;
  @Autowired protected PolozkaTypRepository polozkaTypRepository;
  @Autowired protected PlatbaNajemnehoRepository platbaNajemnehoRepository;
  @Autowired protected PredavaciProtokolPolozkaRepository predavaciProtokolPolozkaRepository;
  @Autowired protected PredavaciProtokolRepository predavaciProtokolRepository;
  @Autowired protected VyuctovaniPolozkaRepository vyuctovaniPolozkaRepository;
  @Autowired protected VyuctovaniRepository vyuctovaniRepository;

  @Autowired protected ICalculatorService calculatorService;
  @Autowired protected IFakturaService fakturaService;
  @Autowired protected IFakturaPolozkaService fakturaPolozkaService;
  @Autowired protected NemovitostService nemovitostService;
  @Autowired protected IPolozkaTypService polozkaTypService;
  @Autowired protected IPredavaciProtokolPolozkaService predavaciProtokolPolozkaService;
  @Autowired protected IVyuctovaniPolozkaService vyuctovaniPolozkaService;
}
