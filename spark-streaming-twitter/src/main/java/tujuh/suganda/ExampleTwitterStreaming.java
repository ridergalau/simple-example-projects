package tujuh.suganda;

/*
 * Completed tutorial in
 * https://github.com/apache/bahir/tree/master/streaming-twitter 
 * 
 * */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;

import scala.Tuple2;
import twitter4j.Status;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;

public class ExampleTwitterStreaming {

	public static void main(String[] args) {
		String consumerKey = args[0];
		String consumerSecret = args[1];
		String accessToken = args[2];
		String accessTokenSecret = args[3];

		String[] filters = new String[] { "jokowi", "indonesia", "polri", "polisi" };

		System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
		System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret);
		System.setProperty("twitter4j.oauth.accessToken", accessToken);
		System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret);

		SparkConf sparkConf = new SparkConf().setAppName("JavaSparkStreamTwitter");

		if (!sparkConf.contains("spark.master")) {
			sparkConf.setMaster("local[3]");
		}

		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(2000));
		JavaReceiverInputDStream<Status> stream = TwitterUtils.createStream(jssc, filters);

		JavaDStream<String> statusTwitter = stream.flatMap(new FlatMapFunction<Status, String>() {
			@Override
			public Iterator<String> call(Status s) {
				return Arrays.asList(s.getText() + "  --  " + s.getPlace() + " --  " + s.getGeoLocation()).iterator();
			}
		});

		JavaDStream<String> hashTags = statusTwitter.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String word) {
				boolean s = word.contains("#");
				if (s == true) {
					System.out.println("================= " + word);
				}
				return word.contains("#");
			}
		});

		statusTwitter.foreachRDD(new VoidFunction<JavaRDD<String>>() {
			@Override
			public void call(JavaRDD<String> rdd) throws Exception {
				rdd.foreach(new VoidFunction<String>() {
					@Override
					public void call(String arg0) throws Exception {
						System.out.println("************* " + arg0);

					}
				});
			}
		});

		// statusTwitter.print(1);
		hashTags.print(1);
		jssc.start();
		try {
			jssc.awaitTermination();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
