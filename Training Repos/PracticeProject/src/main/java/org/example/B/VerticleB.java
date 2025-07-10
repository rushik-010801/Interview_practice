package org.example.B;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.example.A.VerticleAHandler;

public class VerticleB extends AbstractVerticle {
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.eventBus().consumer("Verticel.b.address",
            new VerticleBHandler());
    startPromise.complete();
  }
}
