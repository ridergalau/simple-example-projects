package tujuh.suganda.service;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import tujuh.suganda.service.kafkaconf.KafkaConfig;

public class ExampleService {
	static KafkaConfig conf = new KafkaConfig();
	final static String TOPIC = "test";
	final static Producer<Long, String> producer = conf.createProducer();
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		insert("sss");
	}

	public static void insert(String message) throws InterruptedException, ExecutionException {
		ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, message);

		RecordMetadata metadata = producer.send(record).get();
		System.out.println("OKE");
	}
}
