package tujuh.suganda.config;

import com.github.brainlag.nsq.NSQProducer;

public class NsqConfig {
	final static String SERVER ="127.0.0.1";
	final static int PORT =4150;
	public static NSQProducer getNsqProducer() {
		NSQProducer producer = new NSQProducer().addAddress(SERVER, PORT).start();
		return producer;
	}
}
