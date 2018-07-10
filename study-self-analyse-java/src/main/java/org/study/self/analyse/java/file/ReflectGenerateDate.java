package org.study.self.analyse.java.file;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.study.self.analyse.java.model.InterfaceModel;
import org.study.self.analyse.java.model.ReturnModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * [简要描述]：
 * 
 * @author ggf
 * @data 2017年5月20日
 */
public class ReflectGenerateDate {
	public static Gson gson = new GsonBuilder().serializeNulls().create();

	/**
	 * [简要描述]：生成返回数据对象
	 * @author ggf
	 * @date 2017年5月20日
	 * @param url
	 * @throws Exception
	 */
	public static void getMethodReturnObj(String url) throws Exception {
		Class<?> cls = Class.forName(url);
		Method[] methods = cls.getMethods();
		Arrays.stream(methods).forEach(method -> {
			Class<?> returnCls = method.getReturnType();
			try {
				Object object = null;
				//System.out.println(method.getName() + "返回类型：" + method.getGenericReturnType());
				if (method.getGenericReturnType().toString().endsWith("void")) {
					object = new Object();
				} else if (returnCls.toString().endsWith("Integer") || returnCls.toString().endsWith("int")) {
					object = 123;
				} else if (returnCls.toString().toLowerCase().endsWith("boolean")) {
					object = true;
				} else if (returnCls.toString().endsWith("Map")) {
					Map<Object, Object> map = new HashMap<>();
					map.put("obj", "ggf");
					object = map;
				} else if (returnCls.toString().endsWith("List")) {
					List list = new ArrayList<>();
					String type = method.getGenericReturnType().toString();
					if (type.contains("<")) {
						type = type.split("<")[1].split(">")[0];
					}
					// 检验泛型类型 Integer无法通过newInstnce创建
					if (type.contains("Integer")) {
						list.add(123);
					} else {
						Object typeObj = Class.forName(type).newInstance();
						setObjFields(typeObj);
						list.add(typeObj);
					}
					object = list;
				} else {
					object = returnCls.newInstance();
					setObjFields(object);
				}
				WriteFile.generateFile("F://jsonData//" + url + "." + method.getName() + ".json",
						gson.toJson(new ReturnModel(object)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * [简要描述]：设置生成的对象属性
	 * @author ggf
	 * @date 2017年5月20日
	 * @param object
	 */
	public static void setObjFields(Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getType().toString().endsWith("Integer")) {
				try {
					field.set(object, 123);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (field.getType().toString().endsWith("String")) {
				try {
					field.set(object, field.getName() + "_ggf");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (field.getType().toString().endsWith("String")) {
				try {
					field.set(object, field.getName() + "_ggf");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * [简要描述]：通过反射获取方法返回数据类型，生成返回数据
	 * @author ggf
	 * @date 2017年5月20日
	 * @param interfaceModels
	 * @param savePath
	 */
	public static void generateData(List<InterfaceModel> interfaceModels, String savePath) {
		for (InterfaceModel model : interfaceModels) {
			try {
				getMethodReturnObj(model.getUrl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
