package org.study.base.javanew;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class Lambda {

	@Test
	public void arrayEach() {
		List<String> nameList = Arrays.asList("peter", "anna", "mike", "xenia");
		/*
		 * Collections.sort(nameList, new Comparator<String>() {
		 * 
		 * @Override public int compare(String a, String b) { return
		 * b.compareTo(a); } });
		 */
		Collections.sort(nameList, (a, b) -> b.compareTo(a));

		nameList.forEach(s -> {
			System.out.println(s);
		});
	}

	public void functionalInterface() {
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Converter<String, Integer> converter1 = Integer::valueOf;
		Integer converted = converter.convert("123");
		System.out.println(converted); // 123
	}

	@Test
	public void constructorReferences() {
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
	}

	@Test
	public void scope() {
		final int num = 1;
		Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
		stringConverter.convert(2);
	}
}

@FunctionalInterface
interface Converter<F, T> {
	T convert(F from);
}

class Person {
	String firstName;
	String lastName;

	Person() {
	}

	Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}

interface PersonFactory<P extends Person> {
	P create(String firstName, String lastName);
}
