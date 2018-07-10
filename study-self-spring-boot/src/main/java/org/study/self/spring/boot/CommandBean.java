package org.study.self.spring.boot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class CommandBean implements CommandLineRunner,ApplicationRunner{

	@Autowired
	private RedisProp redis;
	
	private Logger logger = LoggerFactory.getLogger(CommandBean.class);
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println(redis);
		logger.info("------------ CommandLineRunner run ---------");
		Arrays.stream(args).forEach(arg->{
			System.out.println(arg);
		});
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("------------ ApplicationRunner run ---------");
		Thread.sleep(3000);
//		throw new RuntimeException();
	}

}
