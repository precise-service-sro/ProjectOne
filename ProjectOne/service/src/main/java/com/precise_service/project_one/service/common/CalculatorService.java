package com.precise_service.project_one.service.common;

import org.springframework.stereotype.Service;

import com.precise_service.project_one.entity.Cislo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalculatorService implements ICalculatorService {

  public Cislo secist(Cislo cislo1, Cislo cislo2) {
    Cislo soucetCislo = new Cislo();
    soucetCislo.setMnozstvi(cislo1.getMnozstvi() + cislo2.getMnozstvi());
    soucetCislo.setJednotka(cislo1.getJednotka());
    return soucetCislo;
  }

  public Cislo odecist(Cislo cislo1, Cislo cislo2) {
    Cislo rozdilCislo = new Cislo();
    rozdilCislo.setMnozstvi(cislo1.getMnozstvi() - cislo2.getMnozstvi());
    rozdilCislo.setJednotka(cislo1.getJednotka());
    return rozdilCislo;
  }

  public Cislo vynasobit(Cislo cislo1, Cislo cislo2) {
    Cislo soucinCislo = new Cislo();
    soucinCislo.setMnozstvi(cislo1.getMnozstvi() - cislo2.getMnozstvi());
    soucinCislo.setJednotka(cislo1.getJednotka());
    return soucinCislo;
  }

  public Cislo vydelit(Cislo cislo1, Cislo cislo2) {
    Cislo podilCislo = new Cislo();
    podilCislo.setMnozstvi(cislo1.getMnozstvi() / cislo2.getMnozstvi());
    podilCislo.setJednotka(cislo1.getJednotka());
    return podilCislo;
  }
}
