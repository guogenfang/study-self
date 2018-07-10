package org.study.base.reflact;

import java.lang.reflect.Field;

public class FieldRef {
	
	public static void getAllParam() throws Exception{
		TempObj ref = new TempObj("getAllParam");
		Field[] method = ref.getClass().getDeclaredFields();
		for (Field field : method) {
			field.setAccessible(true);
			System.out.println(field.toString() + ",," + field.get(ref));
		}
	}
	
	public static void getAllPublicParam() throws Exception{
		TempObj ref = new TempObj("getAllPublicParam");
		Field[] method = ref.getClass().getFields();
		for (Field field : method) {
			System.out.println(field.toString() + ",," + field.get(ref));
		}
	}
	
	public static void getPrivateParam() throws Exception{
		TempObj ref = new TempObj("getPrivateParam");
		Field field = ref.getClass().getDeclaredField("str1");
		field.setAccessible(true);
		System.out.println(field.toString() + ",," + field.get(ref));
	}
	
	public static void getPublicParam() throws Exception{
		TempObj ref = new TempObj("getPublicParam");
		Field field = ref.getClass().getField("str3"); //getField 只能获取共有的变量方法
		System.out.println(field.toString() + ",," + field.get(ref));
	}

	public static void main(String[] args) throws Exception {
		getAllParam();
		getAllPublicParam();
		getPublicParam();
		getPrivateParam();
	}

}
