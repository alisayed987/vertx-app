package com.example.starter;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import io.vertx.core.Vertx;

@ActivityInterface
public interface FetchValueActivity {
  @ActivityMethod
  float getUSDFromAPI();
}
