package org.study.base.file.readJava;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.study.base.file.ReadClass.ReturnModel;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import clojure.lang.Obj;

public class ReadJavaFiles {
	public static final String EMPTY = "Optional.empty";
	//public static String java_base_path = "F:\\git project\\platform-parent\\platform-service-api\\src\\main\\java\\";
	//public static String class_base_path = "F:\\git project\\platform-parent\\platform-service-api\\target\\classes\\";
	public static String java_base_path = "F:\\git project\\sfy-zb-base-api\\src\\main\\java\\";
	public static String class_base_path = "F:\\git project\\sfy-zb-base-api\\target\\classes\\";
	public static String head = "var $serviceSDK = {};\r\n var baseUrl = 'http://192.168.60.232:8001/?';\r\n";
	public List<String> nList = new ArrayList<>();
	public static List<InterfaceModel> interfaceModels = new ArrayList<>();
	public static Gson gson = new GsonBuilder().serializeNulls().create();
	
	public static void listClasses(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java")&&path.contains("Service"), (level, path, file) -> {
        	String _interface = path.replace("/", ".");
			_interface = _interface.substring(1, _interface.length() - 5);
			InterfaceModel interfaceModel = new InterfaceModel();
			interfaceModel.setUrl(_interface);
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                        super.visit(n, arg);
                        List<MethodModel> methodModels = new ArrayList<>();
                        List<MethodDeclaration> mets = n.getMethods();
                        
                        interfaceModel.setName(n.getName().toString());
                        if(!n.getComment().toString().equals("Optional.empty")){
                        	interfaceModel.setComment(n.getComment().get().toString());
						}
                        
                        for (MethodDeclaration method : mets) {
                        	MethodModel model = new MethodModel();
                        	List<String> params = new ArrayList<>();
                        	
                        	model.setName(method.getName().toString());
                        	if(!method.getComment().toString().equals(EMPTY)){
                        		model.setComment(method.getComment().get().toString());
                        	}
                        	model.setReturnType(method.getType().toString());
                        	for (Parameter param : method.getParameters()) {
                        		params.add(param.getNameAsString());
							}
                        	
                        	model.setParams(params);
                        	methodModels.add(model);
						}
                        
                        interfaceModel.setMethodModelList(methodModels);
                        interfaceModels.add(interfaceModel);
                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).explore(projectDir);
        generateString();
    }

	public static void generateString(){
		//System.out.println(interfaceModels);
		for (InterfaceModel interfaceModel : interfaceModels) {
			StringBuffer mthStr = new StringBuffer(interfaceModel.getComment() + "$serviceSDK." + interfaceModel.getUrlFormat() + " = {");
			for (MethodModel model : interfaceModel.getMethodModelList()) {
				if(StringUtils.isEmpty(model.getMethodParamsToString())){
					mthStr.append(model.getComment() + "'" + model.getName() + "': function(success, error, options){\r\n");
				}else {
					mthStr.append(model.getComment() + "'" + model.getName() + "': function(" + model.getMethodParamsToString() + ", success, error, options){\r\n");
				}
				mthStr.append("var _url = 'i=" + interfaceModel.getUrl() + "&m=" + model.getName() + "';\r\n");
				
				mthStr.append("postService(_url, {'p':JSON.stringify([" + model.getMethodParamsToString()+ "])}, success,error, options);\r\n},\r\n");
			}
			mthStr.append("'_interface': '" + interfaceModel.getUrl() + "'\r\n");
			mthStr.append("}\r\n\r\n");
			
			head += mthStr;
		}
		
		generateFile("F://route.js", head);
	}
	
	//前端服务接口代码
	public static void generateFile(String fileUrl, String data){
		try {
			File file = new File(fileUrl);
			if(file.exists()){
				file.delete();
			}
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
			stream.write(data.getBytes());
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*// 读取所有接口文件路径
	static void getClassFiles(String classfilePath) throws Exception {
		File root = new File(classfilePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getClassFiles(file.getAbsolutePath());
			} else if (file.getAbsolutePath().endsWith(".class") && file.getAbsolutePath().contains("Service")) {
				classFilelist.add(file.getAbsolutePath());
			}
		}
	}*/
	
	// 加载目录
	static void loadClass() throws Exception {
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
	
	//生成返回数据对象
	public static void getMethodReturnObj(String url) throws Exception{
		Class<?> cls = Class.forName(url);
		Method [] methods = cls.getMethods();
		Arrays.stream(methods).forEach(method -> {
			Class<?> returnCls = method.getReturnType();
			try {
				Object object = null;
				System.out.println(method.getName() + "返回类型：" + method.getGenericReturnType());
				if(method.getGenericReturnType().toString().endsWith("void")){
					object = new Object();
				}
				else if(returnCls.toString().endsWith("Integer") || returnCls.toString().endsWith("int") ){
					object = 123;
				}
				else if(returnCls.toString().toLowerCase().endsWith("boolean")){
					object = true;
				}
				else if(returnCls.toString().endsWith("Map")){
					Map<Object, Object> map = new HashMap<>();
					map.put("obj", "ggf");
					object = map;
				}
				else if(returnCls.toString().endsWith("List")){
					List list = new ArrayList<>();
					String type = method.getGenericReturnType().toString();
					if(type.contains("<")){
						type = type.split("<")[1].split(">")[0];
					}
					//检验泛型类型 Integer无法通过newInstnce创建
					if(type.contains("Integer")){
						list.add(123);
					}
					else {
						Object typeObj = Class.forName(type).newInstance();
						setObjFields(typeObj);
						list.add(typeObj);
					}
					object = list;
				}
				else{
					object = returnCls.newInstance();
					setObjFields(object);
				}
				generateFile("F://jsonData//" + url + "." + method.getName() + ".json", gson.toJson(new ReturnModel(object)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	//设置属性
	public static void setObjFields(Object object){
		Field [] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if(field.getType().toString().endsWith("Integer")){
				try {
					field.set(object, 123);
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
	}
	
	//生成模拟方法返回数据
	public static void generateData(){
		for (InterfaceModel model : interfaceModels) {
			try {
				getMethodReturnObj(model.getUrl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
    	loadClass();
        File projectDir = new File(java_base_path);
        listClasses(projectDir);
        generateData();
    }
}
