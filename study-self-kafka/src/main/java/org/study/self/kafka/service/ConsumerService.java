package org.study.self.kafka.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

	private static String bootstrapServers = "192.168.62.101:9092";

	/**
	 * This example demonstrates a simple usage of Kafka's consumer api that relying
	 * on automatic offset committing.
	 * 
	 * @author ggf
	 *
	 */
	static class ConsumerRunnable implements Runnable {
		private String name;
		private final AtomicBoolean closed = new AtomicBoolean(false);

		public ConsumerRunnable(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			String topic = "test";
			Properties configs = new Properties();
			configs.put("bootstrap.servers", bootstrapServers);
			configs.put("group.id", name);
			configs.put("enable.auto.commit", "true");
			configs.put("auto.commit.interval.ms", "1000");
			configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

			KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);
			consumer.subscribe(Arrays.asList(topic));
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records)
					System.out.printf("consumer name = %s, offset = %d, key = %s, value = %s%n", name, record.offset(),
							record.key(), record.value());
			}
		}
	}

	/**
	 * Instead of relying on the consumer to periodically commit consumed offsets,
	 * users can also control when records should be considered as consumed and
	 * hence commit their offsets. This is useful when the consumption of the
	 * messages is coupled with some processing logic and hence a message should not
	 * be considered as consumed until it is completed processing.
	 * 
	 * @author ggf
	 *
	 */
	static class ConsumerRunnable2 implements Runnable {
		private String name;

		public ConsumerRunnable2(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			Properties configs = new Properties();
			configs.put("bootstrap.servers", bootstrapServers);
			configs.put("group.id", "test");
			configs.put("enable.auto.commit", "false");
			configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);
			consumer.subscribe(Arrays.asList("foo", "bar"));
			final int minBatchSize = 200;
			List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records) {
					buffer.add(record);
				}
				if (buffer.size() >= minBatchSize) {
					// insertIntoDb(buffer);
					consumer.commitSync();
					buffer.clear();
				}
			}

			// try {
			// while(running) {
			// ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
			// for (TopicPartition partition : records.partitions()) {
			// List<ConsumerRecord<String, String>> partitionRecords =
			// records.records(partition);
			// for (ConsumerRecord<String, String> record : partitionRecords) {
			// System.out.println(record.offset() + ": " + record.value());
			// }
			// long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
			// consumer.commitSync(Collections.singletonMap(partition, new
			// OffsetAndMetadata(lastOffset + 1)));
			// }
			// }
			// } finally {
			// consumer.close();
			// }

		}
	}

	@SuppressWarnings("unchecked")
	static class ConsumerRunnable3 implements Runnable {
		private final AtomicBoolean closed = new AtomicBoolean(false);
		private final KafkaConsumer<?, ?> consumer = null;

		public void run() {
			try {
				consumer.subscribe(Arrays.asList("topic"));
				while (!closed.get()) {
					ConsumerRecords<?, ?> records = consumer.poll(10000);
					// Handle new records
				}
			} catch (WakeupException e) {
				if (!closed.get())
					throw e;
			} finally {
				consumer.close();
			}
		}

		public void shutdown() {
			closed.set(true);
			consumer.wakeup();
		}

	}

	public static void main(String[] args) {
		new Thread(new ConsumerRunnable("ggf")).start();
		new Thread(new ConsumerRunnable("abc")).start();
	}
}
