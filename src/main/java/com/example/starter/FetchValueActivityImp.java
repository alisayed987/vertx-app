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

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;

public class FetchValueActivityImp implements FetchValueActivity{
//
//  ObjectMapper mapper = new ObjectMapper()
//    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//    .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,true);

  @Override
  public void getUSDFromAPI() {

    try {
      Vertx vertx = Vertx.vertx();
      WebClient client = WebClient.create(vertx);
      client.getAbs("https://freecurrencyapi.net/api/v2/latest?apikey=c604bf70-746f-11ec-a4ee-d7b783de69df")
        .send().onSuccess(response ->{
          JacksonParser res = response.bodyAsJson(JacksonParser.class);
        System.out.println(res.getData().get("EGP"));
          JsonNode currency_values = res.getData();
          float EGP = Float.parseFloat(currency_values.get("EGP").toString());
          // Start ConverterWorker
          ConverterWorker cw = new ConverterWorker();
          cw.startWorker();

          // Deploy ConverterVerticle
          vertx.deployVerticle(new ConvertVerticle());
          vertx.eventBus().request("recieve.address",EGP,handler->{
            System.out.println(handler.result().body());  //Final result (EGP * USD)
          });
        });

    } catch (Exception e) {
      System.out.println("***********************");
      e.printStackTrace();

    }

  }
}
