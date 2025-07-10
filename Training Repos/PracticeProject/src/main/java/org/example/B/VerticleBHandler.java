package org.example.B;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;

public class VerticleBHandler implements Handler<Message<String>> {
  @Override
  public void handle(Message<String> event) {
    Thread currentThread = Thread.currentThread();
    System.out.println("-------------------------------------------------");
    System.out.println("Verticle B handler method Thread Name : " + currentThread.getName() +
            "\nVerticle B handler methodMethod Thread Id : " + currentThread.getId());
    event.reply("Void Result");
  }
}
