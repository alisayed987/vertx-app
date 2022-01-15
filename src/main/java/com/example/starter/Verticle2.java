package com.example.starter;

import io.vertx.core.AbstractVerticle;

public class Verticle2 extends AbstractVerticle {

  @Override
  public void start(){
    vertx.eventBus().consumer("recieve.address", msg->{
      Float in_dollars = (float)4.0;
      Float egp = (Float) msg.body();
      System.out.println("dollars to bounds in V2 :  "+ egp *in_dollars);
      msg.reply("dollars to pounds:"+ egp *in_dollars);
    });

  }
}
