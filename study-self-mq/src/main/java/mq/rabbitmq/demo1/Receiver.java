/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mq.rabbitmq.demo1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StopWatch;

/**
 * @author Gary Russell
 * @author Scott Deeg
 */
@Profile({"test1"})
@RabbitListener(queues = "#{queue1.name}")
public class Receiver {
	
	private final int instance;
	
	public Receiver(int i) {
		this.instance = i;
	}

	@RabbitHandler
	public void receive(String in) throws InterruptedException {
		System.out.println(" '[x] Received '" + in + "'");

//		StopWatch watch = new StopWatch();
//		watch.start();
//		System.out.println("instance " + this.instance + " [x] Received '" + in + "'");
//		doWork(in);
//		watch.stop();
//		System.out.println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

	private void doWork(String in) throws InterruptedException {
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}
}
