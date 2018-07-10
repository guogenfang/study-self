package org.study.base.file.ReadClass;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReadClassFileTest {
	public static void main(String[] args) throws Exception {
		Gson gson = new GsonBuilder().serializeNulls().create();
		Class<?> cls = Class.forName("file.ReadClass.ReadJavaFileTestInterface");
		Method [] methods = cls.getMethods();
		Arrays.stream(methods).forEach(method -> {
			Class<?> returnCls = method.getReturnType();
			try {
				System.out.println("返回类型：" + returnCls.toString());
				if(returnCls.toString().endsWith("Integer")){
					System.out.println("整数类型");
				}
				else{
					Object object = returnCls.newInstance();
					Field [] fields = object.getClass().getDeclaredFields();
					System.out.println(fields.length);
					for (Field field : fields) {
						field.setAccessible(true);
						System.out.println("field:" + field.getType());
						Object val = field.get(object);//得到此属性的值 
						if(field.getType().toString().endsWith("Integer")){
							try {
								field.set(object, 124);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if(field.getType().toString().endsWith("String")){
							try {
								field.set(object, field.getName() + "_ggf");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if(field.getType().toString().endsWith("String")){
							try {
								field.set(object, field.getName() + "_ggf");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					System.out.println(gson.toJson(object));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
}
