package tujuh.suganda.example;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.TopicPartition;
import static java.time.temporal.ChronoUnit.MINUTES;
public class TestByTImes {
public static void main(String[] args) {
	
	Properties props = new Properties();
	props.put("bootstrap.servers", "l=localhost:9092,localhost4:9092");
	props.put("group.id", "group." + UUID.randomUUID().toString());
	props.put("enable.auto.commit", "true");
	props.put("auto.commit.interval.ms", "1000");
	props.put("auto.offset.reset", "earliest");
	props.put("session.timeout.ms", "30000");
	props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	consumer.subscribe(Arrays.asList("ipa_clustering_tw"));
	boolean flag = true;

	while (true) {
	    ConsumerRecords<String, String> records = consumer.poll(100);

	    if(flag) {
	        Map<TopicPartition, Long> query = new HashMap<>();
	       
	        Set<TopicPartition> assignments = consumer.assignment();

			for (TopicPartition topicPartition : assignments) {
				query.put(topicPartition, Instant.now().minus(1, MINUTES).toEpochMilli());
			}
			
	        Map<TopicPartition, OffsetAndTimestamp> result = consumer.offsetsForTimes(query);

	        result.entrySet()
	                .stream()
	                .forEach(entry -> consumer.seek(entry.getKey(), entry.getValue().offset()));

	        flag = false;
	    }

	    for (ConsumerRecord<String, String> record : records)
	        System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	}
}
}
