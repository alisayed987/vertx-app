package com.example.starter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.vertx.core.AbstractVerticle;

import java.time.Duration;

public class FetchVerticle extends AbstractVerticle {


  @Override
  public void start() throws Exception {
    System.out.println("fetch V");

    // Initializing FetchValueWorkFlow
    WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    WorkflowClient client = WorkflowClient.newInstance(service);
    WorkflowOptions options = WorkflowOptions.newBuilder()
      .setTaskQueue("fetch_queue")
      .setWorkflowExecutionTimeout(Duration.ofSeconds(20))
      .build();
    FetchValueWorkFlow wf = client.newWorkflowStub(FetchValueWorkFlow.class, options);
    float EGP = wf.fetchValue();

    vertx.eventBus().publish("recieve.address",EGP);


  }
}
