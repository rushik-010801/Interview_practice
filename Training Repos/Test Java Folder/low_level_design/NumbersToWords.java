package low_level_design;

import java.util.HashMap;
import java.util.Map;

/*
	Question : To convert the number to words. Number would be of size 6 digit
 */
public class NumbersToWords {

	public static final String THOUSAND = "Thousand";
	public static final String HUNDRED = "Hundred";

	private static final Map<Integer, String> wordMap = getMap();

	public static void main(String args[]) {
		System.out.println(printWords(23));
		System.out.println(printWords(19));
		System.out.println(printWords(303030));
		System.out.println(printWords(555555));
		System.out.println(printWords(123456));
		System.out.println(printWords(3123));
		System.out.println(printWords(100003));
		System.out.println(printWords(400));
	}

	private static String printWords(int num) {
		if (num < 100)
			return getWordFor2Digit(num);
		StringBuilder ans = new StringBuilder();
		int divider;
		divider = num > 999 ? 1000 : 100;
		boolean isFirst = true;
		while (num != 0) {
			int mod = num % divider;
			String subset = printWords(mod);
			if (!subset.isBlank()) {
				if (isFirst) {
					ans.insert(0, " " + subset);
					isFirst = false;
				} else {
					ans.insert(0, subset + " " + (divider == 1000 ? THOUSAND : HUNDRED));
				}
			} else
				isFirst = false;
			num = num / divider;
		}

		return ans.toString();
	}

	private static String getWordFor2Digit(int num) {
		if (wordMap.containsKey(num))
			return wordMap.get(num);
		else {
			int unitPlace = num % 10;
			int tenPlace = (num / 10) * 10;
			return wordMap.get(tenPlace) + " " + wordMap.get(unitPlace);
		}
	}

	private static Map<Integer, String> getMap() {
		Map<Integer, String> numberMap = new HashMap<>();

		// Mapping numbers from 0 to 20
		numberMap.put(0, "");
		numberMap.put(1, "One");
		numberMap.put(2, "Two");
		numberMap.put(3, "Three");
		numberMap.put(4, "Four");
		numberMap.put(5, "Five");
		numberMap.put(6, "Six");
		numberMap.put(7, "Seven");
		numberMap.put(8, "Eight");
		numberMap.put(9, "Nine");
		numberMap.put(10, "Ten");
		numberMap.put(11, "Eleven");
		numberMap.put(12, "Twelve");
		numberMap.put(13, "Thirteen");
		numberMap.put(14, "Fourteen");
		numberMap.put(15, "Fifteen");
		numberMap.put(16, "Sixteen");
		numberMap.put(17, "Seventeen");
		numberMap.put(18, "Eighteen");
		numberMap.put(19, "Nineteen");
		numberMap.put(20, "Twenty");

		// Mapping multiples of ten
		numberMap.put(30, "Thirty");
		numberMap.put(40, "Forty");
		numberMap.put(50, "Fifty");
		numberMap.put(60, "Sixty");
		numberMap.put(70, "Seventy");
		numberMap.put(80, "Eighty");
		numberMap.put(90, "Ninety");

		return numberMap;
	}
}
