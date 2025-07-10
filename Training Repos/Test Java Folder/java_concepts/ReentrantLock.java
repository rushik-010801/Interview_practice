package java_concepts;

import java.util.concurrent.locks.Lock;

public class ReentrantLock {

	private static final ReentrantLock POE = new ReentrantLock();

	private static final int MAX_THRESHOLD = 100;
	private static final Thread ODD_PRINTING_THREAD = new Thread(() -> POE.printOdd());
	private static final Thread EVEN_PRINTING_THREAD = new Thread(() -> POE.printEven());

	Lock lock = new java.util.concurrent.locks.ReentrantLock();

	private static int currentNumber = 1;

	public static void main(String[] args) {
		ODD_PRINTING_THREAD.start();
		EVEN_PRINTING_THREAD.start();
	}

	public void printOdd() {
		try {
			synchronized (this) {
				while (currentNumber < MAX_THRESHOLD) {
					while (currentNumber % 2 == 0)
						wait();
					System.out.println("Odd: " + currentNumber++);
					notifyAll();
				}
			}
		} catch (Exception ex) {
			Thread.currentThread().interrupt();
			ex.printStackTrace();
		}
	}

	public void printEven() {
		try {
			synchronized (this) {
				while (currentNumber <= MAX_THRESHOLD) {
					while (currentNumber % 2 != 0)
						wait();
					System.out.println("Even: " + currentNumber++);
					notifyAll();
				}
			}
		} catch (Exception ex) {
			Thread.currentThread().interrupt();
			ex.printStackTrace();
		}
	}

}