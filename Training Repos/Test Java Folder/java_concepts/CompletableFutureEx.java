package java_concepts;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 Completable Future is an extension of Future. This is more flexible while writing async code.
 The main methods of Future is get and when we use that it stops all the threads till the current thread gets executed.
 But CompletableFuture provides various other methods which helps in writing non=blocking code.

 Main Functions of CompletableFutures :
    - Use thenApply() when you need to transform the result.
    - Use thenAccept() when you just need to process the result without returning anything.
    - Use thenRun() when you don’t need the result and just want to run some final logic.
 */
public class CompletableFutureEx {

	public static void main(String args[]) throws InterruptedException, ExecutionException {
		// creating ocmpletableFuture
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception occured.");
			}
			return 1;
		});

//		Future<Void> f = cf.thenAccept(res -> System.out.println("Result " + res));
//		System.out.println("check flow");
//		f.get();

		// cf.thenRun(() -> System.out.println("Computation finished."));

		// Using with Executor service API
//		try {
//			calculateAsync().get();
//		} catch (Exception e) {
//			System.out.println("Exception occured.");
//		}

		// we use the thenCompose method to chain two Futures sequentially.
//        CompletableFuture<String> completableFuture
//                = CompletableFuture.supplyAsync(() -> "Hello")
//                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
//
//        assertEquals("Hello World", completableFuture.get());

//        Running Multiple Futures in Parallel
//
//		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
//		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
//		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");
//
//		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

		// exceptionally() is a method in CompletableFuture that allows you to handle
		// exceptions
		// that occur during asynchronous computation and provide a fallback result.

//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            throw new RuntimeException("Something went wrong!");
//        }).exceptionally(ex -> {
//            System.out.println("Handling exception: " + ex.getMessage());
//            return -1; // Provide a default value
//        });
//
//        System.out.println("Result: " + future.join());
	}

	private static Future<String> calculateAsync() throws InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		Executors.newCachedThreadPool().submit(() -> {
			Thread.sleep(500);
			completableFuture.complete("Hello");
			return null;
		});

		return completableFuture;
	}
}
