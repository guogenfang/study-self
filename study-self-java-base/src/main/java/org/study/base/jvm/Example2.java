package org.study.base.jvm;

import java.lang.reflect.Field;

public class Example2 {
	public String str = "str";
	public String str1 = "str";
	public static void main(String[] args) {
		int a = 1;
		String bb = "111";
		str(bb);
		System.out.println(bb);
	}
	
	public static void str(String bb){
		try {
			Field field = Example2.class.getField("str");
			field.setAccessible(true);
			field.set(bb,"aaa");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
