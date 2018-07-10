package org.study.self.jooq;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.study.self.jooq.service.RoomService;

public class AppTest extends SpringBootTest {
	
	@Autowired
	private RoomService roomService;
	
	@Test
	public void list() {
		roomService.list();
	}
	
}
