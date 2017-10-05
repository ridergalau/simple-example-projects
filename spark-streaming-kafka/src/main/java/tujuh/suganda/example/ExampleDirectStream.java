package tujuh.suganda.example;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import scala.Tuple2;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import kafka.serializer.StringDecoder;

public class ExampleDirectStream implements Serializable {
	private final static String KAFKATOPIC = "sc-fb-about";
	private final static String GROUPNAME = "mly_sample_14";
	private final static String COLLECTION = "monitor-smartcrawler";

	public static void main(String[] args) throws InterruptedException,
			IOException {
		String offset = "smallest";
		System.out.println("topic -> " + KAFKATOPIC);
		System.out.println("offset -> " + offset);
		Date date = new Date();
		String brokers = "namenode01.cluster2.ph:6667,namenode02.cluster2.ph:6667,master02.cluster2.ph:6667,kafka01.cluster2.ph:6667";

//		SparkConf sparkConf = new SparkConf();
		SparkConf sparkConf = new SparkConf().setAppName(
				"ProfilingGraphFacebook").setMaster("local[2]");

		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf,
				new Duration(1000));

		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.OFF);

		HashSet<String> topicsSet = new HashSet<String>(
				Arrays.asList(KAFKATOPIC.split(",")));
		
		HashMap<String, String> kafkaParams = new HashMap<String, String>();
		kafkaParams.put("metadata.broker.list", brokers);
		kafkaParams.put("auto.offset.reset", offset);
		kafkaParams.put("group.id", GROUPNAME);

		JavaPairInputDStream<String, String> messages = KafkaUtils
				.createDirectStream(jssc, String.class, String.class,
						StringDecoder.class, StringDecoder.class, kafkaParams,
						topicsSet);

		JavaDStream<String> lines = messages
				.map(new Function<Tuple2<String, String>, String>() {
					public String call(final Tuple2<String, String> tuple2){
						return tuple2._2();
					}
				}).transform(new Function<JavaRDD<String>, JavaRDD<String>>() {
					public JavaRDD<String> call(JavaRDD<String> arg0) throws Exception {
						return arg0.distinct();
					}
				});
		
		lines.print();
		jssc.start();
		jssc.awaitTermination();
	}
}