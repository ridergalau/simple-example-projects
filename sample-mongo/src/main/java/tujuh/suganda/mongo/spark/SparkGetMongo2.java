package tujuh.suganda.mongo.spark;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;

import com.mongodb.spark.MongoSpark;

public class SparkGetMongo2 {
	final static String COLL = "lain_lain";
	final static SparkSession SPARK = SparkSession.builder().master("local[2]").appName("MongoSparkLoad")
			.config("spark.mongodb.input.uri", "mongodb://localhost:27017/mydb." + COLL)
			.config("spark.mongodb.output.uri", "mongodb://localhost:27017/mydb." + COLL).getOrCreate();

	final static JavaSparkContext JSC = new JavaSparkContext(SPARK.sparkContext());

	public static void main(String[] args) {

		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.ERROR);

		JavaRDD<Document> rdd = MongoSpark.load(JSC).filter(new Function<Document, Boolean>() {
			@Override
			public Boolean call(Document value) throws Exception {
				String jsonValue = value.toJson();
				org.json.JSONObject objeck = null;
				try {
					objeck = new org.json.JSONObject(jsonValue);
				} catch (Exception e) {

				}
				return objeck != null;
			}
		});

		// print first data
		System.out.println("first -> " + rdd.first());
		// count data Rdd
		System.out.println("TOTAL -> " + rdd.count());

		// Loop rdd
		rdd.foreach(new VoidFunction<Document>() {
			public void call(Document value) throws Exception {
				System.out.println("value " + value.toJson());
			}
		});
		System.out.println("Finish....");
		JSC.close();
	}
}
