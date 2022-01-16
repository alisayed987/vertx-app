package com.example.starter;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface CalcResultActivity {
  @ActivityMethod
  float calculateResult(float USD,float EGP);
}
