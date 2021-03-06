package mq.rabbitmq.demo1;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.BindingBuilder.HeadersExchangeMapConfigurer.HeadersExchangeSingleValueBindingCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"test1"})
@Configuration
public class Config {

	public String queue_name = "ggf-text-queue";
	
	public String exchange = "ggf-exchange";
	
	public static String routingKey = "ggf-exchange";
	
	@Bean
	public Queue queue1() {
		return new Queue(queue_name);
	}
	
//	@Bean
//	public HeadersExchange direct() {
//		return new HeadersExchange(exchange);
//	}
//
	@Bean
	public DirectExchange direct() {
		return new DirectExchange(exchange);
	}
	
	@Bean
	@Profile("sender")
	public Sender sender() {
		return new Sender();
	}
	
	@Profile("receiver")
	private static class ReceiverConfig {
		@Bean
		public Receiver receiver1() {
			return new Receiver(1);
		}

		@Bean
		public Binding binding(DirectExchange direct, Queue queue1) {
//			return BindingBuilder.bind(queue1).to(direct).where(routingKey);
			return BindingBuilder.bind(queue1).to(direct).with(routingKey);
		}

	}
}