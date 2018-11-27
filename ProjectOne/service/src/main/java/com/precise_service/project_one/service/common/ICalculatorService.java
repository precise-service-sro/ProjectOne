package com.precise_service.project_one.service.common;

import com.precise_service.project_one.entity.Cislo;

public interface ICalculatorService {
  Cislo secist(Cislo cislo1, Cislo cislo2);
  Cislo odecist(Cislo cislo1, Cislo cislo2);
  Cislo vynasobit(Cislo cislo1, Cislo cislo2);
  Cislo vydelit(Cislo cislo1, Cislo cislo2);
}
