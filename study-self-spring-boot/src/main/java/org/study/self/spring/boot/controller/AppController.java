package org.study.self.spring.boot.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.study.self.spring.boot.IBeanService;
import org.study.self.spring.boot.Result;
import org.study.self.spring.boot.service.AppService;

//@RestController
public class AppController implements InitializingBean{

	@Value("${redis.host}")
	private String redis;
	
	@Autowired
	private AppService appService;
	
	private Logger logger = LoggerFactory.getLogger(AppController.class);
	
	@RequestMapping(value = "/post",method = RequestMethod.POST)
	public Integer postDemo(@RequestParam("channel_id") String channel_id,
			@RequestParam("stream_alias") String stream_alias,
			@RequestParam("publish_id") String publish_id, HttpServletRequest request){	
		return 200;
	}
	
	@RequestMapping(value = "/postA",method = RequestMethod.POST)
	public Integer postDemo(@RequestBody String body, HttpServletRequest request){
		return 200;
	}
	

	@RequestMapping(value = "/name")
	public Result getName() {
		logger.info("-----------getName-----------");
		return Result.ok(appService.getNameById("a"));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("-----------afterPropertiesSet-----------");
	}
	
	@ExceptionHandler
    Result exceptionHandler(Exception e) {
        return Result.error(e.getMessage());
    }
}
