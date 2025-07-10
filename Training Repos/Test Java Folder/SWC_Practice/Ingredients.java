package SWC_Practice;

/*
 FYI : how to take input using Buffered Reader : (which will be helpful while test)

 BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
 String in = input.readLine();

 */

/*
Question:

Note : try these questions without using any exisiting libraries

Input: Given T test cases.

For each test cases, k is given. k is the number of ingredients.

Following k, there are k lines. Each line has 3 numbers. They represent protein, fat and carbohydrate content in the kth ingredient.

Following these k lines, 3 lines are given. Each line has 3 numbers. They represent protein, fat and carbohydrate. For these 3 targets, we need to find out the ingredients to be added to exactly match the target.

Output: for the 3 lines in each test case, print space separated numbers denoting the ingredients to be added to get the exact match.
The ingredients must be printed in the sorted order. If there are many combinations, print the one which minimum number of ingredients.

Sample input/output:

1              //testcases
5             // 5 ingredients
1 2 3       //1st ingredient
4 5 6      //2nd ..
7 8 9
9 18 12
5 7 9       //5th ingredient
5 7 9       //1st target
11 13 15  //2nd target
52 14 3  // 3rd target

Expected output:

5
2 3
-1

Note: -1, when no combination(s) found


 */

import java.util.Scanner;

public class Ingredients {

	static class Ingredient {

		int protein;
		int fats;
		int carbs;

		public Ingredient(int protein, int fats, int carbs) {
			this.protein = protein;
			this.fats = fats;
			this.carbs = carbs;
		}
	};

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of testcases : ");
		int k = Integer.parseInt(sc.nextLine());
		Ingredient[] ingredients;
		for (int t = 0; t < k; t++) {
			int n = Integer.parseInt(sc.nextLine());
			ingredients = new Ingredient[n];
			for (int j = 0; j < n; j++) {
				String[] ingredient = sc.nextLine().split(" ");
				Ingredient ing = new Ingredient(Integer.parseInt(ingredient[0]), Integer.parseInt(ingredient[1]),
						Integer.parseInt(ingredient[2]));
				ingredients[j] = ing;
			}

			Ingredient[] targets = new Ingredient[3];
			for (int i = 0; i < 3; i++) {
				String[] ingredient = sc.nextLine().split(" ");
				Ingredient ing = new Ingredient(Integer.parseInt(ingredient[0]), Integer.parseInt(ingredient[1]),
						Integer.parseInt(ingredient[2]));
				targets[i] = ing;
			}

			for (Ingredient target : targets) {
				String ans = getMixture(ingredients, target);
				System.out.println(ans);
			}
		}
	}

	private static String getMixture(Ingredient[] ingredients, Ingredient target) {
		Answer ans = new Answer(new int[ingredients.length], 0, new int[ingredients.length], 0);

		getMixture(ingredients, target, 0, ans);

		if (ans.minLen == 0)
			return String.valueOf(-1);

		StringBuilder str = new StringBuilder();
		for (int a = 0; a < ans.minLen; a++) {
			str.append(ans.minAns[a] + 1);
			str.append(" ");
		}
		return str.toString().trim();
	}

	private static void getMixture(Ingredient[] ingredients, Ingredient rem, int start, Answer ans) {
		if (start == ingredients.length) {
			if (checkMatch(rem))
				updateAns(ans);
		} else if (checkMatch(rem))
			updateAns(ans);
		else if (checkNegativeMatch(rem)) {
			return;
		} else {
			for (int i = start; i < ingredients.length; i++) {
				updateRem(rem, ingredients[i], ans, i);
				getMixture(ingredients, rem, i + 1, ans);
				reUpdateRem(rem, ingredients[i], ans);
			}
		}
	}

	private static void updateRem(Ingredient rem, Ingredient selected, Answer ans, int index) {
		rem.protein -= selected.protein;
		rem.fats -= selected.fats;
		rem.carbs -= selected.carbs;
		ans.ans[ans.len] = index;
		ans.len++;
	}

	private static void reUpdateRem(Ingredient rem, Ingredient selected, Answer ans) {
		rem.protein += selected.protein;
		rem.fats += selected.fats;
		rem.carbs += selected.carbs;
		ans.len--;
	}

	private static boolean checkMatch(Ingredient rem) {
		return rem.carbs == 0 && rem.fats == 0 && rem.protein == 0;
	}

	private static boolean checkNegativeMatch(Ingredient rem) {
		return rem.carbs < 0 && rem.fats < 0 && rem.protein < 0;
	}

	private static void updateAns(Answer ans) {
		if (ans.minLen == 0 || ans.minLen > ans.len) {
			for (int i = 0; i < ans.len; i++) {
				ans.minAns[i] = ans.ans[i];
			}
			ans.minLen = ans.len;
		}
	}

	static class Answer {
		int[] ans;
		int len;
		int[] minAns;
		int minLen;

		Answer(int[] ans, int len, int[] minAns, int minLen) {
			this.ans = ans;
			this.len = len;
			this.minAns = minAns;
			this.minLen = minLen;
		}
	};
}
