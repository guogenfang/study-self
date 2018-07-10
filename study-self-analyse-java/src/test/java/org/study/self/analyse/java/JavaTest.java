package org.study.self.analyse.java;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.study.self.analyse.java.classFile.LoadClass;
import org.study.self.analyse.java.file.ReflectGenerateDate;
import org.study.self.analyse.java.file.WriteFile;
import org.study.self.analyse.java.javaFile.ReadJavaFiles;
import org.study.self.analyse.java.model.InterfaceModel;

public class JavaTest {
	public static String java_base_path = "F:\\git project\\sfy-zb-base-api\\src\\main\\java\\";
	public static String class_base_path = "F:\\git project\\sfy-zb-base-api\\target\\classes\\";
	public static String savepath = "F://jsonData//";//生成的json文件存储路径
	
	//@Test
	public void get() throws Exception{
		File projectDir = new File(java_base_path);
		List<InterfaceModel> interfaceModels = ReadJavaFiles.listClasses(projectDir);
		String str = ReadJavaFiles.generateString();
        WriteFile.generateFile("F://route.js", str);
        LoadClass.load(class_base_path);
        ReflectGenerateDate.generateData(interfaceModels, savepath);
	}
}
