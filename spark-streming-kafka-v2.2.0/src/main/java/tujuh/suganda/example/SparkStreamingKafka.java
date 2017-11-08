package tujuh.suganda.example;

import java.util.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka010.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import scala.Tuple2;

public class SparkStreamingKafka {
	public static void main(String[] args) throws InterruptedException {
		SparkConf sparkConf = new SparkConf().setAppName("ExampleStream").setMaster("local[2]");

		// SparkConf sparkConf = new SparkConf();

		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(1000));
		
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.OFF);

		Map<String, Object> kafkaParams = new HashMap<>();
		kafkaParams.put("bootstrap.servers", "l=server:2181,server2:2181,server3:2181");
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		kafkaParams.put("group.id", "kafkaConsumer2");
		kafkaParams.put("auto.offset.reset", "earliest");
		kafkaParams.put("enable.auto.commit", false);

		Collection<String> topics = Arrays.asList("topic-0", "topic-1");

		JavaInputDStream<ConsumerRecord<String, String>> stream = KafkaUtils.createDirectStream(jssc,
				LocationStrategies.PreferConsistent(),
				ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams));
		
		stream.mapToPair(record -> new Tuple2<>(record.key(), record.value()));
		
		JavaDStream<String> lines = stream.map(new Function<ConsumerRecord<String,String>, String>() {
			@Override
			public String call(ConsumerRecord<String, String> v1) throws Exception {
				System.out.println(v1.topic()+"  =====> "+v1.value());
				return null;
			}
		});
		
		lines.print();
		jssc.start();
		jssc.awaitTermination();
	}
}
