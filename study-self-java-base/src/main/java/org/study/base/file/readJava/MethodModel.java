package org.study.base.file.readJava;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class MethodModel {
	
	private String name;
	
	private List<String> params;
	
	private String comment;
	
	private String returnType;
	
	public MethodModel() {}
	
	public MethodModel(String comment) {
		this.comment = comment;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public String getComment() {
		if(StringUtils.isEmpty(comment)){
			comment = "";
		}
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public String getMethodParamsToString(){
		String str = "";
		for (String param : params) {
			str += param + ",";
		}
		
		if(params.size() > 0){
			str = str.substring(0, str.length() - 1);
		}
		
		return str;
	}
	
	@Override
	public String toString() {
		return "方法名称:" + name + 
				",方法返回类型:" + returnType + 
				",方法注释:" + comment + 
				",方法参数：" + getMethodParamsToString();
	}
}
