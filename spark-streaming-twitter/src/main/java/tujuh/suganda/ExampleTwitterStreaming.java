package tujuh.suganda;

/*
 * Completed tutorial in
 * https://github.com/apache/bahir/tree/master/streaming-twitter 
 * 
 * */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;

import tujuh.suganda.service.*;
import twitter4j.Status;

import java.util.Arrays;
import java.util.Iterator;

public class ExampleTwitterStreaming {
	static KafkaService Kafka = new KafkaService();
	static NsqService Nsq = new NsqService();

	public static void main(String[] args) {

		/*
		 * String consumerKey = args[0]; String consumerSecret = args[1]; String
		 * accessToken = args[2]; String accessTokenSecret = args[3];
		 */
		String[] filters = new String[] { "jokowi", "indonesia", "polri", "polisi" };

		String consumerKey = args[0];
		String consumerSecret = args[1];
		String accessToken = args[2];
		String accessTokenSecret = args[3];

		System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
		System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret);
		System.setProperty("twitter4j.oauth.accessToken", accessToken);
		System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret);

		SparkConf sparkConf = new SparkConf().setAppName("JavaExampleSparkStreamTwitter");

		if (!sparkConf.contains("spark.master")) {
			sparkConf.setMaster("local[3]");
		}

		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(2000));
		JavaReceiverInputDStream<Status> stream = TwitterUtils.createStream(jssc, filters);

		JavaDStream<String> statusTwitter = stream.flatMap(new FlatMapFunction<Status, String>() {
			@Override
			public Iterator<String> call(Status s) {
				Iterator<String> hasil = Arrays
						.asList(s.getText() + "  --  " + s.getPlace() + " --  " + s.getGeoLocation()).iterator();
				return hasil;
			}
		}).filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String arg0) throws Exception {
				return arg0 != null;
			}
		});

		JavaDStream<String> hashTags = statusTwitter.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String word) {
				return word.contains("#");
			}
		});

		statusTwitter.foreachRDD(new VoidFunction<JavaRDD<String>>() {
			@Override
			public void call(JavaRDD<String> rdd) throws Exception {
				rdd.foreach(new VoidFunction<String>() {
					@Override
					public void call(String arg0) throws Exception {
						// Stored to Nsq
						Nsq.insert("aing", arg0);
						// Stored to Kafka
						Kafka.insert(arg0);
					}
				});
			}
		});

		// statusTwitter.print(1);
		statusTwitter.print(1);
		jssc.start();
		try {
			jssc.awaitTermination();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
