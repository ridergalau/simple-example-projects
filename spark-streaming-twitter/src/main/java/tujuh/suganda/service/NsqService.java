package tujuh.suganda.service;

import java.util.concurrent.TimeoutException;

import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;

import tujuh.suganda.config.NsqConfig;

public class NsqService {
	static NsqConfig nsqConf = new NsqConfig();
	final static NSQProducer nsq = nsqConf.getNsqProducer();

	public static void insert(String topic, String message) {
		try {
			System.out.println("Message Inserted!");
			nsq.produce(topic, (message).getBytes());
		} catch (NSQException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
