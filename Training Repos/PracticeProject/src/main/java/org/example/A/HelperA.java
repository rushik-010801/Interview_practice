package org.example.A;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class HelperA {

  public Future<Void> callHandler(EventBus eventBus){
    Promise<Void> promise = Promise.promise();
    eventBus.<String>request("Verticel.a.address",new JsonObject(), response -> {
      if(response.succeeded()) promise.complete();
      else promise.fail("Promise failed");
    });
    return promise.future();
  }
}
