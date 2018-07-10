package org.study.base.json.jackjson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WriteJson {
	public static void main(String[] args) {
		User u1 = new User(1,"01");
		User u2 = new User(2,"02");
		List<User> ulList = new ArrayList<>();
		ulList.add(u1);
		ulList.add(u2);
		writeJson(u1);
		writeJson(ulList);
	}
	
	public static void writeJson(Object object){
		try {
			ObjectMapper mapper = new ObjectMapper();
			String u1String = mapper.writeValueAsString(object);
			System.out.println(u1String);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	static class User{
		private int id;
		private String name;
		
		User(){}
		
		User(int id, String name){
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
		@Override
		public String toString() {
			return "id:" + id + ",name:" + name;
		}
	}
}