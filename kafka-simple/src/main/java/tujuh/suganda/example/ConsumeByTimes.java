package tujuh.suganda.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.TopicPartition;

public class ConsumeByTimes {

	public static void main(String[] args) {
		Date date = new Date();
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.20.122:6667");
		props.put("group.id", "group." + UUID.randomUUID().toString());
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "earliest");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		//
		// TopicPartition topicPartition = new TopicPartition("test", 0);
		// Map<TopicPartition, Long> ini = new HashMap<>();
		// ini.put(topicPartition, Long.valueOf("1509421528146"));
		//
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
System.out.println( Instant.now().minus(2, MINUTES).toEpochMilli());
		Map<TopicPartition, Long> query = new HashMap<>();
		Set<TopicPartition> assignments = consumer.assignment();

		for (TopicPartition topicPartition : assignments) {
			query.put(topicPartition, Instant.now().minus(1, MINUTES).toEpochMilli());
		}

		consumer.subscribe(Arrays.asList("ipa_clustering_tw"));
//		consumer.offsetsForTimes(query);

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("topic = %s, value = %s, timestamp = %s", record.topic(),
						record.value(), record.timestamp() + "\n");
		}
		
		//

		// Map<TopicPartition, OffsetAndTimestamp> records =
		// consumer.offsetsForTimes(ini);
		// for (Entry<TopicPartition, OffsetAndTimestamp> xx : records.entrySet()) {
		// System.out.println(xx);
		// }

		// Map<TopicPartition, OffsetAndTimestamp> result =
		// consumer.offsetsForTimes(query);
		//
		// result.entrySet().stream().forEach(entry -> consumer.seek(entry.getKey(),
		// Optional.ofNullable(entry.getValue()).map(OffsetAndTimestamp::offset).orElse(new
		// Long(0))));
		//
		// System.out.println(" -- " + result.values());

		// result.forEach((topic,offset) ->
		// System.out.println("Offset for topic=" + topic.topic() + ", partition=" +
		// topic.partition()
		// +" offset=" + offset.offset() + ", Timestamp="
		// + offset.timestamp())
		// );
	}
}
