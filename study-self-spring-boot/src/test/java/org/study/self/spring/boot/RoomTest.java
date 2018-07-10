package org.study.self.spring.boot;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.study.self.spring.boot.model.Room;
import org.study.self.spring.boot.model.User;
import org.study.self.spring.boot.service.RoomService;

public class RoomTest extends SpringBootTest{
	
	@Autowired
	private RoomService roomService;
	
	@Test
	public void save() {
//		Room room = new Room("name", 1, "url", 100001, "法院名称");
//		roomService.save(room);
	}
	
	@Test
	public void update() {
		Room room = new Room();
		room.setName("房间名称");
		Room old = roomService.getById(5);
		System.out.println(old);
		System.out.println("---------------------");
		BeanUtils.copyProperties(room, old, "id");
		System.out.println(room);
		System.out.println(old);
//		room.setId(5);
//		roomService.save(room);
	}
}
