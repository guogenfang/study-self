package org.study.base.reflact;

import java.lang.reflect.Field;

public class Test {
	public static void main(String[] args) throws Exception {
		FieldRef ref = new FieldRef();
		Field a1Field = FieldRef.class.getDeclaredField("a1");
		a1Field.setAccessible(true);
		System.out.println(a1Field.get(ref));
		
	}
}
