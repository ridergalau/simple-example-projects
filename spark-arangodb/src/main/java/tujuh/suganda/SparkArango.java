package tujuh.suganda;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.SQLContext;

import com.arangodb.spark.ArangoSpark;
import com.arangodb.spark.WriteOptions;

import tujuh.suganda.model.*;

public class SparkArango {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().set("arangodb.hosts", "127.0.0.1:8529").set("arangodb.user", "root")
				.set("arangodb.password", "aing").setMaster("local[3]").setAppName("SparkArangoDb");

		JavaSparkContext sc = new JavaSparkContext(conf);
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);

		WriteOptions writeOptions = new WriteOptions();

		JavaRDD<MyModel> dataRdd = sc.textFile("src/main/resources/exampledata.csv")
				.map(new Function<String, MyModel>() {
					public MyModel call(String value) throws Exception {
						String json = "";
						MyModel model = null;
						try {
							String[] data = value.split(",");
							model = new MyModel(data[0], data[0], data[1], Integer.valueOf(data[2]), data[3],
									Integer.valueOf(data[4]), Integer.valueOf(data[5]), Integer.valueOf(data[6]),
									data[7], data[8], Integer.valueOf(data[9]), data[10], data[11]);
						} catch (Exception e) {
						}
						return model;
					}
				});

		try {
			//Stored to Arango
			ArangoSpark.save(dataRdd, "example", writeOptions.database("dbku"));
			System.out.println("---FINISH---");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
