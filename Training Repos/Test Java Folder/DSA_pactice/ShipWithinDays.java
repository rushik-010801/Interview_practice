package DSA_pactice;

public class ShipWithinDays {

	public int shipWithinDays(int[] weights, int days) {
		int max = weights[0];
		int sum = 0;
		for (int weight : weights) {
			max = Math.max(weight, max);
			sum += weight;
		}

		if (days == weights.length)
			return max;

		int low = max, high = sum, ans = high;
		while (low < high) {
			int mid = (low + high) / 2;
			int oneAns = getDays(mid, weights);
			if (oneAns <= days) {
				ans = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}

	private static int getDays(int capacity, int[] weights) {
		int days = 1, curCap = 0;
		for (int weight : weights) {
			if (curCap + weight > capacity) {
				days++;
				curCap = weight;
			} else {
				curCap += weight;
			}
		}
		return days;
	}

}
