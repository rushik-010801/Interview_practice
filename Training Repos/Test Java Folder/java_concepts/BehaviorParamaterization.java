package java_concepts;

import java.util.ArrayList;
import java.util.List;

public class BehaviorParamaterization {
	public static void main(String[] args) {

		System.out.println(filterApple(List.of(new Apple(10, 0, "")), apple -> apple.weight > 50));
		System.out.println(filterApple(List.of(new Apple(10, 0, "")), apple -> apple.color == 0));
	}

	public static List<Apple> filterApple(List<Apple> apples, AppTest predicate) {

		List<Apple> filteredApple = new ArrayList<>();

		for (Apple apple : apples) {
			if (predicate.test(apple)) { // if codition; testing changes
				filteredApple.add(apple);
			}
		}
		return filteredApple;
	}

	interface AppTest {
		boolean test(Apple apple);
	}

	static class WeightBased implements AppTest {
		@Override
		public boolean test(Apple apple) {
			return true;
		}
	}

	private static class Apple {

		private final int weight;
		private final int color;
		private final String desc;

		public Apple(int weight, int color, String desc) {
			this.weight = weight;
			this.color = color;
			this.desc = desc;
		}

		public int getWeight() {
			return weight;
		}

		public int getColor() {
			return color;
		}

		public String getDesc() {
			return desc;
		}
	}

}