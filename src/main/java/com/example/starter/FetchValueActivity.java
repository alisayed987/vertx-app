package com.example.starter;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface FetchValueActivity {
  @ActivityMethod
  float getUSDFromAPI();
}
