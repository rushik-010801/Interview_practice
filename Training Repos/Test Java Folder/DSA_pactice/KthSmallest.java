package DSA_pactice;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallest {
	public static void main(String[] args) {
		// [7, 10, 4, 3, 20, 15], k = 3
		int arr[] = new int[] { 7, 10, 4, 3, 20, 15 };
		int k = 3;
		System.out.println(kthSmallest(arr, k));
	}

	public static int kthSmallest(int[] arr, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.reverseOrder());
		for (int temp : arr) {
			if (queue.size() == k) {
				if (queue.peek() > temp) {
					queue.remove();
					queue.add(temp);
				}
			} else {
				queue.add(temp);
			}
		}
		return queue.remove();
	}
}
