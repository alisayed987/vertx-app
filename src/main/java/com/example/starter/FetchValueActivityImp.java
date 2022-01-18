package com.example.starter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionClient;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;

public class FetchValueActivityImp implements FetchValueActivity{
  private final ActivityCompletionClient completionClient;
  FetchValueActivityImp(ActivityCompletionClient completionClient){
    this.completionClient=completionClient;
  }
  @Override
  public float getUSDFromAPI() {
    System.out.println("Fectch ACtiviy");

    ActivityExecutionContext ctx = Activity.getExecutionContext();
    byte[] taskToken = ctx.getInfo().getTaskToken();
    try {
      System.out.println("trying");
      Vertx vertx = Vertx.vertx();
      WebClient client = WebClient.create(vertx);
      client.getAbs("https://freecurrencyapi.net/api/v2/latest?apikey=c604bf70-746f-11ec-a4ee-d7b783de69df")
        .send().onSuccess(response ->{
          JacksonParser res = response.bodyAsJson(JacksonParser.class);
        System.out.println(res.getData().EGP);
          float EGP = Float.parseFloat(res.getData().EGP);
          completeActivity(taskToken,EGP);
        }).onFailure(r->{
          System.out.println("Failed");

          failActivity(taskToken,new Exception(r));
        });
      ctx.doNotCompleteOnReturn();
      return (float) 0;
    } catch (Exception e) {
      System.out.println("***********************");
      e.printStackTrace();
      failActivity(taskToken,new Exception(e));
      return (float) 0;


    }

  }
  public <R> void completeActivity(byte[] taskToken, R result) {
    completionClient.complete(taskToken, result);
  }

  public void failActivity(byte[] taskToken, Exception failure) {
    completionClient.completeExceptionally(taskToken, failure);
  }
}
