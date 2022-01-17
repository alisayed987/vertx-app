package com.example.starter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.vertx.core.AbstractVerticle;

public class FetchVerticle extends AbstractVerticle {


  @Override
  public void start() throws Exception {

    // Initializing FetchValueWorkFlow
    WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    WorkflowClient client = WorkflowClient.newInstance(service);
    WorkflowOptions options = WorkflowOptions.newBuilder()
      .setTaskQueue("fetch_queue")
      .build();
    FetchValueWorkFlow wf = client.newWorkflowStub(FetchValueWorkFlow.class, options);
    wf.fetchValue();


  }
}
