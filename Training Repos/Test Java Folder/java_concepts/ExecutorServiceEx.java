package java_concepts;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ThreadExecutor implements Callable<Void> {

	@Override
	public Void call() throws Exception {

		Random rand = new Random();
		int randIndex = rand.nextInt(7);
		int randNum = randIndex * 1000;

		long threadId = Thread.currentThread().getId();
		Thread.sleep(randNum);
		System.out.println("Thread Id : " + threadId);
		return null;
	}
}

public class ExecutorServiceEx {

	public static void main(String args[]) {
		// ExecutorService executorService1 = Executors.newSingleThreadExecutor();
		// Creates a ExecutorService object having a single thread.

		// ExecutorService executorService2 = Executors.newScheduledThreadPool(10);
		// Creates a scheduled thread pool executor with 10 threads.
		// In scheduled thread pool, we can schedule tasks of the threads

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		/*
		 * Submission of Threads to Executor Service execute(Runnable task)
		 *
		 * submit(Runnable task) / submit(Callable<T> task) : Return Future unlinke
		 * Execute, which return void.
		 *
		 * invokeAny(Collection<?extends Callable<T>> tasks) : This method returns the
		 * future object of the callable object which gets successfully executed first.
		 *
		 * invokeAll(Collection<? extends Callable<T>> tasks) :The invokeAll() method
		 * takes in a Collection of Callable objects having tasks and returns a list of
		 * Future objects containing the result of all the tasks.
		 */

		ThreadExecutor te = new ThreadExecutor();
		Future<Void> futures[] = new Future[5];
		for (int i = 0; i < 5; i++) {
			futures[i] = executorService.submit(te);
		}

		executorService.shutdown();

	}

}
