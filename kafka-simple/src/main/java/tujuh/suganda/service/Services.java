package tujuh.suganda.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class Services implements Serializable {
	public List<String> getByTimestamp(ConsumerRecord<String, String> record) {
		List<String> value = new ArrayList<>();
		for (int i = 0; i < record.serializedValueSize(); i++) {
			String s = "offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value()
					+ ", timestamp = " + record.timestamp();
			value.add(s);
		}
		return value;
	}
}
