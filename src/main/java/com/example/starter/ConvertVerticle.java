package com.example.starter;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.vertx.core.AbstractVerticle;

public class ConvertVerticle extends AbstractVerticle {


  @Override
  public void start() throws Exception {


    // ConvertVerticle Receiving message(EGP) from FetchVerticle
    vertx.eventBus().consumer("recieve.address", msg->{

      // Initializing CalcResultWorkFlow
      float usd_in = (float) 4;
      WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
      WorkflowClient client = WorkflowClient.newInstance(service);
      WorkflowOptions options = WorkflowOptions.newBuilder()
        .setTaskQueue("convert_queue")
        .build();
      CalcResultWorkFlow wf = client.newWorkflowStub(CalcResultWorkFlow.class, options);

      // Receiving the message
      float EGP = (float) msg.body();
      float result = wf.calcResult(usd_in,EGP);

      // Reply with the result to FetchVerticle
      msg.reply(result);
    });


  }
}
