package java_concepts;

public class StreamsExamples {

	public static void main(String args[]) {
		// Question 1 : Make a frequency map using Streams
//		Character[] arr = new Character[] { 'R', 'U', 'S', 'H', 'I', 'I' };
		// One method
//		Map<Character, Integer> map = new HashMap<>();
//		List<Integer> stream = Arrays.stream(arr).map(i -> map.put(i, map.getOrDefault(i, 0) + 1))
//				.collect(Collectors.toList());

		// 2nd method
//		Map<Character, Integer> map = new HashMap<>();
//		Arrays.stream(arr).forEach(i -> map.put(i, map.getOrDefault(i, 0) + 1));

		// 2nd method
//		Map<Character, Long> map = Arrays.stream(arr).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
//		System.out.println(map);

		// Question 2 : Given a list of integers, use streams to filter out all even
		// numbers and print them.
//		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
//
//		List<Integer> list = Arrays.stream(arr).boxed().filter(x -> x % 2 == 0).collect(Collectors.toList());
//		System.out.println(list);

		// Question 3 : Given a list of strings, convert all of them to uppercase using
		// streams and collect the result.
//		String[] arr = new String[] { "rushik", "kumar", "avula" };
//
//		List<String> ans = Arrays.stream(arr).map(x -> {
//			x = String.valueOf(x.charAt(0)).toUpperCase() + x.substring(1);
//			return x;
//		}).collect(Collectors.toList());
//		System.out.println(ans);

		// Question 4 : Use streams to find and print the first element in a list of
		// integers.
//		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
//		int k = 9;
//
//		int ans = Arrays.stream(arr).filter(x -> x == k).findFirst().orElse(-1);
//		System.out.println(ans);

//		//for return index
//		int index = IntStream.range(0, arr.length)
//				.filter(i -> arr[i] == k)
//				.findFirst()
//				.orElse(-1); // Returns -1 if not found

		// Question 5 : Given a list (or an array) of integers, use streams to count how
		// many elements are present.
//        List<Integer> arr = List.of(1, 2, 3, 4, 5, 6, 7, 8);
//
//        int ans = arr.stream().reduce(0, (a, b) -> a + b);
//        System.out.println(ans);

		// 2nd Method
//        int[] numbers = {1, 2, 3, 4, 5};
//
//        int sum = IntStream.of(numbers).sum(); // Efficient sum calculation
//        System.out.println(sum); // Output: 15

		// Question 6 : Using streams, find the maximum and minimum values in a list of
		// integers.
//		List<Integer> arr = List.of(1, 2, 3, 4, 5, 6, 7, 8);
//
//		// using terminal operations
//		int max = arr.stream().reduce(Integer.MIN_VALUE, Integer::max);
//		System.out.println(max);
//		int min = arr.stream().reduce(Integer.MAX_VALUE, Integer::min);
//		System.out.println(min);

		// Question 7 : Given an array of integers, remove all duplicate values using
		// streams and return the distinct elements.
//        List<Integer> arr = List.of(1, 2, 3, 2, 1, 6, 7, 6);

//		List<Integer> ans = arr.stream().distinct().collect(Collectors.toList());
//		System.out.println(ans);

		// Question 8 :Sort a list of strings in both ascending and descending order
		// using stream operations.
//		List<Integer> ans = arr.stream().sorted().collect(Collectors.toList());
//		System.out.println(ans);

//		List<Integer> ans = arr.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
//		System.out.println(ans);

		// Quesiton 9 :Use streams to calculate the average of a list of integers.
//        double avg = arr.stream().mapToInt(x -> x).average().orElse(0.0);
//        System.out.println(avg);

		// Question 10 : Given a string, count the number of occurrences of each
		// character using the Stream API (consider ignoring case or spaces as needed).
//		String a = "rushik kumar";

//		Map<Character, Long> map = IntStream.range(0, a.length()).map(i -> Character.valueOf(a.charAt(i)))
//				.mapToObj(c -> Character.toLowerCase((char) c)).filter(ch -> !Character.isWhitespace(ch))
//				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));

//		Map<Character, Integer> map = IntStream.range(0, a.length()).map(i -> Character.valueOf(a.charAt(i)))
//				.mapToObj(c -> Character.toLowerCase((char) c)).filter(ch -> !Character.isWhitespace(ch))
//				.collect(Collectors.toMap(str -> str, str -> 1, (map1, map2) -> map1 + map2));
//		System.out.println(map);

		// Question 11 : Write a program that uses streams to convert a list of strings
		// into a map where each key is a string and the value is its length.
//		List<String> list = List.of("a", "abs", "as", "abs");
//
//		Map<String, Integer> map = list.stream()
//				.collect(Collectors.toMap(str -> str, str -> str.length(), (map1, map2) -> map1));
//		System.out.println(map);

		// Question 12 : From an array of strings, use streams to filter and return only
		// those strings that start with a given prefix.
//		List<String> list = List.of("b", "abs", "as", "abc", "ab");
//		String a = "ab";
//
//		List<String> ans = list.stream().filter(str -> str.startsWith(a)).collect(Collectors.toList());
//		System.out.println(ans);

		// Question 13 : Given a list of strings, use streams to group the strings by
		// their first character and count how many strings fall under each group.
//		List<String> list = List.of("b", "bs", "as", "bc", "ab");

//		Map<Character, Integer> map = list.stream().map(str -> Character.toLowerCase(str.charAt(0)))
//				.collect(Collectors.toMap(ch -> ch, ch -> 1, Integer::sum));
//		Map<Character, Long> map = list.stream().map(str -> Character.toLowerCase(str.charAt(0)))
//				.collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
//		System.out.println(map);

		// Question 14 : Given a list of sentences, use streams to split each sentence
		// into words and then flatten the result into a single list of words.
//		List<String> s = List.of("rushik kumar avula", "is my", "name");
//
//		List<String> ans = s.stream().flatMap(sentence -> Arrays.stream(sentence.split(" ")))
//				.collect(Collectors.toList());
//		System.out.println(ans);

		// Question 15 : Given an array of integers, use streams to find the second
		// highest value.
//		List<Integer> arr = List.of(1, 2, 3, 2, 1, 6, 7, 6);
//
//		int ans = arr.stream().sorted(Collections.reverseOrder()).distinct().skip(1).findFirst().orElse(-1);
//		System.out.println(ans);

		// Question 16 : Write a program to concatenate two streams (for example, two
		// lists of strings) into one stream and then print the combined stream.
//		List<String> list1 = Arrays.asList("Java", "Streams", "are");
//		List<String> list2 = Arrays.asList("powerful", "and", "fun");
//
//		Stream<String> ans = Stream.concat(list1.stream(), list2.stream());
//		ans.forEach(System.out::println);

		// Using Parallel Streams
//        List<String> list1 = Arrays.asList("Java", "Parallel", "Streams");
//        List<String> list2 = Arrays.asList("are", "cool", "indeed");
//
//        // Convert both lists to parallel streams and then concatenate them
//        Stream<String> combinedStream = Stream.concat(
//                list1.parallelStream(), list2.parallelStream());
//
//        // forEachOrdered is used to maintain the encounter order even in parallel streams.
//        combinedStream.forEachOrdered(System.out::println);

		// Question 17 : Find the Longest String in a List: Given a list of strings,
		// determine the longest string using Java Streams.
//        List<String> list = Arrays.asList("Java", "Parallel", "Streams");
//
//        String ans = list.stream().reduce("", (a, b) -> a.length() > b.length() ? a : b);
//        System.out.println(ans);

		// Question 18 : Calculate the Average Age: Given a list of Person objects with
		// name and age attributes, compute the average age using Streams.
//		List<Person> list = List.of(new Person("A", 2), new Person("B", 5), new Person("C", 7));
//
//		double avg = list.stream().mapToInt(person -> person.age).average().orElse(-1);
//		System.out.println(avg);

		// Question 19 : Partition given list into even and odd using streams
		// partitionBy
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//		Map<Boolean, List<Integer>> partitioned = numbers.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
//
//		System.out.println(partitioned.get(true));
//		System.out.println(partitioned.get(false));
	}

//	public record Person(String name, int age) {
//	};
}
