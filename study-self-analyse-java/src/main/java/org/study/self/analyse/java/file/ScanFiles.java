package org.study.self.analyse.java.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * [简要描述]：扫描目录下的所有指定类型文件
 * 
 * @author ggf
 * @data 2017年5月19日
 */
public class ScanFiles {
	public static String class_base_path = "F:\\git project\\sfy-zb-base-api\\target\\classes\\";

	public static void main(String[] args) throws Exception {
		List<String> fList = new ArrayList<>();
		fList = getFiles(fList, class_base_path, ".class");
		for (String string : fList) {
			System.out.println(string);
		}
	}
	
	/**
	 * [简要描述]：读取所有接口文件路径
	 * @author ggf
	 * @date 2017年5月19日
	 * @param classFilelist
	 * @param filePath
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static List<String> getFiles(List<String>classFilelist, String filePath, String type) throws Exception {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getFiles(classFilelist, file.getAbsolutePath(), type);
			} else if (file.getAbsolutePath().endsWith(type) && file.getAbsolutePath().contains("Service")) {
				classFilelist.add(file.getAbsolutePath());
			}
		}
		return classFilelist;
	}
}
