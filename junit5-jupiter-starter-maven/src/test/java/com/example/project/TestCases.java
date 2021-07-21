package com.example.project;
import java.util.regex.*;  
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TestCases {
	class Dog {
		private String name;
		private int height;
		private int weight;

		public Dog(String n, int s, int w) {
			this.setName(n);
			this.setHeight(s);
			this.setWeight(w);
		}

		public String toString() {
			return getName() + ": size=" + getHeight() + " weight=" + getWeight() + " \n";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		// setters and getters ...
	}

	@Test
	void test1() {
		// create an array of dogs
		Dog d1 = new Dog("Max", 2, 50);
		Dog d2 = new Dog("Rocky", 1, 30);
		Dog d3 = new Dog("Bear", 3, 40);
		Dog[] dogArray = { d1, d2, d3 };

		// use stream to sort
		Stream<Dog> dogStream = Stream.of(dogArray);
		Stream<Dog> sortedDogStream = dogStream.sorted((Dog m, Dog n) -> Integer.compare(m.getHeight(), n.getHeight()));

		sortedDogStream.forEach(d -> System.out.print(d));
	}

	@ParameterizedTest(name = "Year {0} is a leap year.")
	@DisplayName("Testing Parameterized1")
	@ValueSource(ints = { 2016, 2020, 2048 })
	void testParameter1(Integer year) {
	}
	
	@ParameterizedTest(name = "Year {0} is a leap year.")
	@DisplayName("Testing Parameterized2")
	@MethodSource(value="getParameters")
	void testParameter2(Integer year) {
	}
	
	static int[] getParameters() {
		return new int[]{2016, 2020, 2048};
	}

	@Test
	void test3() {
		List<Integer> s = Arrays.asList(1, 2, 3);
		s.forEach(System.out::println);
		Predicate<Integer> p = n -> n >= 3;
		for (Integer ss : s) {
			if (p.test(ss)) {
				System.out.println("larger than 3: " + ss);
			}
		}
		List<String> strList = Arrays.asList("abc", "ABC", "bcd", "acd", "bbd");
		String[] strArray = strList.toArray(new String[0]);

		Arrays.asList(strArray).stream().forEachOrdered(System.out::println);

		System.out.println("----START");
		Arrays.sort(strArray, (String s1, String s2) -> {
			return s1.compareToIgnoreCase(s2);
		});
		Arrays.asList(strArray).forEach(System.out::println);
		System.out.println("----END");

		Map<String, List<String>> groupedList = Arrays.asList(strArray).stream()
				.collect(Collectors.groupingBy(w -> w.toUpperCase()));
		for (String k : groupedList.keySet()) {
			Arrays.asList(groupedList.get(k).toArray()).forEach(x -> System.out.printf("%s,", x));
			System.out.println("");
		}

	}

	@Test
	@DisplayName("Testing JadenCae")
	void TestJadenCase() {
		Assertions.assertEquals("Test Jaden Case", this.toJadenCase("test jaden case"));
		Assertions.assertEquals(null, this.toJadenCase(null));
		Assertions.assertEquals(null, this.toJadenCase(""));
	}

	public String toJadenCase(String phrase) {
		if (phrase == null || phrase == "")
			return null;
		return Arrays.stream(phrase.split(" ")).map(x -> x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase())
				.collect(Collectors.joining(" "));
	}

	public static long newAvg(double[] arr, double navg) {
		DoubleSummaryStatistics stat = Arrays.stream(arr).summaryStatistics();
		double result = navg * (stat.getCount() + 1) - stat.getSum();
		double roundResult = Math.ceil(result);
		if (roundResult <= 0)
			throw new IllegalArgumentException();
		return (long)roundResult;
	}

	@RepeatedTest(10)
	@DisplayName("Testing HTML")
	void indent() {
		Assertions.assertEquals("<br>\n<br>\n<br>\n", TestCases.indent("<br><br><br>"));
	}

    public static String indent(String source) {
        String temp = source.replaceAll("<[\\w][\\w\\d /]*?>","$0\n");
        return temp;
    }

}
