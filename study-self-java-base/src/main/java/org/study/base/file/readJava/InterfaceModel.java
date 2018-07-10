package org.study.base.file.readJava;

import java.util.List;

public class InterfaceModel {
	private String name;
	
	private String url;
	
	private String comment = "";

	private List<MethodModel> methodModelList;
	
	public String getUrlFormat(){
		return url.replace(".", "_");
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public List<MethodModel> getMethodModelList() {
		return methodModelList;
	}
	
	public void setMethodModelList(List<MethodModel> methodModelList) {
		this.methodModelList = methodModelList;
	}
	
	@Override
	public String toString() {
		return "接口名称:" + name 
				+ ",接口全称:" + url 
				+ ",接口注释:" + comment 
				+ ",接口方法：" + methodModelList;
	}
}
