package com.example.starter;

public class CalcResultActivityImp implements CalcResultActivity{
  @Override
  public float calculateResult(float USD ,float EGP) {
    return (float) USD * EGP;
  }
}
