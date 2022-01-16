package com.example.starter;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class FetcherWorker {
  void startWorker(){
    WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    WorkflowClient client = WorkflowClient.newInstance(service);
    WorkerFactory factory = WorkerFactory.newInstance(client);
    Worker worker = factory.newWorker("fetch_queue");
    worker.registerWorkflowImplementationTypes(FetchValueWorkFLowImp.class);
    worker.registerActivitiesImplementations(new CalcResultActivityImp());
    factory.start();
  }
}
