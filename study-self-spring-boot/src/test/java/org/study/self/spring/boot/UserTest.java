package org.study.self.spring.boot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.study.self.spring.boot.service.UserService;

public class UserTest extends SpringBootTest{
	
	@Autowired
	private UserService userService;
	
	@Test
	public void get() {
		userService.getById(0);
	}
	
	@Test
	public void list() {
		System.out.println(userService.list());
	}
	
	@Test
	public void search() {
		userService.search();
	}
	
//	@Test
//	public void update() {
//		Room room = new Room();
//		room.setName("abc");
//		roomService.save(room);
//	}
}
