package tujuh.suganda;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SQLContext;

import com.arangodb.spark.ArangoSpark;
import com.arangodb.spark.ReadOptions;
import com.arangodb.spark.rdd.api.java.ArangoJavaRDD;

import tujuh.suganda.model.MyModel;

public class SparkLoadFromArango {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().set("arangodb.hosts", "127.0.0.1:8529").set("arangodb.user", "root")
				.set("arangodb.password", "aing").setMaster("local[3]").setAppName("SparkArangoDb");
		JavaSparkContext sc = new JavaSparkContext(conf);

		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);

		ReadOptions option = new ReadOptions();
		option.database("dbku");

		ArangoJavaRDD<String> rdd = ArangoSpark.load(sc, "example", option.database("dbku"), String.class);
		rdd.foreach(new VoidFunction<String>() {
			public void call(String line) {
				System.out.println(line.toString());
				// System.out.println("ZIP CODE --> " + line.getZip());
			}
		});

		/*
		 * ArangoJavaRDD<MyModel> rdd = ArangoSpark.load(sc, "example",
		 * option.database("dbku"), MyModel.class);
		 * 
		 * rdd.foreach(new VoidFunction<MyModel>() { public void call(MyModel line) {
		 * System.out.println(line.toString()); // System.out.println("ZIP CODE --> " +
		 * line.getZip()); } });
		 */

		System.out.println("==FINISH==");
	}
}
