package org.study.self.spring.boot;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main( String[] args ){
    	ApplicationContext context = SpringApplication.run(Application.class, args);
    	Result myBean = context.getBean(Result.class);
    	System.out.println(myBean);
    	System.out.println(context.getBean(IBeanService.class).calc("abc"));
    }
    
    @Bean({"hello"})
    public Result getHello() {
    	System.out.println("get hello bean");
    	return Result.ok("lllll");
    }
    
//    @Bean
//    public IBeanInterface BeanInterface() {
//    	return (arg)->{
//    		//TODO calc 
//			return arg + "---ssss";
//		};
//    }
    
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            Arrays.stream(beanNames).forEach(bean -> {
//            	System.out.println(bean);
            });
        };
    }
    
}
