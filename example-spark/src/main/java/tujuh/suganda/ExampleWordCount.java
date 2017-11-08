package tujuh.suganda;

import java.util.Arrays;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

public class ExampleWordCount {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local[3]").setAppName("SparkWordCountExample");

		JavaSparkContext sc = new JavaSparkContext(conf);
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);
		JavaRDD<String> textFile = sc.textFile("/home/tujuh/Documents/Example/ini");

		// Word Count
		JavaPairRDD<String, Integer> counts = textFile.flatMap(s -> Arrays.asList(s.split(" ")).iterator())
				.mapToPair(word -> new Tuple2<>(word, 1)).reduceByKey((a, b) -> a + b);

		counts.foreach(new VoidFunction<Tuple2<String, Integer>>() {
			@Override
			public void call(Tuple2<String, Integer> arg0) throws Exception {
				System.out.println(arg0);
			}
		});
		
		System.out.println("-----------------------------------------\n\n");

/*		// Creating a pair RDD using the first word as the key in Java
		PairFunction<String, String, String> keyDatax = new PairFunction<String, String, String>() {
			public Tuple2<String, String> call(String x) {
				return new Tuple2(x.split(" ")[0], x);
			}
		};

		PairFunction<String, String, String> keyData = new PairFunction<String, String, String>() {
			public Tuple2<String, String> call(String x) {
				return new Tuple2(x.split("_")[0], x.split("_")[1]);
			}
		};
*/
		
		JavaPairRDD<String, Iterable<String>> pairs = textFile.mapToPair(new PairFunction<String, String, String>() {
			@Override
			public Tuple2<String, String> call(String x) {
				return new Tuple2(x.split("_")[0], x.split("_")[1]);
			}
		}).groupByKey();

		pairs.foreach(new VoidFunction<Tuple2<String, Iterable<String>>>() {
			@Override
			public void call(Tuple2<String, Iterable<String>> arg0) throws Exception {
				System.out.println(arg0);
			}
		});

//		pairs.saveAsTextFile("/home/tujuh/Documents/Example/inihasil");
		System.out.println("===============OKE====================");
	}
}
