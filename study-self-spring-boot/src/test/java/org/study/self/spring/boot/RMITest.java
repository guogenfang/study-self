package org.study.self.spring.boot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RMITest extends SpringBootTest {

	@Autowired
	private IBeanService remoteBeanService;

	@Test
	public void get() {
		remoteBeanService.calc("aaa");
	}
}
