package tujuh.suganda.example;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import scala.Tuple2;
public class ExampleStream implements Serializable {
	private final static String kafkaTopic = "ipa_clustering_tw";
	// private final static String kafkaTopic = "twitter_pilkada";
	private final static String zkHost = "namenode01.cluster2.ph:2181,namenode02.cluster2.ph:2181";
	private final static String groupName = "mly_sample_17";

	public static void main(String[] args) throws InterruptedException,
			IOException {

		SparkConf sparkConf = new SparkConf().setAppName("test").setMaster(
				"local[2]");

		// SparkConf sparkConf = new SparkConf();

		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf,
				new Duration(1000));
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.OFF);
		JavaPairReceiverInputDStream<String, String> messages = KafkaUtils
				.createStream(jssc, zkHost, groupName, getTopicKafka());

//		JavaDStream<String> linesx = messages
//				.map(new Function<Tuple2<String, String>, String>() {
//					@Override
//					public String call(Tuple2<String, String> liness)
//							throws Exception {
//						// TODO Auto-generated method stub
//						return liness._2;
//					}
//				});

		// Supaya tidak Duplicate
		JavaDStream<String> lines = messages.map(
				new Function<Tuple2<String, String>, String>() {
					public String call(final Tuple2<String, String> data){
						return data._2;
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

	private static Map<String, Integer> getTopicKafka() {
		Map<String, Integer> topicMap = new HashMap<String, Integer>();
		String[] topics = kafkaTopic.split(",");
		for (String topic : topics) {
			topicMap.put(topic, 1);
		}
		return topicMap;
	}
}