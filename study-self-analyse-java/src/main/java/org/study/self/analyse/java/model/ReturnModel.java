package org.study.self.analyse.java.model;

public class ReturnModel {
	private int code;
    private Object data;
    
    public ReturnModel() {
	}
    
    public ReturnModel(Object data) {
    	this.data = data;
    }
    
    public int getCode() {
		return code;
	}
    
    public void setCode(int code) {
		this.code = code;
	}
    
    public Object getData() {
		return data;
	}
    
    public void setData(Object data) {
		this.data = data;
	}

}
