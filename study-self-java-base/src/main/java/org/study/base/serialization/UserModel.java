package org.study.base.serialization;

import java.io.Serializable;

public class UserModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String url;
	
	
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
	@Override
	public String toString() {
		return "name:" + name + ",url:" + url;
	}
}
