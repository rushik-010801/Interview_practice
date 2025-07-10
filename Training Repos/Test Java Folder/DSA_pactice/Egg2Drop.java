package DSA_pactice;

import java.util.Arrays;

public class Egg2Drop {
	public static void main(String[] args) {
		twoEggDrop(5);
	}

	public static int twoEggDrop(int n) {
		int[][] dp = new int[n + 1][2];
		return twoEggDrop(n, 2, dp);
	}

	private static int twoEggDrop(int n, int egg, int[][] dp) {
		if (egg == 1)
			return n;
		if (n == 1 || n == 0)
			return n;

		if (dp[n][egg - 1] != 0) {
			for (int i = 0; i < dp.length; i++)
				System.out.print(Arrays.toString(dp[i]));
			System.out.println();
			return dp[n][egg - 1];
		}
		int ans = Integer.MAX_VALUE;

		for (int f = 1; f <= n; f++) {
			int Break = twoEggDrop(f - 1, egg - 1, dp) + 1;
			int notBreak = twoEggDrop(n - f, egg, dp) + 1;
			ans = Math.min(ans, Math.max(Break, notBreak));
		}
		dp[n][egg - 1] = ans;
		return ans;
	}
}
