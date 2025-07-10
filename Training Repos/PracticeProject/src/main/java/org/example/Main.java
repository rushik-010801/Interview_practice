package org.example;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.example.A.HelperA;
import org.example.A.VerticleA;
import org.example.B.HelperB;
import org.example.B.VerticleB;

public class Main{
  public static void main(String[] args) {
    Thread currentThread = Thread.currentThread();
    System.out.println("-------------------------------------------------");
    System.out.println("Main method Thread Name : " + currentThread.getName() +
            "\nMain Method Thread Id : " + currentThread.getId());

//    checkFun().onSuccess(Str -> System.out.println("Private function future"));

    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new VerticleA()).onSuccess(msg -> {
      Thread currentThread3 = Thread.currentThread();
      System.out.println("-------------------------------------------------");
      System.out.println("Main method Thread Name : " + currentThread3.getName() +
              "\nMain Method Thread Id : " + currentThread3.getId());
      System.out.println("VerticleA Deployed successfully");
    });
    vertx.deployVerticle(new VerticleB()).onSuccess(msg -> {
      Thread currentThread3 = Thread.currentThread();
      System.out.println("-------------------------------------------------");
      System.out.println("Main method Thread Name : " + currentThread3.getName() +
              "\nMain Method Thread Id : " + currentThread3.getId());
      System.out.println("VerticleB Deployed successfully");
    });

    EventBus eventBus = vertx.eventBus();

    HelperA helperA = new HelperA();
    helperA.callHandler(eventBus).onSuccess(ignorableResult -> {
      Thread currentThread2 = Thread.currentThread();
      System.out.println("-------------------------------------------------");
      System.out.println("Main method Thread Name : " + currentThread2.getName() +
              "\nMain Method Thread Id : " + currentThread2.getId());
      System.out.println("Success Handler Verticle A");
    }).onFailure(err -> System.out.println("Error handler"));

    HelperB HelperB = new HelperB();
    HelperB.callHandler(eventBus).onSuccess(ignorableResult -> {
      Thread currentThread2 = Thread.currentThread();
      System.out.println("-------------------------------------------------");
      System.out.println("Main method Thread Name : " + currentThread2.getName() +
              "\nMain Method Thread Id : " + currentThread2.getId());
      System.out.println("Success Handler Verticle B");
    }).onFailure(err -> System.out.println("Error handler"));

    System.out.println("Outside the promise and future block");
  }

//  private static Future<String> checkFun(){
//    Promise<String> promise = Promise.promise();
//    Thread currentThread = Thread.currentThread();
//    System.out.println("-------------------------------------------------");
//    System.out.println("Private method Thread Name : " + currentThread.getName() +
//            "\nprivate Method Thread Id : " + currentThread.getId());
//    promise.complete();
//    return promise.future();
//  }
}