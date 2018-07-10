package org.study.base.file.ReadClass;

public class UserModel {
	
	private String id;
	
	private String name;
	
	private Integer age;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "name:" + name + ",id:" + id + ",age:" + age;
	}
}
