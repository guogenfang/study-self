package org.study.base.file.ReadClass;

public class ReturnModel {
	private Integer code;
	
	private Object data;
	
	
	public ReturnModel() {
		// TODO Auto-generated constructor stub
	}
	
	public ReturnModel(Object data){
		this.code = 200;
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
}
