package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    // Start FetchWorkFlow
    FetcherWorker fw = new FetcherWorker();
    fw.startWorker();
    // Start ConverterWorker
    ConverterWorker cw = new ConverterWorker();
    cw.startWorker();

    // Deploy ConverterVerticle
    vertx.deployVerticle(new ConvertVerticle());

    //deploy FetchVerticle
    vertx.deployVerticle(new FetchVerticle());

  }
}
