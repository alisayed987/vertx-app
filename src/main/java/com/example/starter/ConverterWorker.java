package com.example.starter;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;


public class ConverterWorker {


  void startWorker(){
    WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    WorkflowClient client = WorkflowClient.newInstance(service);
    WorkerFactory factory = WorkerFactory.newInstance(client);
    Worker worker = factory.newWorker("convert_queue");
    worker.registerWorkflowImplementationTypes(CalcResultWorkFlowImp.class);
    worker.registerActivitiesImplementations(new CalcResultActivityImp());
    factory.start();
  }

}
