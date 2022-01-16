package com.example.starter;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface CalcResultWorkFlow {
  @WorkflowMethod
  float calcResult(float USD,float EGP);
}
