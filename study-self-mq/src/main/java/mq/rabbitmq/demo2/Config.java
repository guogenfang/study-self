package mq.rabbitmq.demo2;

import java.util.Arrays;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({ "test2" })
@Configuration
public class Config {

	public static String exchange = "test2.fanout";

	public static String routingKey = "test2.orange";

	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("test2.fanout");
	}

	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public Queue queue1() {
			return new AnonymousQueue();
		}

		@Bean
		public Queue queue2() {
			return new AnonymousQueue();
		}

		@Bean
		public Binding binding1(FanoutExchange fanout, Queue queue1) {
			return BindingBuilder.bind(queue1).to(fanout);
		}

		@Bean
		public Binding binding2(FanoutExchange fanout, Queue queue2) {
			return BindingBuilder.bind(queue2).to(fanout);
		}

		@Bean
		public Receiver receiver() {
			return new Receiver();
		}

	}

	@Profile("sender")
	@Bean
	public Sender sender() {
		return new Sender();
	}

	// @Bean
	// SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	// MessageListenerAdapter listenerAdapter) {
	// SimpleMessageListenerContainer container = new
	// SimpleMessageListenerContainer();
	// container.setConnectionFactory(connectionFactory);
	// container.setQueueNames(queue_name);
	// container.setMessageListener(listenerAdapter);
	// return container;
	// }
	//
	// @Bean
	// MessageListenerAdapter listenerAdapter(Receiver receiver) {
	// return new MessageListenerAdapter(receiver, "receiveMessage");
	// }

	@Bean
	public CommandLineRunner commandLineRunner(FanoutExchange fanout, ApplicationContext ctx) {
		System.out.println(fanout);
		return null;
//		return args -> {
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			Arrays.stream(beanNames).forEach(bean -> {
//				System.out.println(bean);
//			});
//		};
	}
}