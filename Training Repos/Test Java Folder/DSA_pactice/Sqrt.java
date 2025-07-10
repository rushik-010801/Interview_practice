package DSA_pactice;

public class Sqrt {

	public static void main(String[] args) {
		System.out.println(mySqrt(34));
	}

	public static int mySqrt(int x) {
		if (x == 0)
			return 0;
		int left = 1;
		int right = x / 2;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			System.out.println(left + " " + right + " " + mid * mid + " " + mid);
			if ((long) x == (long) mid * mid) {
				return mid;
			} else if ((long) x > (long) mid * mid) {

				left = mid + 1;
			} else {

				right = mid - 1;
			}
		}

		return Math.round(right);
	}
}
