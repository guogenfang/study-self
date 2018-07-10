package org.study.self.analyse.java.classFile;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadClass {

	public static void main(String[] args) throws Exception {
		String class_base_path = "F:\\git project\\sfy-zb-base-api\\target\\classes\\";
		load(class_base_path);
	}
	
	/**
	 * [简要描述]：将工程目录添加到JVM加载目录
	 * @author ggf
	 * @date 2017年5月18日
	 * @param class_base_path
	 * @throws Exception
	 */
	public static void load(String class_base_path) throws Exception {
		File clazzPath = new File(class_base_path);
		Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
		boolean accessible = method.isAccessible();
		try {
			if (accessible == false) {
				method.setAccessible(true);
			}
			URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			method.invoke(classLoader, clazzPath.toURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.setAccessible(accessible);
		}
	}
}
