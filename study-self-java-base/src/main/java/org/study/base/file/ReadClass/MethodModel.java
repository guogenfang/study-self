package org.study.base.file.ReadClass;

import java.lang.reflect.Parameter;
import java.util.Arrays;

public class MethodModel {
	private String name;
	
	private Parameter[] param;
	
	private String desc = "";
	
	private String annotation = "";
	
	public MethodModel(){}
	
	public MethodModel(String name, Parameter[] param, String desc) {
		this.name = name;
		this.param = param;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Parameter[] getParam() {
		return param;
	}

	public void setParam(Parameter[] param) {
		this.param = param;
	}

	public String getDesc() {
		desc = "";
		if(param.length == 0){
			return desc;
		}
		
		Arrays.stream(param).forEach(par -> {
			desc += par.getName() + ",";
		});
		return desc.substring(0, desc.length() - 1);
	}
	
	public String getAnnotation() {
		if(param.length == 0){
			return null;
		}
		else{
			Arrays.stream(param).forEach(par -> {
				desc += par + ",";
			});
			return desc.substring(0, desc.length() - 1);
		}
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
