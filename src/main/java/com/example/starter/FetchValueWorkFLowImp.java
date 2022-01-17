package com.example.starter;


public class FetchValueWorkFLowImp implements FetchValueWorkFlow{

  //  Activities Instances
  FetchValueActivity fva = new FetchValueActivityImp();

  @Override
  public void fetchValue() {
    fva.getUSDFromAPI();

  }
}
