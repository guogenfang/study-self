package org.study.self.spring.boot.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.stereotype.Component;
import org.study.self.spring.boot.IBeanService;
import org.study.self.spring.boot.service.BeanService;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年1月19日
 */
//@Component
public class RmiConfig {

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			Arrays.stream(beanNames).forEach(bean -> {
				// System.out.println(bean);
			});
		};
	}

	// @Bean
	// RmiServiceExporter BeanService(BeanServiceImpl beanServiceImpl) {
	//// org.springframework.remoting.rmi.RmiServiceExporter
	// RmiServiceExporter rmi = new RmiServiceExporter();
	// rmi.setServiceInterface(IBeanInterface.class);
	// rmi.setServiceName("beanService");
	// rmi.setService(beanServiceImpl);
	// return rmi;
	// }
	//

	@Bean
	HttpInvokerServiceExporter BeanService(BeanService beanService) {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setService(beanService);
		exporter.setServiceInterface(IBeanService.class);
		exporter.afterPropertiesSet();
		return exporter;
	}

	@Bean
	HttpInvokerProxyFactoryBean remoteBeanService() {
		HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
		factoryBean.setServiceInterface(IBeanService.class);
		factoryBean.setServiceUrl("http://127.0.0.1:8080/beanService");
		return factoryBean;
	}
}
