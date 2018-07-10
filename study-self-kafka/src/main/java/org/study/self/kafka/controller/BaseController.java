package org.study.self.kafka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BaseController {

	@RequestMapping("/get")
	public String get() {
		return "hello";
	}
	
}
