package com.precise_service.project_one.web;

import lombok.Data;

@Data
public class Car {
  private String id;
  private String brand;
  private  int year;
  private String color;
  private int price;
  private boolean soldState;

  public Car(String randomId, String randomBrand, int randomYear, String randomColor, int randomPrice, boolean randomSoldState) {
    this.id = randomId;
    this.brand = randomBrand;
    this.year = randomYear;
    this.color = randomColor;
    this.price = randomPrice;
    this.soldState = randomSoldState;
  }
}
