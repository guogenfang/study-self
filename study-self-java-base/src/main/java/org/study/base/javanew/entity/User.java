package org.study.base.javanew.entity;


public class User {
	private Integer id;
	private String name;
	private Integer age;
	private Integer status;
	private School school;

	public User() {
		age = (int) (Math.random() * 50);
	}

	public User(Integer id, String name, School school) {
		this.id = id;
		this.name = name;
		this.school = school;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return id + "," + name + "," + age + ",school:" + school;
	}
}
