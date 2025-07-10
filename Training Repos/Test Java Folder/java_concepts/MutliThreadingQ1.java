package java_concepts;

/*

You have an integer array of size N, and you need to compute the sum of all elements in parallel using multithreading.
Sample Input :
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int numThreads = 3;

Sample Output :
    Sum of array: 55

 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Adder implements Callable<Integer> {
	private final int[] arr;
	private final int start;
	private final int end;

	Adder(int[] arr, int start, int end) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() {
		int sum = 0;
		for (int i = start; i < end; i++) {
			sum += arr[i];
		}
		System.out.println("Thread " + Thread.currentThread().getId() + " computed sum: " + sum);
		return sum;
	}
}

public class MutliThreadingQ1 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int numThreads = sc.nextInt();
		sc.close();

		ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

		List<Future<Integer>> futures = new ArrayList<>();

		// Calculate chunk size
		int chunkSize = (int) Math.ceil(n / (double) numThreads);
		for (int i = 0; i < numThreads; i++) {
			int start = i * chunkSize;
			int end = Math.min(start + chunkSize, n); // Ensure last chunk covers remaining elements
			futures.add(executorService.submit(new Adder(arr, start, end)));
		}

		// Compute total sum by retrieving results from Future
		int totalSum = 0;
		for (Future<Integer> future : futures) {
			totalSum += future.get(); // Blocking call, waits for thread completion
		}

//		CompletableFuture<Integer>[] futures = new CompletableFuture[threads];
//
//		int start = 0;
//		for (int i = 0; i < threads; i++) {
//			int end = Math.min(start + size, n); // Ensure last chunk covers remaining elements
//			int chunkStart = start, chunkEnd = end;
//
//			futures[i] = CompletableFuture.supplyAsync(() -> {
//				int sum = 0;
//				for (int j = chunkStart; j < chunkEnd; j++) {
//					sum += arr[j];
//				}
//				System.out.println("Thread " + Thread.currentThread().getId() + " computed sum: " + sum);
//				return sum;
//			}, executorService);
//
//			start = end;
//		}
//
//		// Combine results from all futures
//		int totalSum = CompletableFuture.allOf(futures).thenApply(v -> {
//			int sum = 0;
//			for (CompletableFuture<Integer> f : futures) {
//				try {
//					sum += f.get(); // Collecting sum from each future
//				} catch (InterruptedException | ExecutionException e) {
//					e.printStackTrace();
//				}
//			}
//			return sum;
//		}).get();

		System.out.println("Total Sum: " + totalSum);

		executorService.shutdown();
	}
}