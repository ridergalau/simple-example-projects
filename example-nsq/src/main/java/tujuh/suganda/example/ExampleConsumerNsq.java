package tujuh.suganda.example;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

public class ExampleConsumerNsq {
	public static void main(String[] args) {
		NSQLookup lookup = new DefaultNSQLookup();
		lookup.addLookupAddress("localhost", 4161);
		NSQConsumer consumer = new NSQConsumer(lookup, "aing", "aing", (message) -> {
			String s = new String(message.getMessage());
			System.out.println("Text Decryted : " + s);
			// now mark the message as finished.
			message.finished();

			// or you could requeue it, which indicates a failure and puts it back on the
			// queue.
			// message.requeue();
		});
		consumer.start();
	}
}
