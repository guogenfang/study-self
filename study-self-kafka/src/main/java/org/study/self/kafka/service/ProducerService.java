package org.study.self.kafka.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
	public void send() {
		String topic = "test";
		Properties props = new Properties();
		props.setProperty("metadata.broker.list", "192.168.62.101:9092");
		props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");
		props.put("host.name", "ggf-VirtualBox");
		props.put("bootstrap.servers", "192.168.62.101:9092");
		// props.put("acks", "all");
		// props.put("retries", 0);
		// props.put("batch.size", 16384);
		// props.put("linger.ms", 1);
		// props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 100; i < 120; i++) {
			producer.send(new ProducerRecord<String, String>(topic, Integer.toString(i), Integer.toString(i)));
		}

		producer.close();

	}
}
