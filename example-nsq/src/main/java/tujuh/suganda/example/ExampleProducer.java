package tujuh.suganda.example;

import java.util.concurrent.TimeoutException;

import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;

public class ExampleProducer {
public static void main(String[] args) throws NSQException, TimeoutException {
	NSQProducer producer = new NSQProducer().addAddress("localhost", 4150).start();            
	producer.produce("aing", ("Hai Suganda!").getBytes());
}
}
