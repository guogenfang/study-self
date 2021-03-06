package mq;

import java.util.concurrent.locks.LockSupport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class RabbitAmqpTutorialsApplication {

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
			}
		};
	}

//	@Bean
//	public CommandLineRunner tutorial() {
//		return new RabbitAmqpTutorialsRunner();
//	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RabbitAmqpTutorialsApplication.class, args);
	}

}
