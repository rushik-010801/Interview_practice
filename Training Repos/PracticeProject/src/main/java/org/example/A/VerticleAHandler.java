package org.example.A;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

public class VerticleAHandler implements Handler<Message<String>> {
  @Override
  public void handle(Message<String> event) {
    Thread currentThread = Thread.currentThread();
    System.out.println("-------------------------------------------------");
    System.out.println("Verticle A handler method Thread Name : " + currentThread.getName() +
            "\nVerticle A handler methodMethod Thread Id : " + currentThread.getId());
    event.reply("Void Result");
  }
}
