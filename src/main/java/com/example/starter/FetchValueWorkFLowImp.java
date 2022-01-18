package com.example.starter;


import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.ActivityStub;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class FetchValueWorkFLowImp implements FetchValueWorkFlow{

//  //  Activities Instances
//  FetchValueActivity fva = new FetchValueActivityImp();

  ActivityOptions options1 = ActivityOptions.newBuilder()
    .setTaskQueue("fetch_queue")
    .setStartToCloseTimeout(Duration.ofSeconds(5))
    .build();


  @Override
  public float fetchValue() {
    System.out.println("WF ");

    FetchValueActivity activities = Workflow.newActivityStub(FetchValueActivity.class,options1);
    float EGP = activities.getUSDFromAPI();
    System.out.println("wF Again");
    System.out.println(EGP);
    return  EGP;
  }
}
