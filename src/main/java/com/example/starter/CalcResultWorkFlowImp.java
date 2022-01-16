package com.example.starter;

public class CalcResultWorkFlowImp implements CalcResultWorkFlow{
  // Activities Instances
CalcResultActivityImp crai = new CalcResultActivityImp();
  @Override
  public float calcResult(float USD,float EGP) {
    float result = crai.calculateResult(USD,EGP);
    return result;
  }
}
