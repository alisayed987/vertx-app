package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });

    vertx.deployVerticle(new Verticle2());
    Float var = getEGP("https://freecurrencyapi.net/api/v2/latest?apikey=c604bf70-746f-11ec-a4ee-d7b783de69df");
    vertx.eventBus().request("recieve.address",var,handler ->{
      System.out.println("dollars to bounds in Main V :  "+handler.result().body());
    });
  }

  public float getEGP(String myUrl) {
    try {
      // Http GET request
      URL url = new URL(myUrl);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("Accept", "application/json");
      InputStream responseStream = con.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputline;
      StringBuffer response = new StringBuffer();

      // appending response strings together
      while((inputline = in.readLine()) != null){
        response.append(inputline);
      }
      System.out.println("tst");
      int start = response.toString().indexOf("EGP");
      String stri = response.toString().substring(start+5,start+10);
      System.out.println(stri);
      // Extracting EGP from the String Response

      Float EGP = Float.parseFloat( stri ) ;
      System.out.println(EGP);

      return (EGP);
    } catch (Exception e) {
      e.printStackTrace();

      return 0;
    }

  }
}
