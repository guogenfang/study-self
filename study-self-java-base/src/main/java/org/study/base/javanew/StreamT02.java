package org.study.base.javanew;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;
import org.study.base.javanew.entity.User;

public class StreamT02 {

	public static void print(String str) {
		System.out.println(str);
	}

	public static Integer compareSize(String a, String b) {
		return a.length() - b.length();
	}

	public static Integer compareIntPositive(Integer a, Integer b) {
		return a - b;
	}

	public static Integer compareIntNegnative(Integer a, Integer b) {
		return -(a - b);
	}

	@Test
	public void readFile01() {
		try (Stream<String> lines = Files.lines(Paths.get("G:\\theme-66.xml"))) {
			lines.forEach(StreamT02::print);
			// System.out::println
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void readFile02() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("G:\\theme-66.xml"));
			br.lines().forEach(line -> {
				System.out.println(line);
			});
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void listStringSort() {
		List<String> list = Arrays.asList("a1", "abcdef", "ab2", "abcdf", "ab1", "a2");
		System.out.println("--------list按照长度排序---------");
		list.stream().sorted(StreamT02::compareSize).forEach(System.out::println);
		System.out.println(list.stream().sorted(StreamT02::compareSize).findFirst().get());

		System.out.println("--------list 最长字符串---------");
		System.out.println(list.stream().max(StreamT02::compareSize).get());

		System.out.println("--------list 最长字符串的长度---------");
		System.out.println(list.stream().mapToInt(String::length).max().getAsInt());
	}

	@Test
	public void listIntSort() {
		List<Integer> list = Arrays.asList(9, 17, 20, 8, 30, 16, 13, 19, 3);
		System.out.println("--------list 按照大小排序---------");
		List<Integer> res = list.stream().filter(a -> a > 10).sorted(StreamT02::compareIntNegnative).limit(3)
				.collect(Collectors.toList());

		System.out.println(res);

		Integer max = list.stream().max(StreamT02::compareIntPositive).get();
		System.out.println("最大值：" + max);

		Stream.of(new String[] { "GFGDFadfEE", "cCCRdERfGD" }).map(String::toLowerCase).forEach(System.out::println);
	}

	@Test
	public void randomInt() {
		Stream.iterate(0, n -> n + 3).limit(10).forEach(x -> System.out.print(x + " "));
		System.out.println();
		Stream.generate(() -> (int) (Math.random() * 100)).limit(10).forEach(x -> System.out.print(x + " "));
		System.out.println();
		IntStream.generate(() -> (int) (Math.random() * 100)).limit(10).forEach(x -> System.out.print(x + " "));
	}

	@Test
	public void group() {
		Map<Integer, List<User>> personGroups = Stream.generate(User::new).limit(100)
				.collect(Collectors.groupingBy(User::getAge));
		Iterator<?> it = personGroups.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, List<Person>> persons = (Map.Entry) it.next();
			System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
		}
	}

	@Test
	public void flatMapDemo() {
		Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
		Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
		outputStream.forEach(System.out::println);
	}

	public static void main(String[] args) throws IOException {

	}
}
