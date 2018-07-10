package org.study.base.file.ReadClass;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 1、加载 目录下的所有.class文件、方法
 * 2、生成jsAPI
 * @author ggf
 *
 */
public class ScanClass {

	public static ArrayList<String> filelist = new ArrayList<String>();
	public static String filePath = "F:\\git project\\platform-parent\\platform-service-api\\target\\classes\\";
	public static String head = "var $route = {};\r\n var baseUrl = 'http://localhost:8080/?';\r\n";

	public static void main(String[] args) throws Exception {
		getFiles(filePath);
		loadClass();
		Arrays.stream(filelist.toArray()).forEach(name -> {
			String className = name.toString().replace(filePath, "").replace("\\", ".").replace(".class", "");
			try {
				Class<?> cls = Class.forName(className);
				StringBuffer mthStr = new StringBuffer("$route." + cls.getSimpleName() + " = {");
				for (MethodModel model : getMethod(cls)) {
					if(model.getDesc() == null || model.getDesc().equals("")){
						mthStr.append("'" + model.getName() + "': function(success, error){\r\n");
					}else {
						mthStr.append("'" + model.getName() + "': function(" + model.getDesc() + ", success, error){\r\n");
					}
					mthStr.append("var _url = 'i=" + cls.getName() + "&m=" + model.getName() + "';\r\n");
					
					mthStr.append("post(_url, {'p':JSON.stringify([" + model.getDesc() + "])}, success,error);\r\n},\r\n");
				}
				mthStr.append("'_interface': '" + cls.getName() + "'\r\n");
				mthStr.append("}\r\n\r\n");

				head += mthStr;
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		System.out.println(head);

	}
	
	//读取类所有方法，转换为对象，存储到list
	public static List<MethodModel> getMethod(Class<?> cls) {
		List<MethodModel> models = new ArrayList<>();
		Method[] methods = cls.getDeclaredMethods();
		Arrays.stream(methods).forEach(method -> {
			MethodModel model = new MethodModel();
			model.setName(method.getName());
			model.setParam(method.getParameters());
			models.add(model);
		});
		return models;
	}

	// 加载目录
	static void loadClass() throws Exception {
		File clazzPath = new File(filePath);
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

	// 读取所有接口文件路径
	static void getFiles(String filePath) throws Exception {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getFiles(file.getAbsolutePath());
			} else if (file.getAbsolutePath().endsWith(".class") && file.getAbsolutePath().contains("Service")) {
				filelist.add(file.getAbsolutePath());
			}
		}
	}

}
