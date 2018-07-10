package org.study.base.javanew.entity;

public class School {
	private Integer id;
	
	private String name;

	public School(Integer id, String name) {
		this.name = name;
		this.id = id;
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
	
	@Override
	public String toString() {
		return id + "." + name;
	}
}