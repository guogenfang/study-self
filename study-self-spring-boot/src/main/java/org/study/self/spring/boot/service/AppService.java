package org.study.self.spring.boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppService {
	private Logger logger = LoggerFactory.getLogger(AppService.class);
	
	public String getNameById(String id) {
		logger.info("get name by id, id:{}",id);
		return "123--ggf";
	}
}
