package org.study.self.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class AppTest {
	public static void main(String[] args) {
		String topic = "test";
//		Properties props = new Properties();
//		props.setProperty("metadata.broker.list", "192.168.62.101:9092");
//		props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
//		props.put("request.required.acks", "1");
//		props.put("host.name", "ggf-VirtualBox");
//		props.put("bootstrap.servers", "192.168.62.101:9092");
//		// props.put("acks", "all");
//		// props.put("retries", 0);
//		// props.put("batch.size", 16384);
//		// props.put("linger.ms", 1);
//		// props.put("buffer.memory", 33554432);
//		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//		Producer<String, String> producer = new KafkaProducer<>(props);
//		for (int i = 100; i < 120; i++)
//			producer.send(new ProducerRecord<String, String>(topic, Integer.toString(i), Integer.toString(i)));
//
//		producer.close();

		 Properties props = new Properties();
	     props.put("bootstrap.servers", "192.168.62.101:9092");
	     props.put("group.id", "test");
	     props.put("enable.auto.commit", "true");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	     consumer.subscribe(Arrays.asList(topic));
	     while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records)
	             System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	     }

	}
}
