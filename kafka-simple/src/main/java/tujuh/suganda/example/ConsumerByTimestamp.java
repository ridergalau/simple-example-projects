package tujuh.suganda.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import tujuh.suganda.service.Services;

public class ConsumerByTimestamp {
	public static Services serv = new Services();

	public static void main(String[] args) {
		Date x = new Date();
		Properties props = new Properties();
		props.put("bootstrap.servers", "tujuh-pc:9092");
		props.put("group.id", "group." + UUID.randomUUID().toString());
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "earliest");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		List<String> data = new ArrayList<>();

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("test1"));

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				if (record.timestamp() >= Long.valueOf("1509421514138") && record.timestamp() <= x.getTime()) {
					data.add(record.value() + " -> " + record.timestamp());
				}
			if (data.size() > 0)
				System.out.println(data);
			data.clear();
		}

	}

}
