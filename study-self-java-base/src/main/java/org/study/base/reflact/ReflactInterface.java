package org.study.base.reflact;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflactInterface {
	public static void main(String[] args) throws Exception {
		Class class1 = Class.forName("reflact.TestInterface");
		Method[] methods = class1.getMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
			Arrays.asList(method.getParameters()).forEach(param -> {
				System.out.println(param.getType());
			});
		}
		System.out.println(class1.getMethod("del", Integer[].class));
	}
}
