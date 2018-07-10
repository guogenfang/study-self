package org.study.base.file.readJava;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ScanJava {
	public static ArrayList<String> filelist = new ArrayList<String>();
	public static String path = "F:\\git project\\platform-parent\\platform-service-api\\src\\main\\java\\";

	public static void main(String[] args) throws Exception {
		System.out.println(path);
		getFiles(path);
		for (String file : filelist) {
			String _interface = file.replace(path, "").replace("\\", ".");
			_interface = _interface.substring(0, _interface.length() - 5);
			System.out.println("接口名称全称:" + _interface);
			String [] str = _interface.split("\\.");
			System.out.println("接口名称:" + str[str.length - 1]);
			File sourceFile = new File(file);
			CompilationUnit compilationUnit = JavaParser.parse(sourceFile);
			new VoidVisitorAdapter<Object>() {
				
			};
			/*List<Node> root = new ArrayList<>();
			List<Comment> comments = compilationUnit.getAllContainedComments();
			for (Comment comment : comments) {
				System.out.println("comment:" + comment);
			}*/
			/*Arrays.stream(compilationUnit.getChildNodes().toArray())
			.filter(node -> !node.toString().contains("import "))
			.filter(node -> !node.toString().contains("package "))
			.forEach(node->{
				root.add((Node) node);
			});
			if(root.size() == 2){
				System.out.println("接口注释：" + root.get(1));
			}
			System.out.println("-------------------------------");
			List<Node> nodes = root.get(0).getChildNodes();
			List<MethodModel> methodModels = new ArrayList<>();
			for(int i = 0; i < nodes.size(); i++){
				System.out.println(i + "--------------");
				System.out.println(nodes.get(i));
				System.out.println(nodes.get(i).getChildNodes());*/
				/*if(i == 0){
					System.out.println(nodes.get(i).getComment());
					System.out.println("接口名称：" + nodes.get(i));
				}
				else{
					MethodModel methodModel = new MethodModel();
					Node node = nodes.get(i);
					Optional<Comment> optional = node.getComment();
					if(!optional.toString().equals("Optional.empty")){
						methodModel.setComment(optional.get().toString());
					}
					List<Node> childs = node.getChildNodes();
					for(int num = 0 ; num < childs.size(); num++){
						if(num == 0){
							methodModel.setName(childs.get(num).toString());
						}
						if(num == childs.size() - 1){
							methodModel.setReturnType(childs.get(num).toString());
						}
					}
					methodModels.add(methodModel);
				}*/
			//}
			//System.out.println("---------" + methodModels);
		}

	}

	public static void nodeMsg(List<Node> nodes) {
		for (Node node : nodes) {
			System.out.println("node:" + node);
			if (node.getChildNodes() != null || node.getChildNodes().size() > 0) {
				nodeMsg(node.getChildNodes());
				System.out.println("-----------------");
			}
		}
	}

	// 读取所有接口文件路径
	static void getFiles(String filePath) throws Exception {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getFiles(file.getAbsolutePath());
			} else if (file.getAbsolutePath().endsWith(".java") && file.getAbsolutePath().contains("Service")) {
				filelist.add(file.getAbsolutePath());
			}
		}
	}
}
