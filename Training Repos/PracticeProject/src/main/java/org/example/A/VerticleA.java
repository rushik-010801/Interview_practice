package org.example.A;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleA extends AbstractVerticle {
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.eventBus().consumer("Verticel.a.address",
            new VerticleAHandler());
    startPromise.complete();
  }
}
