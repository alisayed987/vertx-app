package com.example.starter;


public class FetchValueWorkFLowImp implements FetchValueWorkFlow{

  //  Activities Instances
  FetchValueActivity fva = new FetchValueActivityImp();

  @Override
  public float fetchValue() {
    float EGP = fva.getUSDFromAPI();
    return EGP;
  }
}
