package org.study.base.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class User implements Comparable<User>{
	private int age;
	private String name;
	
	public User() {}
	
	public User(int age,String name) {
		this.age = age;
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "age:" + age + ",name:" + name;
	}

	public int compareTo(User o) {
		System.out.println(this.toString());
		System.out.println(o.toString());
		int result = 0;
		if(this.getAge() > o.getAge()){
			result = this.age - o.getAge();
		}
		if(o.getAge() == this.getAge()){
			result = this.getName().compareTo(o.getName());
		}
		return result;
	}
}

public class Compare {
	public static void main(String[] args) {
		User u1 = new User(9,"cgf");
		User u2 = new User(10,"agf");
		User u3 = new User(9,"bgf");
		User u4 = new User(11,"agf");
		List<User> list = new ArrayList<User>();
		list.add(u1);
		list.add(u2);
		list.add(u3);
		list.add(u4);
		Collections.sort(list);
		for (User uu : list) {
			System.out.println("---" + uu.toString());
		}
	}
}
